package blackjackNew;

public class GUI {

    public Frame frame;

    private Label playerScoreDisplay = new Label(100, 520, 200, 50, "", "");
    private Label dealerScoreDisplay = new Label(100, 280, 200, 50, "", "");
    private Label moneyDisplay = new Label(0, 520, 300, 50, "$", "");
    private Label resultDisplay = new Label(200, 600, 400, 50, "", "result");

    public GUI(Shared shared) {
        frame = new Frame(0, 0, 800, 800, shared);
        TextField betField = new TextField(0, 440, 300, 50, "Enter your bet", shared);

        Button hitButton = new Button(0, 200, 100, 50, "HIT", shared);
        Button standButton = new Button(0, 280, 100, 50, "STAND", shared);
        Button splitButton = new Button(0, 360, 100, 50, "SPLIT", shared);

        frame.add(betField);

        frame.add(hitButton);
        frame.add(standButton);
        frame.add(splitButton);

        frame.add(playerScoreDisplay);
        frame.add(dealerScoreDisplay);
        frame.add(moneyDisplay);
        frame.add(resultDisplay);

        frame.repaint();
        frame.revalidate();
    }

    public Label playerScoreDisplay() {
        return this.playerScoreDisplay;
    }

    public Label dealerScoreDisplay() {
        return this.dealerScoreDisplay;
    }

    public Label moneyDisplay() {
        return this.moneyDisplay;
    }

    public Label resultDisplay() {
        return this.resultDisplay;
    }
}
