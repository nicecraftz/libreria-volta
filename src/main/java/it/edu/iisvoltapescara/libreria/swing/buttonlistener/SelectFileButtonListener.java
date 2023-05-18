package it.edu.iisvoltapescara.libreria.swing.buttonlistener;

import it.edu.iisvoltapescara.libreria.swing.FrameApplication;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@RequiredArgsConstructor
public class SelectFileButtonListener implements ActionListener {
    private final FrameApplication finestra;

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = finestra.getFileChooser();

        int status = fileChooser.showOpenDialog(finestra);
        if (status != JFileChooser.APPROVE_OPTION) {
            return;
        }

        finestra.getChooseFile().setText(fileChooser.getSelectedFile().getName());
    }
}
