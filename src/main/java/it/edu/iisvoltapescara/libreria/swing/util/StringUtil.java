package it.edu.iisvoltapescara.libreria.swing.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil {
    public boolean isStringInteger(String str) {
        if (str == null || str.isEmpty()) return false;

        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isStringDouble(String str) {
        if (str == null || str.isEmpty()) return false;

        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
