/*
 * Joshua Liu
 * August 2025
 * Blackjack remake
 */

package blackjackOO;

import java.util.Random;
import java.util.Scanner;

public class Blackjack {
    static String[] suits = { "Spades", "Hearts", "Clubs", "Diamonds" };
    static String[] values = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
    static String[] deck = new String[52];
    static Scanner scanner = new Scanner(System.in);
    static int cardI = 0; // Current card index

    public static void main(String[] args) {

        createDeck();
        shuffleDeck();

        // for (String s : deck) {
        // System.out.println(s);
        // }

        String firstPlayerCard = deck[cardI++];
        String firstDealerCard = deck[cardI++];
        String secondPlayerCard = deck[cardI++];
        String secondDealerCard = deck[cardI++];

        Hand player = new Hand("Player", new String[] { firstPlayerCard, secondPlayerCard }, 1000);
        Hand dealer = new Hand("Dealer", new String[] { firstDealerCard, secondDealerCard }, Integer.MAX_VALUE);

        dealer.displayCards();
        player.displayCards();

        while (true) {
            if (scanner.nextLine().contains("1")) {
                player.addCard(deck[cardI++]);
            }
            dealer.displayCards();
            player.displayCards();
        }
    }

    // Default is one deck
    static void createDeck() {
        int i = 0;
        for (String suit : suits) {
            for (String value : values) {
                deck[i++] = value + "-" + suit;
            }
        }
    }

    // Can create more than 1 deck at once
    static void createDeck(int amountOfDecks) {
        amountOfDecks = amountOfDecks < 1 ? 1 : amountOfDecks;
        deck = new String[52 * amountOfDecks];

        int i = 0;
        for (int a = 0; a < amountOfDecks; a++) {
            for (String suit : suits) {
                for (String value : values) {
                    deck[i++] = value + "-" + suit;
                }
            }
        }
    }

    static void shuffleDeck() {
        Random random = new Random();

        for (int i = 0; i < deck.length; i++) {
            int swapI = random.nextInt(0, deck.length);

            String temp = deck[i];
            deck[i] = deck[swapI];
            deck[swapI] = temp;
        }
    }
}