package it.edu.iisvoltapescara.libreria.swing.util;

import it.edu.iisvoltapescara.libreria.swing.FrameApplication;

import javax.swing.*;

public class DialogHelper {
    private static final FrameApplication FRAME_APPLICATION = FrameApplication.getInstance();

    public static void showErrorDialog(String title, String message) {
        JOptionPane.showMessageDialog(FRAME_APPLICATION, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfoDialog(String title, String message) {
        JOptionPane.showMessageDialog(FRAME_APPLICATION, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static int showConfirmDialog(String title, String message) {
        return JOptionPane.showConfirmDialog(FRAME_APPLICATION, message, title, JOptionPane.YES_NO_OPTION);
    }

    public static String showInputDialog(String title, String message) {
        return JOptionPane.showInputDialog(FRAME_APPLICATION, message, title, JOptionPane.QUESTION_MESSAGE);
    }



}
