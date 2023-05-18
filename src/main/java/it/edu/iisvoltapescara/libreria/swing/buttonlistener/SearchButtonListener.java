package it.edu.iisvoltapescara.libreria.swing.buttonlistener;

import it.edu.iisvoltapescara.libreria.book.Book;
import it.edu.iisvoltapescara.libreria.book.BookManager;
import it.edu.iisvoltapescara.libreria.swing.FrameApplication;
import it.edu.iisvoltapescara.libreria.swing.util.DialogHelper;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

@RequiredArgsConstructor
public class SearchButtonListener implements ActionListener {
    private final FrameApplication frameApplication;

    @Override
    public void actionPerformed(ActionEvent e) {
        String title = DialogHelper.showInputDialog("Ricerca", "Inserisci il titolo del libro da cercare");

        if (title == null || title.isEmpty() || title.isBlank()) {
            DialogHelper.showErrorDialog("Errore", "Titolo non valido!");
            return;
        }

        BookManager bookManager = frameApplication.getBookManager();
        Optional<Book> bookOptional = bookManager.get(title);

        if (bookOptional.isEmpty()) {
            DialogHelper.showErrorDialog("Errore", "Libro non trovato!");
            return;
        }

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
        book.open();
    }
}
