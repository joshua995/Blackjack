package blackjackNew;

public class GUI {

    public Frame frame;

    private Label playerScoreDisplay = new Label(100, 520, 200, 50, "", "");
    private Label dealerScoreDisplay = new Label(100, 280, 200, 50, "", "");
    private Label moneyDisplay = new Label(0, 520, 300, 50, "$", "");
    private Label resultDisplay = new Label(200, 600, 400, 50, "", "result");
    private Label[] dealerCards = new Label[11];
    private Label[] playerCards = new Label[11];
    private int dealerI = 0;
    private int playerI = 0;

    public GUI(Shared shared) {
        frame = new Frame(0, 0, 800, 800, shared);
        TextField betField = new TextField(0, 440, 300, 50, "Enter your bet", shared);

        Button hitButton = new Button(0, 200, 100, 50, "HIT", shared);
        Button standButton = new Button(0, 280, 100, 50, "STAND", shared);
        Button splitButton = new Button(0, 360, 100, 50, "SPLIT", shared);

        for (int i = 0; i < dealerCards.length; i++) {
            dealerCards[i] = new Label(i * 50, 150, 25, 50, "", "card");
            frame.add(dealerCards[i]);
        }
        for (int i = 0; i < playerCards.length; i++) {
            playerCards[i] = new Label(i * 50, 390, 25, 50, "", "card");
            frame.add(playerCards[i]);
        }

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

    public Label[] dealerCards() {
        return this.dealerCards;
    }

    public Label[] playerCards() {
        return this.playerCards;
    }

    public int playerI() {
        return this.playerI;
    }

    public void addCardToPlayer(String cardText) {
        this.playerCards[playerI++].setText(cardText);
    }

    public void addCardToDealer(String cardText) {
        this.dealerCards[dealerI++].setText(cardText);
    }

    public void reset() {
        resultDisplay.setText("");
        for (Label l : this.playerCards) {
            l.setText("");
        }
        for (Label l : this.dealerCards) {
            l.setText("");
        }
        this.playerI = 0;
        this.dealerI = 0;
        this.frame.revalidate();
    }
}
