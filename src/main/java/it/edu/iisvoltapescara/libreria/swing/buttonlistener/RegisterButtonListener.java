package it.edu.iisvoltapescara.libreria.swing.buttonlistener;

import it.edu.iisvoltapescara.libreria.Main;
import it.edu.iisvoltapescara.libreria.book.Book;
import it.edu.iisvoltapescara.libreria.swing.FrameApplication;
import it.edu.iisvoltapescara.libreria.swing.util.DialogHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Classe che implementa l'interfaccia ActionListener
 * per gestire il click sul bottone di registrazione
 */
public class RegisterButtonListener implements ActionListener {
    private final FrameApplication finestra;

    public RegisterButtonListener(FrameApplication finestra) {
        this.finestra = finestra;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Prendo i valori dai campi
        String title = finestra.getTitleField().getText().trim();
        String author = finestra.getAuthorField().getText().trim();
        String pagesString = finestra.getPageField().getText().trim();
        String priceString = finestra.getPriceField().getText().trim();

        // Controllo che i campi non siano vuoti
        if (finestra.areFieldsInvalid()) {
            finestra.changeFieldsColorIfEmpty(Color.RED);
            DialogHelper.showInfoDialog("Errore", "I campi non possono essere vuoti!");
            finestra.changeFieldsColor(Color.WHITE);
            return;
        }

        // Controllo che i campi siano validi, in questo caso che siano numerici
        if (!Main.checkStringCanBeDouble(priceString)) {
            finestra.getPriceField().setBackground(Color.RED);
            DialogHelper.showErrorDialog("Errore", "Il campo prezzo deve essere numerico!");
            finestra.getPriceField().setBackground(Color.WHITE);
            return;
        }

        // Controllo che i campi siano validi, in questo caso che siano numerici
        if (!Main.checkStringCanBeInteger(pagesString)) {
            finestra.getPageField().setBackground(Color.RED);
            DialogHelper.showErrorDialog("Errore", "Il campo pagine deve essere numerico!");
            finestra.getPageField().setBackground(Color.WHITE);
            return;
        }

        // Controllo che sia stato selezionato un file
        if (finestra.getFileChooser().getSelectedFile() == null) {
            DialogHelper.showErrorDialog("Errore", "Non hai selezionato un file!");
            return;
        }


        int pages = Integer.parseInt(pagesString);
        double price = Double.parseDouble(priceString);
        File selectedFile = finestra.getFileChooser().getSelectedFile();

        File output = new File(System.getProperty("user.dir") + File.separator + "libri", selectedFile.getName());
        if (!output.getParentFile().exists()) output.getParentFile().mkdirs();

        // Copio il file selezionato nella cartella libri
        try {
            Files.copy(selectedFile.toPath(), output.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Creo un nuovo libro e lo aggiungo al BookManager
        Book book = new Book(title, author, pages, price, output);
        finestra.getBookManager().add(book);

        // Mostro un messaggio di successo
        DialogHelper.showInfoDialog("Registrato", "Libro registrato con successo!");

        // Resetto i campi e ricarico i libri nella text area
        finestra.resetAllFields();
        finestra.loadBooksInTextArea();
    }
}
