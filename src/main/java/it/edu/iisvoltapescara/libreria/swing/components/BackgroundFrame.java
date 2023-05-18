package it.edu.iisvoltapescara.libreria.swing.components;

import javax.swing.*;
import java.awt.*;

public class BackgroundFrame extends JFrame {
    private final Image backgroundImage;

    public BackgroundFrame() {
        backgroundImage = new ImageIcon("resources/img.png").getImage();
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }
        });
    }
}
