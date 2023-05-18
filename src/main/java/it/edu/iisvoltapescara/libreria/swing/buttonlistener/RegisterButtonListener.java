package it.edu.iisvoltapescara.libreria.swing.buttonlistener;

import it.edu.iisvoltapescara.libreria.book.Book;
import it.edu.iisvoltapescara.libreria.swing.FrameApplication;
import it.edu.iisvoltapescara.libreria.swing.util.DialogHelper;
import it.edu.iisvoltapescara.libreria.swing.util.StringUtil;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@RequiredArgsConstructor
public class RegisterButtonListener implements ActionListener {
    private final FrameApplication finestra;

    @Override
    public void actionPerformed(ActionEvent e) {
        String title = finestra.getTitleField().getText().trim();
        String author = finestra.getAuthorField().getText().trim();
        String pagesString = finestra.getPageField().getText().trim();
        String priceString = finestra.getPriceField().getText().trim();


        if (finestra.areFieldsInvalid()) {
            finestra.setFieldsColorIfEmpty(Color.RED);
            DialogHelper.showInfoDialog("Errore", "I campi non possono essere vuoti!");
            finestra.setFieldsColor(Color.WHITE);
            return;
        }

        if (!StringUtil.isStringDouble(priceString)) {
            finestra.getPriceField().setBackground(Color.RED);
            DialogHelper.showErrorDialog("Errore", "Il campo prezzo deve essere numerico!");
            finestra.getPriceField().setBackground(Color.WHITE);
            return;
        }

        if (!StringUtil.isStringInteger(pagesString)) {
            finestra.getPageField().setBackground(Color.RED);
            DialogHelper.showErrorDialog("Errore", "Il campo pagine deve essere numerico!");
            finestra.getPageField().setBackground(Color.WHITE);
            return;
        }

        if (finestra.getFileChooser().getSelectedFile() == null) {
            DialogHelper.showErrorDialog("Errore", "Non hai selezionato un file!");
            return;
        }

        int pages = Integer.parseInt(pagesString);
        double price = Double.parseDouble(priceString);
        File selectedFile = finestra.getFileChooser().getSelectedFile();

        File output = new File(System.getProperty("user.dir") + File.separator + "libri", selectedFile.getName());
        if (!output.getParentFile().exists()) output.getParentFile().mkdirs();

        try {
            Files.copy(selectedFile.toPath(), output.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Book book = new Book(title, author, pages, price, output);
        finestra.getBookManager().add(book);

        DialogHelper.showInfoDialog("Registrato", "Libro registrato con successo!");
        finestra.resetFileChooser();
        finestra.clearFields();
        finestra.loadBooksInTextArea();
    }
}
