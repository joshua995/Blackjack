package blackjackNew;

import javax.swing.JTextField;

public class GUI {

    public Frame frame = new Frame(0, 0, 500, 500);

    private Shared shared;

    private Button hitButton;
    private Button standButton;
    private Button splitButton;

    private JTextField betField = new JTextField("Enter your bet");

    public GUI(Shared shared) {
        this.shared = shared;
        Button hitButton = new Button(0, 0, 100, 20, "HIT", shared);
        Button standButton = new Button(0, 30, 100, 20, "STAND", shared);
        Button splitButton = new Button(0, 60, 100, 20, "SPLIT", shared);
        frame.add(hitButton);
        frame.add(standButton);
        frame.add(splitButton);
        frame.add(betField);
        frame.repaint();
        frame.revalidate();
    }
}
