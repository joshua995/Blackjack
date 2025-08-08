/*
 * Joshua Liu
 * August 2025
 * Blackjack remake object oriented structure
 */

package blackjackNew;

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

    static Hand player = null;
    static Hand dealer = null;
    static List<Hand> singlePlayerHands = new ArrayList<>();
    static int playerI = 0; // Current player's hand index

    static boolean gameOver = false;

    static Shared shared = new Shared();

    static GUI gui = new GUI(shared);

    public static void main(String[] args) {
        createDeck();// Can be set to use multiple decks

        while (!gameOver) {
            resetGame();

            double bet = 0.0;
            while (bet == 0) {
                System.out.printf("Your money: $%.2f -- Enter your bet: ", player.money());
                while (shared.bet() == "")
                    ;
                String inputBet = shared.bet();
                shared.bet("");
                try {
                    bet = Double.parseDouble(inputBet);
                    if (bet > player.money()) {
                        System.out.println("Insufficient funds, try again.");
                        bet = 0;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid bet, try again.");
                    bet = 0;
                }
            }
            player.subtractMoney(bet);

            dealer.displayCards();
            player.displayCards();
            System.out.println(instructions);

            boolean blackjack = player.score() == 21 || dealer.score() == 21 ? true : false;

            shared.breakOut(blackjack);
            while (!shared.breakOut()) {
                while (shared.move() == "")
                    ;
                String input = shared.move();
                if (input.contains("hit")) {// Hit
                    shared.move("");
                    if (singlePlayerHands.get(playerI).addCard(deck[cardI++])) {// If hand hits 21 or more
                        shared.breakOut(standLogic(shared.breakOut()));
                    }
                } else if (input.contains("stand")) {// Stand
                    shared.move("");
                    shared.breakOut(standLogic(shared.breakOut()));
                } else if (input.contains("split") && singlePlayerHands.get(playerI).canSplit()) {// Split
                    shared.move("");
                    singlePlayerHands.add(singlePlayerHands.get(playerI).splitHand(singlePlayerHands.size() - 1));
                }

                dealer.displayCards();
                for (int i = 0; i <= playerI; i++) {
                    if (i < singlePlayerHands.size())
                        singlePlayerHands.get(i).displayCards();
                }

                if (shared.breakOut()) {
                    break;
                }
                System.out.println(instructions);
            }
            // gui.frame.dispose(); // TODO temporary
            // Scoring
            checkWinner(blackjack, bet);
            System.out.println("-".repeat(100));
            if (player.money() <= 0) {
                gameOver = true;
            }
        }
        scanner.close();
    }

    static boolean standLogic(boolean breakOut) {
        playerI++;
        if (breakOut = (playerI == singlePlayerHands.size())) {// Check if player has unplayed splits
            dealer.dealerAddCards(deck, cardI,
                    singlePlayerHands.toArray(new Hand[singlePlayerHands.size()]));
        }
        return breakOut;
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

    static void checkWinner(boolean blackjack, double bet) {
        if (dealer.score() == 21) {
            if (blackjack) {
                System.out.print("Blackjack ");
            }
            System.out.println("Dealer Won");
        } else {
            if (blackjack) {
                System.out.println("Blackjack Payer Won");
                player.addMoney(bet * 2.5);
            } else {
                for (Hand hand : singlePlayerHands) {
                    if (hand.score() == 21 || (hand.score() > dealer.score() && hand.score() <= 21)
                            || (dealer.score() > 21 && hand.score() <= 21)) {
                        System.out.println(hand.type() + " Won");
                        player.addMoney(bet * 2);
                    } else {
                        System.out.println("Dealer Won Against " + hand.type());
                    }
                }
            }
        }
    }

    static void resetGame() {
        shared.reset();
        cardI = 0;
        playerI = 0;
        singlePlayerHands.clear();

        shuffleDeck();

        String firstPlayerCard = deck[cardI++];
        String firstDealerCard = deck[cardI++];
        String secondPlayerCard = deck[cardI++];
        String secondDealerCard = deck[cardI++];

        player = new Hand("Player", new String[] { firstPlayerCard, secondPlayerCard },
                player == null ? 1000 : player.money());
        dealer = new Hand("Dealer", new String[] { firstDealerCard, secondDealerCard }, Integer.MAX_VALUE);
        singlePlayerHands.add(player);
    }
}