package it.edu.iisvoltapescara.libreria.swing.components;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Classe che permette di creare un bottone arrotondato
 */
public class RoundedButton extends JButton {
    private final Color backgroundColor;

    public RoundedButton(String text, Color backgroundColor) {
        super(text);
        this.backgroundColor = backgroundColor;
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    /**
     * Riscrittura del metodo paintComponent per disegnare il bottone arrotondato
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Antialiasing per rendere il bottone più fluido
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Disegna il bottone arrotondato
        RoundRectangle2D.Float shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        g2.setColor(backgroundColor);
        g2.fill(shape);

        super.paintComponent(g2);
        g2.dispose();
    }
}