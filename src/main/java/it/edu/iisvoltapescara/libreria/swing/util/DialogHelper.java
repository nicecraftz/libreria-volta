package it.edu.iisvoltapescara.libreria.swing.util;

import it.edu.iisvoltapescara.libreria.swing.FrameApplication;
import lombok.experimental.UtilityClass;

import javax.swing.*;

@UtilityClass
public class DialogHelper {
    private static final FrameApplication FRAME_APPLICATION = FrameApplication.getInstance();

    public void showErrorDialog(String title, String message) {
        JOptionPane.showMessageDialog(FRAME_APPLICATION, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public void showInfoDialog(String title, String message) {
        JOptionPane.showMessageDialog(FRAME_APPLICATION, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public int showConfirmDialog(String title, String message) {
        return JOptionPane.showConfirmDialog(FRAME_APPLICATION, message, title, JOptionPane.YES_NO_OPTION);
    }

    public String showInputDialog(String title, String message) {
        return JOptionPane.showInputDialog(FRAME_APPLICATION, message, title, JOptionPane.QUESTION_MESSAGE);
    }


}
