package blackjackNew;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Shared {
    private boolean breakOut = false;
    private String move = "";
    private String bet = "";
    private List<Image> cardImg = new ArrayList<>();

    public Shared(List<Image> cardImg) {
        this.cardImg = cardImg;
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
        this.bet = string;
    }

    public synchronized List<Image> cardImg() {
        return this.cardImg;
    }

    public void reset() {
        this.breakOut = false;
        this.move = "";
        this.bet = "";
    }

}
