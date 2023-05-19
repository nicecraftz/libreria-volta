package it.edu.iisvoltapescara.libreria.swing.buttonlistener;

import it.edu.iisvoltapescara.libreria.book.Book;
import it.edu.iisvoltapescara.libreria.book.BookManager;
import it.edu.iisvoltapescara.libreria.swing.FrameApplication;
import it.edu.iisvoltapescara.libreria.swing.util.DialogHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

/**
 * Classe che implementa l'interfaccia ActionListener
 * per gestire il click sul bottone di ricerca
 */
public class SearchButtonListener implements ActionListener {
    private final FrameApplication frameApplication;

    public SearchButtonListener(FrameApplication frameApplication) {
        this.frameApplication = frameApplication;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String title = DialogHelper.showInputDialog("Ricerca", "Inserisci il titolo del libro da cercare");

        // se l'input è vuoto, mostra un messaggio di errore
        if (title == null || title.isEmpty()) {
            DialogHelper.showErrorDialog("Errore", "Titolo non valido!");
            return;
        }

        // cerca il libro
        BookManager bookManager = frameApplication.getBookManager();
        Optional<Book> bookOptional = bookManager.get(title);

        // se non è stato trovato, mostra un messaggio di errore
        if (!bookOptional.isPresent()) {
            DialogHelper.showErrorDialog("Errore", "Libro non trovato!");
            return;
        }

        // mostra un messaggio di conferma
        Book book = bookOptional.get();
        String format = "Titolo: %s\nAutore: %s\nPrezzo: %.2f EUR\nPagine: %d\n";
        int selection = DialogHelper.showConfirmDialog("Libro Trovato",
                String.format(
                        format,
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPrice(),
                        book.getPages()
                ) + "\nVuoi visionare il pdf?");

        if (selection != JOptionPane.YES_OPTION) return;

        // apre il pdf
        book.open();
    }
}
