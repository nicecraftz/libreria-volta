package it.edu.iisvoltapescara.libreria.swing.buttonlistener;

import it.edu.iisvoltapescara.libreria.swing.FrameApplication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe che implementa l'interfaccia ActionListener
 * per gestire il click sul bottone di selezione file
 */
public class SelectFileButtonListener implements ActionListener {
    private final FrameApplication finestra;

    public SelectFileButtonListener(FrameApplication finestra) {
        this.finestra = finestra;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = finestra.getFileChooser();

        // Apre la finestra di dialogo per la scelta del file
        int status = fileChooser.showOpenDialog(finestra);
        if (status != JFileChooser.APPROVE_OPTION) {
            return;
        }

        // Imposta il nome del file selezionato al bottone
        finestra.getChooseFile().setText(fileChooser.getSelectedFile().getName());
    }
}
