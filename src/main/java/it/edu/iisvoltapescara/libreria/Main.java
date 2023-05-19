package it.edu.iisvoltapescara.libreria;

import it.edu.iisvoltapescara.libreria.swing.FrameApplication;

public class Main {
    public static void main(String[] args) {
        new FrameApplication();
    }

    public static boolean checkStringCanBeInteger(String string) {
        if (string == null || string.isEmpty()) return false;

        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkStringCanBeDouble(String string) {
        if (string == null || string.isEmpty()) return false;

        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
