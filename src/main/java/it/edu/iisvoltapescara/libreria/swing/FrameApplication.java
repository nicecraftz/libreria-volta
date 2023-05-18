package it.edu.iisvoltapescara.libreria.swing;

import it.edu.iisvoltapescara.libreria.book.Book;
import it.edu.iisvoltapescara.libreria.book.BookManager;
import it.edu.iisvoltapescara.libreria.file.PDFFileFilter;
import it.edu.iisvoltapescara.libreria.swing.buttonlistener.*;
import it.edu.iisvoltapescara.libreria.swing.components.RoundedButton;
import it.edu.iisvoltapescara.libreria.swing.windowlistener.WindowManager;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

@Getter
public class FrameApplication extends JFrame {
    @Getter
    private static FrameApplication instance;

    private final JTextField titleField, authorField, pageField, priceField;
    private final JLabel titleLabel, authorLabel, pageLabel, priceLabel;
    private final RoundedButton delete, register, chooseFile, searchButton, removeButton;

    private final JFileChooser fileChooser;
    private final JTextArea bookArea;
    private final JScrollPane scrollPane;

    private final BookManager bookManager = new BookManager();

    public FrameApplication() {
        super("Libreria");

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(980, 620);
        setLocation(0, 0);

        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Image image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/img.jpg"))).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            }
        };

        p.setLayout(null);
        getContentPane().add(p);

        titleField = new JTextField();
        authorField = new JTextField();
        pageField = new JTextField();
        priceField = new JTextField();

        titleLabel = new JLabel("Titolo   ===> ");
        authorLabel = new JLabel("Autore   ===> ");
        pageLabel = new JLabel("Pagine   ===> ");
        priceLabel = new JLabel("Prezzo   ===> ");

        setOpaque(true);
        setLabelColor(new Color(128, 128, 128));

        delete = new RoundedButton("Cancella", Color.RED);
        register = new RoundedButton("Registra", Color.GREEN);
        chooseFile = new RoundedButton("Scegli File", Color.CYAN);
        searchButton = new RoundedButton("Cerca", Color.YELLOW);
        removeButton = new RoundedButton("Rimuovi", Color.PINK);

        bookArea = new JTextArea();
        bookArea.setEditable(false);
        scrollPane = new JScrollPane(bookArea);

        scrollPane.setBounds(750, 150, 200, 400);

        fileChooser = new JFileChooser(System.getProperty("user.dir"));
        fileChooser.setFileFilter(new PDFFileFilter());

        titleLabel.setBounds(15, 10, 80, 30);
        titleField.setBounds(140, 10, 320, 30);

        authorLabel.setBounds(500, 10, 80, 30);
        authorField.setBounds(610, 10, 320, 30);

        pageLabel.setBounds(15, 70, 80, 30);
        pageField.setBounds(140, 70, 320, 30);

        priceLabel.setBounds(500, 70, 80, 30);
        priceField.setBounds(610, 70, 320, 30);

        delete.setBounds(60, 350, 100, 30);
        register.setBounds(220, 350, 100, 30);
        removeButton.setBounds(380, 350, 100, 30);
        searchButton.setBounds(60, 250, 100, 30);
        chooseFile.setBounds(220, 250, 100, 30);

        addWindowListener(new WindowManager());

        delete.addActionListener(new DeleteButtonListener(this));
        register.addActionListener(new RegisterButtonListener(this));
        chooseFile.addActionListener(new SelectFileButtonListener(this));
        searchButton.addActionListener(new SearchButtonListener(this));
        removeButton.addActionListener(new RemoveActionListener(this));

        addComponents(p,
                titleLabel, authorLabel, pageLabel, priceLabel,
                titleField, authorField, pageField, priceField,
                delete, register, chooseFile, searchButton, removeButton,
                scrollPane
        );

        onEnable();
    }

    private void onEnable() {
        instance = this;
        bookManager.loadBooksFromSaveFile();
        loadBooksInTextArea();
        setVisible(true);
    }

    public void loadBooksInTextArea() {
        bookArea.setText("");
        int contatore = 1;
        for (Book book : bookManager.getBooks()) {
            bookArea.append("Libro " + contatore + ": " + book.getTitle() + "\n");
            contatore++;
        }
    }

    public void resetFileChooser() {
        chooseFile.setText("Seleziona File");
        fileChooser.setSelectedFile(null);
    }

    public void addComponents(JPanel destination, Component... components) {
        for (Component component : components) {
            destination.add(component);
        }
    }

    public void setLabelColor(Color color) {
        authorLabel.setForeground(color);
        titleLabel.setForeground(color);
        pageLabel.setForeground(color);
        priceLabel.setForeground(color);
    }

    public void setOpaque(boolean opaque) {
        authorLabel.setOpaque(opaque);
        titleLabel.setOpaque(opaque);
        pageLabel.setOpaque(opaque);
        priceLabel.setOpaque(opaque);
    }

    public void clearFields() {
        titleField.setText("");
        authorField.setText("");
        pageField.setText("");
        priceField.setText("");
    }

    public boolean areFieldsInvalid() {
        return titleField.getText().isEmpty()
                || authorField.getText().isEmpty()
                || priceField.getText().isEmpty()
                || priceField.getText().isEmpty();
    }

    public void setFieldsColorIfEmpty(Color color) {
        for (JTextField jTextField : Arrays.asList(titleField, authorField, pageField, priceField)) {
            if (jTextField.getText().isEmpty() || jTextField.getText().isBlank()) jTextField.setBackground(color);
        }
    }

    public void setFieldsColor(Color color) {
        for (JTextField jTextField : Arrays.asList(titleField, authorField, pageField, priceField)) {
            jTextField.setBackground(color);
        }
    }
}