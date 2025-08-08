package blackjackNew;

import java.awt.Font;

import javax.swing.JLabel;

public class Label extends JLabel {

    private int height;
    private String type;

    public Label(int x, int y, int width, int height, String text, String type) {
        this.type = type;
        this.height = height;
        this.setSize(width, height);
        if (text == "$") {
            x = 400 + ((400 - width) / 2);
        }
        this.setLocation(x, y);
        this.setText(text);
        this.setFont(new Font("Arial", 0, height / 2));
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        if (this.type == "result") {
            this.setSize(this.getHeight() * (text.length() - 1), 50 * countRepeats("Won", text));
        } else {
            this.setSize(this.getHeight() * (text.length() - 1), this.getHeight());
        }
    }

    private int countRepeats(String target, String text) {
        int repeats = 0;
        for (String s : text.split(" ")) {
            if (s.contains(target)) {
                repeats++;
            }
        }
        return repeats;
    }

}
