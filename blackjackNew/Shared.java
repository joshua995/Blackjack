package blackjackNew;

public class Shared {
    private boolean breakOut = false;
    private String move = "";
    private String bet = "";

    public Shared() {

    }

    public synchronized boolean breakOut() {
        return this.breakOut;
    }

    public synchronized void breakOut(boolean bool) {
        this.breakOut = bool;
    }

    public synchronized String move() {
        return this.move;
    }

    public synchronized void move(String string) {
        this.move = string;
    }

    public synchronized String bet() {
        return this.bet;
    }

    public synchronized void bet(String string) {
        System.out.println(string);
        this.bet = string;
    }

    public void reset() {
        this.breakOut = false;
        this.move = "";
        this.bet = "";
    }

}
