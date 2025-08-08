package blackjackNew;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Frame extends JFrame {
    private Shared shared;

    public Frame(int x, int y, int width, int height, Shared shared) {
        this.shared = shared;
        this.setLayout(null);
        this.setLocation(x, y);
        this.setSize(width, height);
        this.setVisible(true);
    }

    void drawCards(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        // TODO Shared.images
        int startingX = 100;
        int startingY = 390;
        g2d.drawRect(startingX, startingY, (int) (100 / 1.4), 100);
        g2d.drawRect(startingX + 20, startingY + 5, (int) (100 / 1.4), 100);
        g2d.drawRect(startingX + 20 * 2, startingY + 5 * 2, (int) (100 / 1.4), 100);
        g2d.drawRect(startingX + 20 * 3, startingY + 5 * 3, (int) (100 / 1.4), 100);
        g2d.drawRect(startingX + 20 * 4, startingY + 5 * 4, (int) (100 / 1.4), 100);
        g2d.drawRect(startingX + 20 * 5, startingY + 5 * 5, (int) (100 / 1.4), 100);
        g2d.drawRect(startingX + 20 * 6, startingY + 5 * 6, (int) (100 / 1.4), 100);
        g2d.drawRect(startingX + 20 * 7, startingY + 5 * 7, (int) (100 / 1.4), 100);
        g2d.drawRect(startingX + 20 * 8, startingY + 5 * 8, (int) (100 / 1.4), 100);
        g2d.drawRect(startingX + 20 * 9, startingY + 5 * 9, (int) (100 / 1.4), 100);
        g2d.drawRect(startingX + 20 * 10, startingY + 5 * 10, (int) (100 / 1.4), 100);

        int startingXD = 100;
        int startingYD = 150;
        g2d.drawRect(startingXD, startingYD, (int) (100 / 1.4), 100);
        g2d.drawRect(startingXD + 20, startingYD + 5, (int) (100 / 1.4), 100);
        g2d.drawRect(startingXD + 20 * 2, startingYD + 5 * 2, (int) (100 / 1.4), 100);
        g2d.drawRect(startingXD + 20 * 3, startingYD + 5 * 3, (int) (100 / 1.4), 100);
        g2d.drawRect(startingXD + 20 * 4, startingYD + 5 * 4, (int) (100 / 1.4), 100);
        g2d.drawRect(startingXD + 20 * 5, startingYD + 5 * 5, (int) (100 / 1.4), 100);
        g2d.drawRect(startingXD + 20 * 6, startingYD + 5 * 6, (int) (100 / 1.4), 100);
        g2d.drawRect(startingXD + 20 * 7, startingYD + 5 * 7, (int) (100 / 1.4), 100);
        g2d.drawRect(startingXD + 20 * 8, startingYD + 5 * 8, (int) (100 / 1.4), 100);
        g2d.drawRect(startingXD + 20 * 9, startingYD + 5 * 9, (int) (100 / 1.4), 100);
        g2d.drawRect(startingXD + 20 * 10, startingYD + 5 * 10, (int) (100 / 1.4), 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawCards(g);
    }
}
