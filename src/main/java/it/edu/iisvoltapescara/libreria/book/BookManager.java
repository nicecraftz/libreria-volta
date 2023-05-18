package it.edu.iisvoltapescara.libreria.book;

import it.edu.iisvoltapescara.libreria.file.ManagedFile;

import java.io.File;
import java.util.Optional;
import java.util.Vector;


public class BookManager {
    private final ManagedFile managedFile = new ManagedFile(new File(System.getProperty("user.dir") + File.separator + "data", "books.txt"));
    private final Vector<Book> books = new Vector<>();

    public int size() {
        return books.size();
    }

    public void add(Book book) {
        books.add(book);
        saveBookToFile(book);
    }

    public void remove(Book book) {
        books.remove(book);
        removeBookFromFile(book.getTitle());
    }

    public Optional<Book> get(String title) {
        return books.parallelStream().filter(book -> book.getTitle().equalsIgnoreCase(title)).findFirst();
    }

    public Vector<Book> getBooks() {
        return books;
    }

    public void loadBooksFromSaveFile() {
        for (String serializedBook : managedFile.readLines()) {
            deserializeBook(serializedBook).ifPresent(books::add);
        }
    }

    private void saveBookToFile(Book book) {
        String serializedBook = serializeBook(book);
        managedFile.append(serializedBook);
    }

    private void removeBookFromFile(String title) {
        managedFile.removeIf(line -> line.split(";")[0].equalsIgnoreCase(title));
    }

    private String serializeBook(Book book) {
        return String.format(
                "%s;%s;%s;%s;%s",
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                book.getPrice(),
                book.getFile().getAbsolutePath()
        );
    }

    private Optional<Book> deserializeBook(String serializedBook) {
        String[] bookData = serializedBook.split(";");

        String title = bookData[0];
        String author = bookData[1];
        int pages = Integer.parseInt(bookData[2]);
        double price = Double.parseDouble(bookData[3]);
        File file = bookData[4].equalsIgnoreCase("null") ? null : new File(bookData[4]);

        if (title.isBlank() || author.isBlank() || pages <= 0 || price < 0 || (file != null && !file.exists())) {
            return Optional.empty();
        }

        return Optional.of(new Book(title, author, pages, price, file));
    }

}