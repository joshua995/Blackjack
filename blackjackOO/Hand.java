/*
 * Joshua Liu
 * August 2025
 * Blackjack remake (class for hands)
 */

 package blackjackOO;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private String type = ""; // Player or dealer
    private int score = 0;
    private double money = 0.0;
    private List<String> cards = new ArrayList<>();

    public Hand(String type, String[] initialCards, double money) {
        this.type = type;

        for (String iC : initialCards) {
            addCard(iC);
        }
        this.money = money;
    }

    public int score() {
        return this.score;
    }

    public double money() {
        return this.money;
    }

    public void displayCards() {
        System.out.println(type + "'s Hand: " + this.score);
        for (String s : this.cards) {
            System.out.print(s + "\t");
        }
        System.out.println("\n");
    }

    public void addCard(String card) {
        this.cards.add(card);
        int aceCount = 0;
        this.score = 0;
        for (String s : this.cards) {
            String value = s.split("-")[0];
            System.out.println(value);
            if (value.contains("A")) {
                aceCount++;
                this.score += 1;
            } else if (value.contains("J") || value.contains("Q") || value.contains("K")) {
                this.score += 10;
            } else {
                this.score += Integer.parseInt(value);
            }
        }
        this.score += aceCount > 0 && this.score <= 11 ? 10 : 0;
    }
}
