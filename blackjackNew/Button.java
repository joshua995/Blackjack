package blackjackNew;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class Button extends JButton {
    public Button(int x, int y, int width, int height, String text, Shared shared) {
        width = height * (text.length() - 1);
        this.setSize(width, height);
        x = 400 + ((400 - width) / 2);
        this.setLocation(x, y);
        this.setText(text);
        this.setFont(new Font("Arial", 0, height));
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBackground(Color.green);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                shared.move(text.toLowerCase());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
    }
}