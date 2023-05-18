package it.edu.iisvoltapescara.libreria.swing.buttonlistener;

import it.edu.iisvoltapescara.libreria.swing.FrameApplication;
import it.edu.iisvoltapescara.libreria.swing.util.DialogHelper;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@RequiredArgsConstructor
public class DeleteButtonListener implements ActionListener {
    private final FrameApplication finestra;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int result = DialogHelper.showConfirmDialog("Cancella", "Sei sicuro di voler cancellare i campi?");
        if (result != JOptionPane.YES_OPTION) return;

        finestra.clearFields();
        DialogHelper.showInfoDialog("Cancella", "I Campi sono stati puliti!");
    }
}
