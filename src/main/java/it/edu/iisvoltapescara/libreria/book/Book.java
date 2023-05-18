package it.edu.iisvoltapescara.libreria.book;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Book {
    private final String title, author;
    private final int pages;
    private final double price;
    private final File file;

    public Book(String title, String author, int pages, double price, File file) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.price = price;
        this.file = file;
    }

    public void open() {
        if (file == null) return;
        Desktop desktop = Desktop.getDesktop();

        try {
            desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public double getPrice() {
        return price;
    }

    public File getFile() {
        return file;
    }
}
