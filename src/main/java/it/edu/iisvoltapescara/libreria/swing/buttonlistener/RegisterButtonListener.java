package it.edu.iisvoltapescara.libreria.swing.buttonlistener;

import it.edu.iisvoltapescara.libreria.book.Book;
import it.edu.iisvoltapescara.libreria.swing.FrameApplication;
import it.edu.iisvoltapescara.libreria.swing.util.DialogHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RegisterButtonListener implements ActionListener {
    private final FrameApplication finestra;

    public RegisterButtonListener(FrameApplication finestra) {
        this.finestra = finestra;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String title = finestra.getTitleField().getText();
        String author = finestra.getAuthorField().getText();
        String pagesString = finestra.getPageField().getText();
        String priceString = finestra.getPriceField().getText();

        if (finestra.areFieldsInvalid()) {
            finestra.setFieldsColorIfEmpty(Color.RED);
            DialogHelper.showInfoDialog("Errore", "I campi non possono essere vuoti!");
            finestra.setFieldsColor(Color.WHITE);
            return;
        }

        if (finestra.getFileChooser().getSelectedFile() == null) {
            DialogHelper.showErrorDialog("Errore", "Non hai selezionato un file!");
            return;
        }

        int pages = Integer.parseInt(pagesString);
        double price = Double.parseDouble(priceString);
        File selectedFile = finestra.getFileChooser().getSelectedFile();
        Book book = new Book(title, author, pages, price, selectedFile);
        finestra.getBookManager().add(book);

        DialogHelper.showInfoDialog("Registrato", "Libro registrato con successo!");
        finestra.resetFileChooser();
        finestra.clearFields();
        finestra.loadBooksInTextArea();
    }
}
