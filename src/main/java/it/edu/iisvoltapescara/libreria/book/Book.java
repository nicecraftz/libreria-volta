package it.edu.iisvoltapescara.libreria.book;

import lombok.Data;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@Data
public class Book {
    private final String title, author;
    private final int pages;
    private final double price;
    private final File file;

    public void open() {
        if (file == null) return;
        Desktop desktop = Desktop.getDesktop();

        try {
            desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
