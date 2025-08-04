/*
 * Joshua Liu
 * August 2025
 * Blackjack remake
 */

package blackjackOO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {
    static String[] suits = { "Spades", "Hearts", "Clubs", "Diamonds" };
    static String[] values = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
    static String[] deck = new String[52];
    static Scanner scanner = new Scanner(System.in);
    static int cardI = 0; // Current card index
    static String instructions = "'1' to HIT | '2' to STAND | '3' to SPLIT";

    static Hand player;
    static Hand dealer;
    static List<Hand> singlePlayerHands = new ArrayList<>();
    static int playerI = 0;

    public static void main(String[] args) {
        createDeck();// TODO change to user input to set how many decks to use

        resetGame();

        boolean blackjack = player.score() == 21 || dealer.score() == 21 ? true : false;

        boolean breakOut = blackjack;
        while (!breakOut) {
            String input = scanner.nextLine();
            if (input.contains("1")) {// Hit
                if (singlePlayerHands.get(playerI).addCard(deck[cardI++])) {
                    if (singlePlayerHands.get(playerI).score() <= 21) {
                        playerI++;
                        if (breakOut = (playerI == singlePlayerHands.size())) {
                            dealer.dealerAddCards(deck, cardI, new Hand[] { player });
                        }
                    } else {
                        playerI++;
                        breakOut = (playerI == singlePlayerHands.size());
                    }
                }
            } else if (input.contains("2")) {// Stand
                playerI++;
                if (breakOut = (playerI == singlePlayerHands.size())) {
                    dealer.dealerAddCards(deck, cardI, new Hand[] { player });
                }
            } else if (input.contains("3") && singlePlayerHands.get(playerI).canSplit()) {// Split
                singlePlayerHands.add(singlePlayerHands.get(playerI).splitHand(playerI));
            }

            dealer.displayCards();
            for (int i = 0; i <= playerI; i++) {
                if (i < singlePlayerHands.size())
                    singlePlayerHands.get(i).displayCards();
            }
            System.out.println(instructions);
            if (breakOut) {
                break;
            }
        }
        if (dealer.score() == 21) {
            if (blackjack) {
                System.out.print("Blackjack ");
            }
            System.out.println("Dealer Won");
        } else {
            if (blackjack) {
                System.out.print("Blackjack Payer Won");
            } else {
                for (Hand hand : singlePlayerHands) {
                    if (hand.score() == 21 || (hand.score() > dealer.score() && hand.score() <= 21)
                            || dealer.score() > 21) {
                        System.out.println(hand.type() + " Won");
                    } else {
                        System.out.println("Dealer Won Against " + hand.type());
                    }
                }
            }
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

    static void resetGame() {
        cardI = 0;
        playerI = 0;
        singlePlayerHands.clear();

        shuffleDeck();

        String firstPlayerCard = deck[cardI++];
        String firstDealerCard = deck[cardI++];
        String secondPlayerCard = deck[cardI++];
        String secondDealerCard = deck[cardI++];

        player = new Hand("Player", new String[] { firstPlayerCard, secondPlayerCard }, 1000);
        dealer = new Hand("Dealer", new String[] { firstDealerCard, secondDealerCard }, Integer.MAX_VALUE);
        singlePlayerHands.add(player);

        dealer.displayCards();
        player.displayCards();
        System.out.println(instructions);
    }
}