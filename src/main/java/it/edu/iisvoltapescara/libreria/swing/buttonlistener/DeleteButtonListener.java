package it.edu.iisvoltapescara.libreria.swing.buttonlistener;

import it.edu.iisvoltapescara.libreria.swing.FrameApplication;
import it.edu.iisvoltapescara.libreria.swing.util.DialogHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe che implementa l'interfaccia ActionListener
 * per gestire il click sul bottone di pulizia dei campi
 */
public class DeleteButtonListener implements ActionListener {
    private final FrameApplication finestra;

    public DeleteButtonListener(FrameApplication finestra) {
        this.finestra = finestra;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // mostra un messaggio di conferma
        int result = DialogHelper.showConfirmDialog("Cancella", "Sei sicuro di voler cancellare i campi?");
        if (result != JOptionPane.YES_OPTION) return;


        // pulisce i campi
        finestra.resetAllFields();
        DialogHelper.showInfoDialog("Cancella", "I Campi sono stati puliti!");
    }
}
