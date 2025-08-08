package blackjackNew;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class TextField extends JTextField {

    public TextField(int x, int y, int width, int height, String text, Shared shared) {
        this.setSize(width, height);
        this.setLocation(x, y);
        this.setToolTipText(text);
        this.setEditable(true);
        this.setEnabled(true);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shared.bet(e.getActionCommand());
            }
        });
    }

}
