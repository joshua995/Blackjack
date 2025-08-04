/*
 * Joshua Liu
 * August 2025
 * Blackjack remake (class for hands)
 * Allows for multiplayer later
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
            System.out.print(s + "  ");
        }
        System.out.println("\n");
    }

    public void addCard(String card) {
        this.cards.add(card);
        this.calculateScore();
    }

    // For dealer (CPU)
    public void dealerAddCards(String[] deck, int cardI, Hand[] players) {
        this.calculateScore();

        // Ensure that the dealer adds cards until its value is greater or equal to any
        // player
        for (Hand player : players) {
            while (player.score() > this.score && this.score < 21) {
                this.addCard(deck[cardI++]);
            }
        }
    }

    public boolean canSplit() {
        if (this.cards.size() > 2) {
            return false;
        }

        String valueS = this.cards.get(0).split("-")[0];
        int value = valueS.contains("A") ? 1
                : (valueS.contains("J") || valueS.contains("Q") || valueS.contains("K")) ? 10
                        : Integer.parseInt(valueS);

        valueS = this.cards.get(1).split("-")[0];
        int value1 = valueS.contains("A") ? 1
                : (valueS.contains("J") || valueS.contains("Q") || valueS.contains("K")) ? 10
                        : Integer.parseInt(valueS);

        return value == value1 ? true : false;
    }

    public Hand splitHand(int playerI) {
        String card = this.cards.get(1);
        this.cards.remove(1);
        this.calculateScore();
        return new Hand("Player " + (playerI + 1), new String[] { card }, money);
    }

    private void calculateScore() {
        int aceCount = 0;
        this.score = 0;
        for (String s : this.cards) {
            String value = s.split("-")[0];

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
