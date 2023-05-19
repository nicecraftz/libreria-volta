package it.edu.iisvoltapescara.libreria.swing.buttonlistener;

import it.edu.iisvoltapescara.libreria.book.BookManager;
import it.edu.iisvoltapescara.libreria.swing.FrameApplication;
import it.edu.iisvoltapescara.libreria.swing.util.DialogHelper;
import it.edu.iisvoltapescara.libreria.book.Book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

/**
 * Classe che implementa l'interfaccia ActionListener
 * per gestire il click sul bottone di rimozione
 */
public class RemoveActionListener implements ActionListener {
    private final FrameApplication frameApplication;

    public RemoveActionListener(FrameApplication frameApplication) {
        this.frameApplication = frameApplication;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // mostra un messaggio di richiesta input
        String title = DialogHelper.showInputDialog("Rimozione", "Inserisci il titolo del libro da rimuovere");


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

        // rimuove il libro
        Book book = bookOptional.get();
        bookManager.remove(book);

        // mostra un messaggio di conferma
        DialogHelper.showInfoDialog("Rimozione", "Libro rimosso con successo!");

        // aggiorna la lista dei libri nell'area di testo
        frameApplication.loadBooksInTextArea();
    }
}
