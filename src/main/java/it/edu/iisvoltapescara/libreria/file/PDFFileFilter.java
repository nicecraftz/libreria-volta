package it.edu.iisvoltapescara.libreria.file;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class PDFFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        return !f.isDirectory() && f.getName().toLowerCase().endsWith(".pdf");
    }

    @Override
    public String getDescription() {
        return "PDF Files (*.pdf)";
    }
}
