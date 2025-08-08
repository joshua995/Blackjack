package blackjackNew;

public class Shared {
    private boolean breakOut = false;
    private String move = "";

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

}
