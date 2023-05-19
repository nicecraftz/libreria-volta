package it.edu.iisvoltapescara.libreria.swing;

import it.edu.iisvoltapescara.libreria.book.Book;
import it.edu.iisvoltapescara.libreria.book.BookManager;
import it.edu.iisvoltapescara.libreria.file.PDFFileFilter;
import it.edu.iisvoltapescara.libreria.swing.buttonlistener.*;
import it.edu.iisvoltapescara.libreria.swing.components.RoundedButton;
import it.edu.iisvoltapescara.libreria.swing.windowlistener.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;


public class FrameApplication extends JFrame {
    private static FrameApplication instance; // Istanza della classe ottenibile in modo statico.

    private final JTextField titleField = new JTextField();
    private final JTextField authorField = new JTextField();
    private final JTextField pageField = new JTextField();
    private final JTextField priceField = new JTextField();

    private final RoundedButton chooseFile = new RoundedButton("Scegli File", Color.CYAN);
    private final JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));

    private final JTextArea bookArea = new JTextArea();
    private final BookManager bookManager = new BookManager();

    public FrameApplication() {
        super("Libreria");

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(980, 620);
        setLocation(0, 0);

        /*
         * Creazione di un JPanel con sfondo.
         * faciamo l'override del metodo paintComponent per disegnare l'immagine.
         */
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Image image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/img.jpg"))).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            }
        };

        p.setLayout(null);
        getContentPane().add(p);

        JLabel titleLabel = new JLabel("Titolo   ===> ");
        JLabel authorLabel = new JLabel("Autore   ===> ");
        JLabel pageLabel = new JLabel("Pagine   ===> ");
        JLabel priceLabel = new JLabel("Prezzo   ===> ");

        authorLabel.setOpaque(true);
        titleLabel.setOpaque(true);
        pageLabel.setOpaque(true);
        priceLabel.setOpaque(true);

        Color color = new Color(128, 128, 128);
        authorLabel.setForeground(color);
        titleLabel.setForeground(color);
        pageLabel.setForeground(color);
        priceLabel.setForeground(color);

        RoundedButton delete = new RoundedButton("Cancella", Color.RED);
        RoundedButton register = new RoundedButton("Registra", Color.GREEN);
        RoundedButton searchButton = new RoundedButton("Cerca", Color.YELLOW);
        RoundedButton removeButton = new RoundedButton("Rimuovi", Color.PINK);

        bookArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(bookArea);
        scrollPane.setBounds(650, 10, 300, 450);

        fileChooser.setFileFilter(new PDFFileFilter());
        chooseFile.setBounds(260, 280, 100, 30);

        titleLabel.setBounds(15, 10, 80, 30);
        titleField.setBounds(140, 10, 480, 30);

        pageLabel.setBounds(15, 70, 80, 30);
        pageField.setBounds(140, 70, 480, 30);

        authorLabel.setBounds(15, 130, 80, 30);
        authorField.setBounds(140, 130, 480, 30);

        priceLabel.setBounds(15, 200, 80, 30);
        priceField.setBounds(140, 200, 480, 30);


        register.setBounds(60, 350, 100, 30);
        delete.setBounds(480, 350, 100, 30);

        searchButton.setBounds(650, 500, 100, 30);
        removeButton.setBounds(850, 500, 100, 30);

        addWindowListener(new WindowManager());

        chooseFile.addActionListener(new SelectFileButtonListener(this));
        delete.addActionListener(new DeleteButtonListener(this));
        register.addActionListener(new RegisterButtonListener(this));
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

    /**
     * Metodo principale.
     */
    private void onEnable() {
        instance = this;
        bookManager.loadBooksFromSaveFile();
        loadBooksInTextArea();
        setVisible(true);
    }

    /**
     * Carica tutti i libri nella text area.
     */
    public void loadBooksInTextArea() {
        bookArea.setText("I Tuoi Libri:\n");
        int contatore = 1;
        for (Book book : bookManager.getBooks()) {
            bookArea.append("Libro " + contatore + ": " + book.getTitle() + "\n");
            contatore++;
        }
    }

    /**
     * Aggiunge i componenti al pannello.
     *
     * @param destination Pannello di destinazione.
     * @param components  Componenti da aggiungere.
     */
    public void addComponents(JPanel destination, Component... components) {
        for (Component component : components) {
            destination.add(component);
        }
    }


    /**
     * Resetta tutti i campi del form.
     */
    public void resetAllFields() {
        titleField.setText("");
        authorField.setText("");
        pageField.setText("");
        priceField.setText("");

        fileChooser.setSelectedFile(null);
        chooseFile.setText("Seleziona File");
    }

    /**
     * Controlla se i campi sono vuoti.
     *
     * @return true se i campi sono vuoti, false altrimenti.
     */
    public boolean areFieldsInvalid() {
        return titleField.getText().isEmpty()
                || authorField.getText().isEmpty()
                || priceField.getText().isEmpty()
                || priceField.getText().isEmpty();
    }

    /**
     * Cambia il colore di sfondo dei campi vuoti.
     *
     * @param color Colore da impostare.
     */
    public void changeFieldsColorIfEmpty(Color color) {
        for (JTextField jTextField : Arrays.asList(titleField, authorField, pageField, priceField)) {
            if (jTextField.getText().isEmpty()) jTextField.setBackground(color);
        }
    }

    /**
     * Cambia il colore di sfondo di tutti i campi.
     *
     * @param color Colore da impostare.
     */
    public void changeFieldsColor(Color color) {
        for (JTextField jTextField : Arrays.asList(titleField, authorField, pageField, priceField)) {
            jTextField.setBackground(color);
        }
    }

    public static FrameApplication getInstance() {
        return instance;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JTextField getAuthorField() {
        return authorField;
    }

    public JTextField getPageField() {
        return pageField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public RoundedButton getChooseFile() {
        return chooseFile;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    public BookManager getBookManager() {
        return bookManager;
    }
}