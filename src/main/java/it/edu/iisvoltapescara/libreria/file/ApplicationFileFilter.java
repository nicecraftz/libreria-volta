package it.edu.iisvoltapescara.libreria.file;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ApplicationFileFilter extends FileFilter {
    private static final String PDF_EXTENSION = ".pdf";

    @Override
    public boolean accept(File f) {
        return f != null && f.getName().toLowerCase().endsWith(PDF_EXTENSION);
    }

    @Override
    public String getDescription() {
        return null;
    }
}
