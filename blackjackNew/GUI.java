package blackjackNew;

public class GUI {

    public Frame frame = new Frame(0, 0, 500, 500);

    private Label playerHandDisplay = new Label(300, 0, 200, 20, "");
    private Label dealerHandDisplay = new Label(300, 30, 200, 20, "");

    public GUI(Shared shared) {
        TextField betField = new TextField(0, 90, 200, 20, "Enter your bet", shared);
        Button hitButton = new Button(0, 0, 100, 20, "HIT", shared);
        Button standButton = new Button(0, 30, 100, 20, "STAND", shared);
        Button splitButton = new Button(0, 60, 100, 20, "SPLIT", shared);

        frame.add(betField);

        frame.add(hitButton);
        frame.add(standButton);
        frame.add(splitButton);

        frame.add(playerHandDisplay);
        frame.add(dealerHandDisplay);

        frame.repaint();
        frame.revalidate();
    }

    public Label playerHandDisplay() {
        return this.playerHandDisplay;
    }

    public Label dealerHandDisplay() {
        return this.dealerHandDisplay;
    }
}
