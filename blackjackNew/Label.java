package blackjackNew;

import javax.swing.JLabel;

public class Label extends JLabel {

    public Label(int x, int y, int width, int height, String text) {
        this.setSize(width, height);
        this.setLocation(x, y);
        this.setText(text);
    }

}
