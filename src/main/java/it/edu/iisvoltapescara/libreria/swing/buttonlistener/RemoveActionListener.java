package it.edu.iisvoltapescara.libreria.swing.buttonlistener;

import it.edu.iisvoltapescara.libreria.book.BookManager;
import it.edu.iisvoltapescara.libreria.swing.FrameApplication;
import it.edu.iisvoltapescara.libreria.swing.util.DialogHelper;
import it.edu.iisvoltapescara.libreria.book.Book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class RemoveActionListener implements ActionListener {
    private final FrameApplication frameApplication;

    public RemoveActionListener(FrameApplication frameApplication) {
        this.frameApplication = frameApplication;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String title = DialogHelper.showInputDialog("Rimozione", "Inserisci il titolo del libro da rimuovere");

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
        bookManager.remove(book);
        DialogHelper.showInfoDialog("Rimozione", "Libro rimosso con successo!");
        frameApplication.loadBooksInTextArea();
    }
}
