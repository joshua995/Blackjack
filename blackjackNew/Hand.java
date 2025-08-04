/*
 * Joshua Liu
 * August 2025
 * Blackjack remake (class for hands)
 * Allows for multiplayer later
 */

package blackjackNew;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private String type = ""; // Player or dealer
    private int score = 0;
    private double money = 0.0;
    private List<String> cards = new ArrayList<>();
    private boolean hideCard = true;

    public Hand(String type, String[] initialCards, double money) {
        this.type = type;

        for (String iC : initialCards) {
            addCard(iC);
        }
        this.money = money;
    }

    public String type() {
        return this.type;
    }

    public int score() {
        return this.score;
    }

    public double money() {
        return this.money;
    }

    public void addMoney(double winnings) {
        this.money += winnings;
    }

    public void subtractMoney(double bet) {
        this.money -= bet;
    }

    public void displayCards() {
        if (this.type != "Dealer" || !this.hideCard) {
            System.out.println(this.type + "'s Hand: " + this.score);
            for (String s : this.cards) {
                System.out.print(s + "  ");
            }
        } else {
            int value = getValue(this.cards.get(0).split("-")[0]);
            System.out.println(this.type + "'s Hand: " + value);
            System.out.print(this.cards.get(0));
        }
        System.out.println("\n");
    }

    public boolean addCard(String card) {
        this.cards.add(card);
        this.calculateScore();

        return this.score >= 21 ? true : false;
    }

    // For dealer (CPU)
    public void dealerAddCards(String[] deck, int cardI, Hand[] players) {
        this.hideCard = false;
        this.calculateScore();

        // Ensure that the dealer adds cards until its value is greater or equal to any
        // player
        for (Hand player : players) {
            while (player.score() > this.score && player.score() <= 21) {
                this.addCard(deck[cardI++]);
            }
        }
    }

    public boolean canSplit() {
        if (this.cards.size() > 2 || this.cards.size() == 1) { // Prevent splitting after a hit or immediately after a
                                                               // split
            return false;
        }

        int value = getValue(this.cards.get(0).split("-")[0]);
        int value1 = getValue(this.cards.get(1).split("-")[0]);

        return value == value1 ? true : false;
    }

    public Hand splitHand(int playerI) {
        String card = this.cards.get(1);
        this.cards.remove(1);
        this.calculateScore();
        return new Hand("Player's Split " + (playerI + 1), new String[] { card }, money);
    }

    private int getValue(String card) {
        String valueS = card;
        return valueS.contains("A") ? 1
                : (valueS.contains("J") || valueS.contains("Q") || valueS.contains("K")) ? 10
                        : Integer.parseInt(valueS);

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
