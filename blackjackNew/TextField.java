package blackjackNew;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class TextField extends JTextField {

    public TextField(int x, int y, int width, int height, String text, Shared shared) {
        this.setSize(width, height);
        x = 400 + ((400 - width) / 2);
        this.setLocation(x, y);
        this.setToolTipText(text);
        this.setFont(new Font("Arial", 0, height));
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shared.bet(e.getActionCommand());
            }
        });
    }

}
