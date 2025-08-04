/*
 * Joshua Liu
 * 2019
 * Blackjack
 */
import java.time.*;
import java.util.*;
import java.util.concurrent.*;

public class Blackjack {
	// sN = swap number for dealing the cards
	static int sN = 0;
	// Total player score
	static int playerScore = 0;
	static int playerSplitScore = 0;
	// Total dealer score
	static int dealerScore = 0;

	// To detect split/deactivate it
	static boolean split = false;
	static boolean activeSplit = true;
	// Allows the player to play with the 2nd hand
	static boolean secondHand = false;
	// Toggles between reseting and playing
	static boolean play = true;
	// If the player runs out of money the game ends
	static boolean playWithBet = true;

	// Allows the player to toggle between a visual hand or not
	static boolean visual = false;

	// Player's hand/score
	static ArrayList<String> playerHand = new ArrayList<String>();
	static ArrayList<Integer> playerScoreArray = new ArrayList<Integer>();
	// Player's split hand
	static ArrayList<String> playerHandSplit = new ArrayList<String>();
	static ArrayList<Integer> playerSplitScoreArray = new ArrayList<Integer>();
	// Dealer's hand/score
	static ArrayList<String> dealerHand = new ArrayList<String>();
	static ArrayList<Integer> dealerScoreArray = new ArrayList<Integer>();

	// Used in split
	static String splitExtras = "";
	static String splitExtras1 = "";

	// Create visuals for the hand
	static ArrayList<String> displayCardsPlayer = new ArrayList<String>();
	static ArrayList<String> displayCardsPlayer2nd = new ArrayList<String>();
	static ArrayList<String> displayCardsDealer = new ArrayList<String>();

	// Creates scanner to allow player to hit or stand
	static Scanner scan = new Scanner(System.in);

	// Suits and values for the deck
	static String[] suit = { "Spades", "Diamonds", "Clubs", "Hearts" };
	static String[] value = { "A ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "9 ", "10 ", "J ", "Q ", "K " };
	static String[] deck = new String[52];

	// Scanner that allows the player to bet a certain amount
	static Scanner bet = new Scanner(System.in);

	// Default money for player is $1000
	static int moneyOwned = 1000;
	static int storeBet = 0;
	static boolean betting = true;

	public static void main(String[] args) throws InterruptedException {
		/***** Rules and how the game works *****/
		// Dealer overrides all scores even if player has 2 scores of 21 and if it is a
		// draw
		// 1 split at the start is allowed if possible
		// Dealer will hit if it's score is under 16 and if the score is under any of
		// the player's scores
		/***************************************/

		// Everything that runs the game is here *(at the very bottom)*
		playGame();
	}

	/*
	 * Methods ahead 29 (Ctrl + F) might help: createDeck, startGame, dealForPlayer,
	 * countPlayerScore, displayPlayerHandAndScore, dealForDealer, countDealerScore,
	 * displayDealerHandAndScore, dealForPlayerSplit, countPlayerSplitScore,
	 * displayPlayerSplitHandAndScore, checkBlackjack, checkPlayerBust,
	 * checkFinalScores, checkAcesWild, onSplit, switchCaseHitNumber1,
	 * switchCaseStandNumber2, switchCaseSplitNumber3, reset, makeCards, timePlayed,
	 * placeBet, gemaOver, ticTacToe, printGameBoard, placePiece, checkWinner,
	 * playGame
	 */
	// Creates and shuffles the deck
	public static void createDeck() {
		// Creates the deck
		for (int i = 0; i < deck.length; i++) {
			deck[i] = value[i % 13] + suit[i / 13];
		}

		// shuffles deck
		for (int r = 0; r < deck.length; r++) {
			int index = (int) (Math.random() * deck.length);

			String temp = deck[r];
			deck[r] = deck[index];
			deck[index] = temp;
		}
	}

	// Deals 2 cards for the player and dealer
	public static void startGame() throws InterruptedException {
		// Player can place a bet (optional)
		//placeBet();

		createDeck();

		dealForDealer();
		dealForDealer();
		countDealerScore();
		displayDealerHandAndScore();

		dealForPlayer();
		dealForPlayer();
		countPlayerScore();
		displayPlayerHandAndScore();

		// Checks for a blackjack
		checkBlackjack();

		// Displays instructions
		System.out.println("Hit = '1' | Stand = '2' | Split = '3' | Visual = '5' | Redeal/Play again = '4'");
	}

	// Deals a card for player's first hand
	public static void dealForPlayer() {
		playerHand.add(deck[sN]);

		deck[sN] = "";

		sN++;
	}

	// Counts the score for player's 1st hand
	public static void countPlayerScore() {
		playerScoreArray.clear();
		playerScore = 0;
		// Spades
		if (playerHand.contains("A Spades")) {
			playerScoreArray.add(1);
		}
		if (playerHand.contains("2 Spades")) {
			playerScoreArray.add(2);
		}
		if (playerHand.contains("3 Spades")) {
			playerScoreArray.add(3);
		}
		if (playerHand.contains("4 Spades")) {
			playerScoreArray.add(4);
		}
		if (playerHand.contains("5 Spades")) {
			playerScoreArray.add(5);
		}
		if (playerHand.contains("6 Spades")) {
			playerScoreArray.add(6);
		}
		if (playerHand.contains("7 Spades")) {
			playerScoreArray.add(7);
		}
		if (playerHand.contains("8 Spades")) {
			playerScoreArray.add(8);
		}
		if (playerHand.contains("9 Spades")) {
			playerScoreArray.add(9);
		}
		if (playerHand.contains("10 Spades")) {
			playerScoreArray.add(10);
		}
		if (playerHand.contains("J Spades")) {
			playerScoreArray.add(10);
		}
		if (playerHand.contains("Q Spades")) {
			playerScoreArray.add(10);
		}
		if (playerHand.contains("K Spades")) {
			playerScoreArray.add(10);
		}
		// Diamonds
		if (playerHand.contains("A Diamonds")) {
			playerScoreArray.add(1);
		}
		if (playerHand.contains("2 Diamonds")) {
			playerScoreArray.add(2);
		}
		if (playerHand.contains("3 Diamonds")) {
			playerScoreArray.add(3);
		}
		if (playerHand.contains("4 Diamonds")) {
			playerScoreArray.add(4);
		}
		if (playerHand.contains("5 Diamonds")) {
			playerScoreArray.add(5);
		}
		if (playerHand.contains("6 Diamonds")) {
			playerScoreArray.add(6);
		}
		if (playerHand.contains("7 Diamonds")) {
			playerScoreArray.add(7);
		}
		if (playerHand.contains("8 Diamonds")) {
			playerScoreArray.add(8);
		}
		if (playerHand.contains("9 Diamonds")) {
			playerScoreArray.add(9);
		}
		if (playerHand.contains("10 Diamonds")) {
			playerScoreArray.add(10);
		}
		if (playerHand.contains("J Diamonds")) {
			playerScoreArray.add(10);
		}
		if (playerHand.contains("Q Diamonds")) {
			playerScoreArray.add(10);
		}
		if (playerHand.contains("K Diamonds")) {
			playerScoreArray.add(10);
		}
		// Clubs
		if (playerHand.contains("A Clubs")) {
			playerScoreArray.add(1);
		}
		if (playerHand.contains("2 Clubs")) {
			playerScoreArray.add(2);
		}
		if (playerHand.contains("3 Clubs")) {
			playerScoreArray.add(3);
		}
		if (playerHand.contains("4 Clubs")) {
			playerScoreArray.add(4);
		}
		if (playerHand.contains("5 Clubs")) {
			playerScoreArray.add(5);
		}
		if (playerHand.contains("6 Clubs")) {
			playerScoreArray.add(6);
		}
		if (playerHand.contains("7 Clubs")) {
			playerScoreArray.add(7);
		}
		if (playerHand.contains("8 Clubs")) {
			playerScoreArray.add(8);
		}
		if (playerHand.contains("9 Clubs")) {
			playerScoreArray.add(9);
		}
		if (playerHand.contains("10 Clubs")) {
			playerScoreArray.add(10);
		}
		if (playerHand.contains("J Clubs")) {
			playerScoreArray.add(10);
		}
		if (playerHand.contains("Q Clubs")) {
			playerScoreArray.add(10);
		}
		if (playerHand.contains("K Clubs")) {
			playerScoreArray.add(10);
		}
		// Hearts
		if (playerHand.contains("A Hearts")) {
			playerScoreArray.add(1);
		}
		if (playerHand.contains("2 Hearts")) {
			playerScoreArray.add(2);
		}
		if (playerHand.contains("3 Hearts")) {
			playerScoreArray.add(3);
		}
		if (playerHand.contains("4 Hearts")) {
			playerScoreArray.add(4);
		}
		if (playerHand.contains("5 Hearts")) {
			playerScoreArray.add(5);
		}
		if (playerHand.contains("6 Hearts")) {
			playerScoreArray.add(6);
		}
		if (playerHand.contains("7 Hearts")) {
			playerScoreArray.add(7);
		}
		if (playerHand.contains("8 Hearts")) {
			playerScoreArray.add(8);
		}
		if (playerHand.contains("9 Hearts")) {
			playerScoreArray.add(9);
		}
		if (playerHand.contains("10 Hearts")) {
			playerScoreArray.add(10);
		}
		if (playerHand.contains("J Hearts")) {
			playerScoreArray.add(10);
		}
		if (playerHand.contains("Q Hearts")) {
			playerScoreArray.add(10);
		}
		if (playerHand.contains("K Hearts")) {
			playerScoreArray.add(10);
		}
		for (int i = 0; i < playerScoreArray.size(); i++) {
			playerScore += playerScoreArray.get(i);
		}
		// Check for a wild ace and then recounts the score
		checkAcesWild();
		playerScore = 0;
		for (int i = 0; i < playerScoreArray.size(); i++) {
			playerScore += playerScoreArray.get(i);
		}
	}

	// Displays the hand and score for player's 1st hand
	public static void displayPlayerHandAndScore() {
		System.out.println("Player's hand:");
		System.out.println(playerHand);

		System.out.println("Player's score");
		System.out.println(playerScore + "\n");
	}

	// Deals a card for dealer's
	public static void dealForDealer() {
		dealerHand.add(deck[sN]);

		deck[sN] = "";

		sN++;
	}

	// Counts the score for dealer's hand
	public static void countDealerScore() {
		dealerScoreArray.clear();
		dealerScore = 0;
		// Spades
		if (dealerHand.contains("A Spades")) {
			dealerScoreArray.add(1);
		}
		if (dealerHand.contains("2 Spades")) {
			dealerScoreArray.add(2);
		}
		if (dealerHand.contains("3 Spades")) {
			dealerScoreArray.add(3);
		}
		if (dealerHand.contains("4 Spades")) {
			dealerScoreArray.add(4);
		}
		if (dealerHand.contains("5 Spades")) {
			dealerScoreArray.add(5);
		}
		if (dealerHand.contains("6 Spades")) {
			dealerScoreArray.add(6);
		}
		if (dealerHand.contains("7 Spades")) {
			dealerScoreArray.add(7);
		}
		if (dealerHand.contains("8 Spades")) {
			dealerScoreArray.add(8);
		}
		if (dealerHand.contains("9 Spades")) {
			dealerScoreArray.add(9);
		}
		if (dealerHand.contains("10 Spades")) {
			dealerScoreArray.add(10);
		}
		if (dealerHand.contains("J Spades")) {
			dealerScoreArray.add(10);
		}
		if (dealerHand.contains("Q Spades")) {
			dealerScoreArray.add(10);
		}
		if (dealerHand.contains("K Spades")) {
			dealerScoreArray.add(10);
		}
		// Diamonds
		if (dealerHand.contains("A Diamonds")) {
			dealerScoreArray.add(1);
		}
		if (dealerHand.contains("2 Diamonds")) {
			dealerScoreArray.add(2);
		}
		if (dealerHand.contains("3 Diamonds")) {
			dealerScoreArray.add(3);
		}
		if (dealerHand.contains("4 Diamonds")) {
			dealerScoreArray.add(4);
		}
		if (dealerHand.contains("5 Diamonds")) {
			dealerScoreArray.add(5);
		}
		if (dealerHand.contains("6 Diamonds")) {
			dealerScoreArray.add(6);
		}
		if (dealerHand.contains("7 Diamonds")) {
			dealerScoreArray.add(7);
		}
		if (dealerHand.contains("8 Diamonds")) {
			dealerScoreArray.add(8);
		}
		if (dealerHand.contains("9 Diamonds")) {
			dealerScoreArray.add(9);
		}
		if (dealerHand.contains("10 Diamonds")) {
			dealerScoreArray.add(10);
		}
		if (dealerHand.contains("J Diamonds")) {
			dealerScoreArray.add(10);
		}
		if (dealerHand.contains("Q Diamonds")) {
			dealerScoreArray.add(10);
		}
		if (dealerHand.contains("K Diamonds")) {
			dealerScoreArray.add(10);
		}
		// Clubs
		if (dealerHand.contains("A Clubs")) {
			dealerScoreArray.add(1);
		}
		if (dealerHand.contains("2 Clubs")) {
			dealerScoreArray.add(2);
		}
		if (dealerHand.contains("3 Clubs")) {
			dealerScoreArray.add(3);
		}
		if (dealerHand.contains("4 Clubs")) {
			dealerScoreArray.add(4);
		}
		if (dealerHand.contains("5 Clubs")) {
			dealerScoreArray.add(5);
		}
		if (dealerHand.contains("6 Clubs")) {
			dealerScoreArray.add(6);
		}
		if (dealerHand.contains("7 Clubs")) {
			dealerScoreArray.add(7);
		}
		if (dealerHand.contains("8 Clubs")) {
			dealerScoreArray.add(8);
		}
		if (dealerHand.contains("9 Clubs")) {
			dealerScoreArray.add(9);
		}
		if (dealerHand.contains("10 Clubs")) {
			dealerScoreArray.add(10);
		}
		if (dealerHand.contains("J Clubs")) {
			dealerScoreArray.add(10);
		}
		if (dealerHand.contains("Q Clubs")) {
			dealerScoreArray.add(10);
		}
		if (dealerHand.contains("K Clubs")) {
			dealerScoreArray.add(10);
		}
		// Hearts
		if (dealerHand.contains("A Hearts")) {
			dealerScoreArray.add(1);
		}
		if (dealerHand.contains("2 Hearts")) {
			dealerScoreArray.add(2);
		}
		if (dealerHand.contains("3 Hearts")) {
			dealerScoreArray.add(3);
		}
		if (dealerHand.contains("4 Hearts")) {
			dealerScoreArray.add(4);
		}
		if (dealerHand.contains("5 Hearts")) {
			dealerScoreArray.add(5);
		}
		if (dealerHand.contains("6 Hearts")) {
			dealerScoreArray.add(6);
		}
		if (dealerHand.contains("7 Hearts")) {
			dealerScoreArray.add(7);
		}
		if (dealerHand.contains("8 Hearts")) {
			dealerScoreArray.add(8);
		}
		if (dealerHand.contains("9 Hearts")) {
			dealerScoreArray.add(9);
		}
		if (dealerHand.contains("10 Hearts")) {
			dealerScoreArray.add(10);
		}
		if (dealerHand.contains("J Hearts")) {
			dealerScoreArray.add(10);
		}
		if (dealerHand.contains("Q Hearts")) {
			dealerScoreArray.add(10);
		}
		if (dealerHand.contains("K Hearts")) {
			dealerScoreArray.add(10);
		}

		for (int i = 0; i < dealerScoreArray.size(); i++) {
			dealerScore += dealerScoreArray.get(i);
		}
		// Check for wild ace and then recounts the score
		checkAcesWild();
		dealerScore = 0;
		for (int i = 0; i < dealerScoreArray.size(); i++) {
			dealerScore += dealerScoreArray.get(i);
		}
	}

	// Displays the hand and score for dealer'shand
	public static void displayDealerHandAndScore() {
		System.out.println("Dealer's hand:");
		System.out.println(dealerHand);

		System.out.println("Dealer's score:");
		System.out.println(dealerScore + "\n");
	}

	// Deals a card for player's 2nd hand
	public static void dealForPlayerSplit() {
		playerHandSplit.add(deck[sN]);

		deck[sN] = "";

		sN++;
	}

	// Counts the score for player's 2nd hand
	public static void countPlayerSplitScore() {
		playerSplitScoreArray.clear();
		playerSplitScore = 0;
		// Spades
		if (playerHandSplit.contains("A Spades")) {
			playerSplitScoreArray.add(1);
		}
		if (playerHandSplit.contains("2 Spades")) {
			playerSplitScoreArray.add(2);
		}
		if (playerHandSplit.contains("3 Spades")) {
			playerSplitScoreArray.add(3);
		}
		if (playerHandSplit.contains("4 Spades")) {
			playerSplitScoreArray.add(4);
		}
		if (playerHandSplit.contains("5 Spades")) {
			playerSplitScoreArray.add(5);
		}
		if (playerHandSplit.contains("6 Spades")) {
			playerSplitScoreArray.add(6);
		}
		if (playerHandSplit.contains("7 Spades")) {
			playerSplitScoreArray.add(7);
		}
		if (playerHandSplit.contains("8 Spades")) {
			playerSplitScoreArray.add(8);
		}
		if (playerHandSplit.contains("9 Spades")) {
			playerSplitScoreArray.add(9);
		}
		if (playerHandSplit.contains("10 Spades")) {
			playerSplitScoreArray.add(10);
		}
		if (playerHandSplit.contains("J Spades")) {
			playerSplitScoreArray.add(10);
		}
		if (playerHandSplit.contains("Q Spades")) {
			playerSplitScoreArray.add(10);
		}
		if (playerHandSplit.contains("K Spades")) {
			playerSplitScoreArray.add(10);
		}
		// Diamonds
		if (playerHandSplit.contains("A Diamonds")) {
			playerSplitScoreArray.add(1);
		}
		if (playerHandSplit.contains("2 Diamonds")) {
			playerSplitScoreArray.add(2);
		}
		if (playerHandSplit.contains("3 Diamonds")) {
			playerSplitScoreArray.add(3);
		}
		if (playerHandSplit.contains("4 Diamonds")) {
			playerSplitScoreArray.add(4);
		}
		if (playerHandSplit.contains("5 Diamonds")) {
			playerSplitScoreArray.add(5);
		}
		if (playerHandSplit.contains("6 Diamonds")) {
			playerSplitScoreArray.add(6);
		}
		if (playerHandSplit.contains("7 Diamonds")) {
			playerSplitScoreArray.add(7);
		}
		if (playerHandSplit.contains("8 Diamonds")) {
			playerSplitScoreArray.add(8);
		}
		if (playerHandSplit.contains("9 Diamonds")) {
			playerSplitScoreArray.add(9);
		}
		if (playerHandSplit.contains("10 Diamonds")) {
			playerSplitScoreArray.add(10);
		}
		if (playerHandSplit.contains("J Diamonds")) {
			playerSplitScoreArray.add(10);
		}
		if (playerHandSplit.contains("Q Diamonds")) {
			playerSplitScoreArray.add(10);
		}
		if (playerHandSplit.contains("K Diamonds")) {
			playerSplitScoreArray.add(10);
		}
		// Clubs
		if (playerHandSplit.contains("A Clubs")) {
			playerSplitScoreArray.add(1);
		}
		if (playerHandSplit.contains("2 Clubs")) {
			playerSplitScoreArray.add(2);
		}
		if (playerHandSplit.contains("3 Clubs")) {
			playerSplitScoreArray.add(3);
		}
		if (playerHandSplit.contains("4 Clubs")) {
			playerSplitScoreArray.add(4);
		}
		if (playerHandSplit.contains("5 Clubs")) {
			playerSplitScoreArray.add(5);
		}
		if (playerHandSplit.contains("6 Clubs")) {
			playerSplitScoreArray.add(6);
		}
		if (playerHandSplit.contains("7 Clubs")) {
			playerSplitScoreArray.add(7);
		}
		if (playerHandSplit.contains("8 Clubs")) {
			playerSplitScoreArray.add(8);
		}
		if (playerHandSplit.contains("9 Clubs")) {
			playerSplitScoreArray.add(9);
		}
		if (playerHandSplit.contains("10 Clubs")) {
			playerSplitScoreArray.add(10);
		}
		if (playerHandSplit.contains("J Clubs")) {
			playerSplitScoreArray.add(10);
		}
		if (playerHandSplit.contains("Q Clubs")) {
			playerSplitScoreArray.add(10);
		}
		if (playerHandSplit.contains("K Clubs")) {
			playerSplitScoreArray.add(10);
		}
		// Hearts
		if (playerHandSplit.contains("A Hearts")) {
			playerSplitScoreArray.add(1);
		}
		if (playerHandSplit.contains("2 Hearts")) {
			playerSplitScoreArray.add(2);
		}
		if (playerHandSplit.contains("3 Hearts")) {
			playerSplitScoreArray.add(3);
		}
		if (playerHandSplit.contains("4 Hearts")) {
			playerSplitScoreArray.add(4);
		}
		if (playerHandSplit.contains("5 Hearts")) {
			playerSplitScoreArray.add(5);
		}
		if (playerHandSplit.contains("6 Hearts")) {
			playerSplitScoreArray.add(6);
		}
		if (playerHandSplit.contains("7 Hearts")) {
			playerSplitScoreArray.add(7);
		}
		if (playerHandSplit.contains("8 Hearts")) {
			playerSplitScoreArray.add(8);
		}
		if (playerHandSplit.contains("9 Hearts")) {
			playerSplitScoreArray.add(9);
		}
		if (playerHandSplit.contains("10 Hearts")) {
			playerSplitScoreArray.add(10);
		}
		if (playerHandSplit.contains("J Hearts")) {
			playerSplitScoreArray.add(10);
		}
		if (playerHandSplit.contains("Q Hearts")) {
			playerSplitScoreArray.add(10);
		}
		if (playerHandSplit.contains("K Hearts")) {
			playerSplitScoreArray.add(10);
		}

		for (int i = 0; i < playerSplitScoreArray.size(); i++) {
			playerSplitScore += playerSplitScoreArray.get(i);
		}
		// Check for wild ace and then recounts the score
		checkAcesWild();
		playerSplitScore = 0;
		for (int i = 0; i < playerSplitScoreArray.size(); i++) {
			playerSplitScore += playerSplitScoreArray.get(i);
		}
	}

	// Displays the hand and score for player's 2nd hand
	public static void displayPlayerSplitHandAndScore() {
		System.out.println("Player's 2nd hand:");
		System.out.println(playerHandSplit);

		System.out.println("Player's 2nd score:");
		System.out.println(playerSplitScore + "\n");
	}

	// Checks for a blackjack
	public static void checkBlackjack() throws InterruptedException {
		if (dealerScore == 21) {
			// Makes a visual of the hand
			makeCards();
			System.out.println("Dealer Blackjack, you lose! \n");
			// Display's player's money
			System.out.println("Your money: $" + moneyOwned);

			// Game ends if player has no money
			gameOver();

			play = false;
		} else if (playerScore == 21) {
			// Makes a visual of the hand
			makeCards();
			System.out.println("Blackjack, you win!!! \n");

			// Adds money
			moneyOwned += storeBet * 2.5;
			// Display's player's money
			System.out.println("Your money: $" + moneyOwned);
			play = false;
		}
	}

	// Checks for a player bust
	public static void checkPlayerBust() throws InterruptedException {
		if (!split && !secondHand && playerScore > 21) {
			System.out.println("----------------------------------------");
			// Displays both player and dealer's hand
			displayDealerHandAndScore();

			displayPlayerHandAndScore();
			// Makes a visual of the hand
			makeCards();
			System.out.println("You busted! Dealer wins \n");
			// Display's player's money
			System.out.println("Your money: $" + moneyOwned);
			// Game ends if player has no money
			gameOver();

			play = false;
		} else if (split && !secondHand && playerScore > 21) {
			System.out.println("----------------------------------------");
			// Displays dealer's hand
			displayDealerHandAndScore();

			// Displays player's 1st hand
			displayPlayerHandAndScore();

			System.out.println("Player's first hand final score: " + playerScore + " is a bust \n");

			// Displays the player's 2nd hand/instructions afterwards
			displayPlayerSplitHandAndScore();
			System.out.println("Hit = '1' | Stand = '2' | Split = '3' | Visual = '5' | Redeal/Play again = '4'");

			secondHand = true;
		} else if (split && secondHand && (playerSplitScore > 21 && playerScore > 21)) {
			System.out.println("----------------------------------------");
			// Makes a visual of the hand
			makeCards();

			System.out.println("You busted!");
			System.out.println("Dealer wins");
			// Display's player's money
			System.out.println("Your money: $" + moneyOwned);
			// Game ends if player has no money
			gameOver();
			play = false;
		} else if (split && secondHand && playerSplitScore > 21) {
			System.out.println("----------------------------------------");
			System.out.println("Player's 2nd hand final score: " + playerSplitScore + " is a bust \n");

			// Automatically stands
			switchCaseStandNumber2();
		}
	}

	// Check to see who wins
	public static void checkFinalScores() throws InterruptedException {
		System.out.println("----------------------------------------");
		// Display for dealer
		displayDealerHandAndScore();

		if (!split) {
			// Display for player's 1st hand
			displayPlayerHandAndScore();
			System.out.println("Player's final score: " + playerScore + "\n");
		} else {
			// Display for both of player's hands
			displayPlayerHandAndScore();
			System.out.println("Player's 1st hand final score: " + playerScore);
			System.out.println("Player's 2nd hand final score: " + playerSplitScore);
			System.out.println("Dealer's final score: " + dealerScore + "\n");
		}
		// If dealer's score is over 21 it is a bust
		if (dealerScore > 21) {
			System.out.println("Dealer busted, you win!! \n");
			// Adds money
			moneyOwned += storeBet * 2;
			// Display's player's money
			System.out.println("Your money: $" + moneyOwned + "\n");
		} // If dealer's score is over player's score
		else if ((dealerScore >= playerScore || playerScore > 21)
				&& (dealerScore >= playerSplitScore || playerSplitScore > 21)) {
			System.out.println("You lost! \n");
			// Display's player's money
			System.out.println("Your money: $" + moneyOwned + "\n");
			// Game ends if player has no money
			gameOver();
		} // If player's score is over dealer's score
		else if ((dealerScore < playerScore || dealerScore < playerSplitScore)
				&& (playerScore <= 21 || playerSplitScore <= 21)) {
			System.out.println("You win!!! \n");
			// Adds money
			moneyOwned += storeBet * 2;
			// Display's player's money
			System.out.println("Your money: $" + moneyOwned + "\n");
		}
		play = false;
	}

	// Checks for a wild ace
	public static void checkAcesWild() {
		// For player's 1st hand
		// If hand contains 4 aces
		if (playerHand.contains("A Spades") && playerHand.contains("A Diamonds") && playerHand.contains("A Clubs")
				&& playerHand.contains("A Hearts")) {
			if (playerHand.contains("A Spades") || playerHand.contains("A Diamonds") || playerHand.contains("A Clubs")
					|| playerHand.contains("A Hearts")) {
				if (playerScore < 11) {
					playerScoreArray.add(10);
				}
			}
			// If hand contains 3 aces
		} else if ((playerHand.contains("A Spades") && playerHand.contains("A Diamonds")
				&& playerHand.contains("A Clubs"))
				|| (playerHand.contains("A Spades") && playerHand.contains("A Diamonds")
						&& playerHand.contains("A Hearts"))
				|| (playerHand.contains("A Spades") && playerHand.contains("A Hearts")
						&& playerHand.contains("A Clubs"))
				|| (playerHand.contains("A Clubs") && playerHand.contains("A Hearts")
						&& playerHand.contains("A Diamonds"))) {
			if (playerHand.contains("A Spades") || playerHand.contains("A Diamonds") || playerHand.contains("A Clubs")
					|| playerHand.contains("A Hearts")) {
				if (playerScore <= 11) {
					playerScoreArray.add(10);
				}
			}
			// If hand contains 2 aces
		} else if ((playerHand.contains("A Spades") && playerHand.contains("A Diamonds"))
				|| (playerHand.contains("A Spades") && playerHand.contains("A Clubs"))
				|| (playerHand.contains("A Spades") && playerHand.contains("A Hearts"))
				|| (playerHand.contains("A Clubs") && playerHand.contains("A Hearts"))
				|| (playerHand.contains("A Clubs") && playerHand.contains("A Diamonds"))
				|| (playerHand.contains("A Diamonds") && playerHand.contains("A Hearts"))) {
			if (playerHand.contains("A Spades") || playerHand.contains("A Diamonds") || playerHand.contains("A Clubs")
					|| playerHand.contains("A Hearts")) {
				if (playerScore <= 11) {
					playerScoreArray.add(10);
				}
			}
			// If hand contains 1 ace
		} else if (playerHand.contains("A Spades") || playerHand.contains("A Diamonds")
				|| playerHand.contains("A Clubs") || playerHand.contains("A Hearts")) {
			if (playerScore <= 11) {
				playerScoreArray.add(10);
			}
		}
		// For dealer
		// If hand contains 4 aces
		if (dealerHand.contains("A Spades") && dealerHand.contains("A Diamonds") && dealerHand.contains("A Clubs")
				&& dealerHand.contains("A Hearts")) {
			if (dealerHand.contains("A Spades") || dealerHand.contains("A Diamonds") || dealerHand.contains("A Clubs")
					|| dealerHand.contains("A Hearts")) {
				if (dealerScore <= 11) {
					dealerScoreArray.add(10);
				}
			}
			// If hand contains 3 aces
		} else if ((dealerHand.contains("A Spades") && dealerHand.contains("A Diamonds")
				&& dealerHand.contains("A Clubs"))
				|| (dealerHand.contains("A Spades") && dealerHand.contains("A Diamonds")
						&& dealerHand.contains("A Hearts"))
				|| (dealerHand.contains("A Spades") && dealerHand.contains("A Hearts")
						&& dealerHand.contains("A Clubs"))
				|| (dealerHand.contains("A Clubs") && dealerHand.contains("A Hearts")
						&& dealerHand.contains("A Diamonds"))) {
			if (dealerHand.contains("A Spades") || dealerHand.contains("A Diamonds") || dealerHand.contains("A Clubs")
					|| dealerHand.contains("A Hearts")) {
				if (dealerScore <= 11) {
					dealerScoreArray.add(10);
				}
			}
			// If hand contains 2 aces
		} else if ((dealerHand.contains("A Spades") && dealerHand.contains("A Diamonds"))
				|| (dealerHand.contains("A Spades") && dealerHand.contains("A Clubs"))
				|| (dealerHand.contains("A Spades") && dealerHand.contains("A Hearts"))
				|| (dealerHand.contains("A Clubs") && dealerHand.contains("A Hearts"))
				|| (dealerHand.contains("A Clubs") && dealerHand.contains("A Diamonds"))
				|| (dealerHand.contains("A Diamonds") && dealerHand.contains("A Hearts"))) {
			if (dealerHand.contains("A Spades") || dealerHand.contains("A Diamonds") || dealerHand.contains("A Clubs")
					|| dealerHand.contains("A Hearts")) {
				if (dealerScore <= 11) {
					dealerScoreArray.add(10);
				}
			}
			// If hand contains 1 aces
		} else if (dealerHand.contains("A Spades") || dealerHand.contains("A Diamonds")
				|| dealerHand.contains("A Clubs") || dealerHand.contains("A Hearts")) {
			if (dealerScore <= 11) {
				dealerScoreArray.add(10);
			}
		}
		// For player's 2nd hand
		// If hand contains 4 aces
		if (playerHandSplit.contains("A Spades") && playerHandSplit.contains("A Diamonds")
				&& playerHandSplit.contains("A Clubs") && playerHandSplit.contains("A Hearts")) {
			if (playerHandSplit.contains("A Spades") || playerHandSplit.contains("A Diamonds")
					|| playerHandSplit.contains("A Clubs") || playerHandSplit.contains("A Hearts")) {
				if (playerSplitScore <= 11) {
					playerSplitScoreArray.add(10);
				}
			}
			// If hand contains 3 aces
		} else if ((playerHandSplit.contains("A Spades") && playerHandSplit.contains("A Diamonds")
				&& playerHandSplit.contains("A Clubs"))
				|| (playerHandSplit.contains("A Spades") && playerHandSplit.contains("A Diamonds")
						&& playerHandSplit.contains("A Hearts"))
				|| (playerHandSplit.contains("A Spades") && playerHandSplit.contains("A Hearts")
						&& playerHandSplit.contains("A Clubs"))
				|| (playerHandSplit.contains("A Clubs") && playerHandSplit.contains("A Hearts")
						&& playerHandSplit.contains("A Diamonds"))) {
			if (playerHandSplit.contains("A Spades") || playerHandSplit.contains("A Diamonds")
					|| playerHandSplit.contains("A Clubs") || playerHandSplit.contains("A Hearts")) {
				if (playerSplitScore <= 11) {
					playerSplitScoreArray.add(10);
				}
			}
			// If hand contains 2 aces
		} else if ((playerHandSplit.contains("A Spades") && playerHandSplit.contains("A Diamonds"))
				|| (playerHandSplit.contains("A Spades") && playerHandSplit.contains("A Clubs"))
				|| (playerHandSplit.contains("A Spades") && playerHandSplit.contains("A Hearts"))
				|| (playerHandSplit.contains("A Clubs") && playerHandSplit.contains("A Hearts"))
				|| (playerHandSplit.contains("A Clubs") && playerHandSplit.contains("A Diamonds"))
				|| (playerHandSplit.contains("A Diamonds") && playerHandSplit.contains("A Hearts"))) {
			if (playerHandSplit.contains("A Spades") || playerHandSplit.contains("A Diamonds")
					|| playerHandSplit.contains("A Clubs") || playerHandSplit.contains("A Hearts")) {
				if (playerSplitScore <= 11) {
					playerSplitScoreArray.add(10);
				}
			}
			// If hand contains 1 aces
		} else if (playerHandSplit.contains("A Spades") || playerHandSplit.contains("A Diamonds")
				|| playerHandSplit.contains("A Clubs") || playerHandSplit.contains("A Hearts")) {
			if (playerSplitScore <= 11) {
				playerSplitScoreArray.add(10);
			}
		}
	}

	// Happens once when the player splits
	public static void onSplit() {
		// Takes the 2nd card and places it on the 2nd hand
		splitExtras = playerHand.get(1);
		playerHandSplit.add(splitExtras);
		playerHand.remove(1);
		// Counts and displays player's 1st hand
		countPlayerScore();
		displayPlayerHandAndScore();
		// Counts and displays player's 2nd hand
		countPlayerSplitScore();
		displayPlayerSplitHandAndScore();

		// Instructions
		System.out.println("Hit = '1' | Stand = '2' | Split = '3' | Visual = '5' | Redeal/Play again = '4'");

		// Does not allow another split
		activeSplit = false;

		split = true;
	}

	// Switch case number 1 (Hit)
	public static void switchCaseHitNumber1() throws InterruptedException {
		// Deactivates split
		activeSplit = false;
		if (!split && !secondHand) {
			// Display's dealer's hand and score
			displayDealerHandAndScore();

			dealForPlayer();
			countPlayerScore();
			displayPlayerHandAndScore();
			// Displays instructions
			System.out.println("Hit = '1' | Stand = '2' | Split = '3' | Visual = '5' | Redeal/Play again = '4'");
			// Check for a bust
			checkPlayerBust();
		} else if (split && secondHand) {
			// Display's dealer's hand and score
			displayDealerHandAndScore();
			// Display's player's 1st hand and score
			displayPlayerHandAndScore();
			// Displays player's 2nd hand and score
			dealForPlayerSplit();
			countPlayerSplitScore();
			displayPlayerSplitHandAndScore();
			// Displays instructions
			System.out.println("Hit = '1' | Stand = '2' | Split = '3' | Visual = '5' | Redeal/Play again = '4'");
			// Check for a bust
			checkPlayerBust();
		} else if (split && !secondHand) {
			// Display's dealer's hand and score
			displayDealerHandAndScore();

			dealForPlayer();
			countPlayerScore();
			// Displays player's 1st hand and score
			displayPlayerHandAndScore();
			// Check for a bust
			checkPlayerBust();
			// Displays instructions
			System.out.println("Hit = '1' | Stand = '2' | Split = '3' | Visual = '5' | Redeal/Play again = '4'");
		}
		// Stands when player's scores are equal to 21
		if (split && playerSplitScore == 21) {
			// Automatically stands
			switchCaseStandNumber2();
		} else if (split && playerScore == 21) {
			secondHand = false;
			// Automatically stands
			switchCaseStandNumber2();
		} else if (!split && playerScore == 21) {
			// Automatically stands
			switchCaseStandNumber2();
		}
	}

	// Switch case number 2 (Stand)
	public static void switchCaseStandNumber2() throws InterruptedException {
		// Allows the player to hit or stand on the 2nd hand
		if (split && !secondHand) {
			secondHand = true;
			displayPlayerSplitHandAndScore();
		} // If player does not split
		else {
			// Dealer will take a card if it's score is under 16 or under the players score
			// (minimum score for dealer is 16)
			while (dealerScore < 16 || (dealerScore < playerScore && playerScore <= 21)
					|| (dealerScore < playerSplitScore && playerSplitScore <= 21)) {
				// Deals one card for the dealer then counts the score/displays the score
				dealForDealer();
				countDealerScore();
			}
			// Displays the dealer and player's hands and final score plus who wins
			displayDealerHandAndScore();
			displayPlayerHandAndScore();
			// display's player's 2nd hand only if they split
			if (split) {
				displayPlayerSplitHandAndScore();
			}
			// Makes a visual of the hand
			makeCards();
			// Check to see who wins
			checkFinalScores();
		}
	}

	// Switch case number 3 (Split)
	public static void switchCaseSplitNumber3() {

		splitExtras = playerHand.get(0);
		splitExtras1 = playerHand.get(1);
		try {
			if (activeSplit && (splitExtras.charAt(0) == splitExtras1.charAt(0)
					|| (splitExtras.charAt(0) == '1' && splitExtras1.charAt(0) == 'J')
					|| (splitExtras.charAt(0) == 'J' && splitExtras1.charAt(0) == '1')
					|| (splitExtras.charAt(0) == '1' && splitExtras1.charAt(0) == 'Q')
					|| (splitExtras.charAt(0) == 'Q' && splitExtras1.charAt(0) == '1')
					|| (splitExtras.charAt(0) == '1' && splitExtras1.charAt(0) == 'K')
					|| (splitExtras.charAt(0) == 'K' && splitExtras1.charAt(0) == '1')
					|| (splitExtras.charAt(0) == 'J' && splitExtras1.charAt(0) == 'Q')
					|| (splitExtras.charAt(0) == 'Q' && splitExtras1.charAt(0) == 'J')
					|| (splitExtras.charAt(0) == 'J' && splitExtras1.charAt(0) == 'K')
					|| (splitExtras.charAt(0) == 'K' && splitExtras1.charAt(0) == 'J')
					|| (splitExtras.charAt(0) == 'Q' && splitExtras1.charAt(0) == 'K')
					|| (splitExtras.charAt(0) == 'K' && splitExtras1.charAt(0) == 'Q'))) {
				onSplit();
				// Does not allow another split to happen
				activeSplit = false;
			} // If split is not allowed
			else if (splitExtras.charAt(0) == splitExtras1.charAt(0) && !activeSplit) {
				System.out.println("No Split");
			} else {
				System.out.println("No Split");
			}
		} catch (Exception e) {
			System.out.println("No Split");
		}

	}

	// Resets all variables and starts a new game
	public static void reset() throws InterruptedException {
		// sN = swap number for dealing the cards
		sN = 0;
		// Total player score
		playerScore = 0;
		playerSplitScore = 0;
		// Total dealer score
		dealerScore = 0;

		split = false;
		activeSplit = true;
		secondHand = false;
		play = true;

		// Player's hand/score
		playerHand = new ArrayList<String>();
		playerScoreArray = new ArrayList<Integer>();
		// Player's split hand
		playerHandSplit = new ArrayList<String>();
		playerSplitScoreArray = new ArrayList<Integer>();
		// Dealer's hand/score
		dealerHand = new ArrayList<String>();
		dealerScoreArray = new ArrayList<Integer>();

		// Used in split
		splitExtras = "";
		splitExtras1 = "";

		// Player betting boolean
		betting = true;

		startGame();
	}

	// Creates an actual card representation
	public static void makeCards() throws InterruptedException {
		// Hearts
		String heartAce = "______________" + "\n" + " |A ___   ___  A|" + "\n" + " | /   \\_/   \\  |" + "\n"
				+ " | \\  Hearts /  |" + "\n" + " |  \\       /   |" + "\n" + " |   \\     /    |" + "\n"
				+ " |A   \\___/    A|" + "\n" + " |______________|" + "\n";

		String heart2 = "______________" + "\n" + " |2 ___   ___  2|" + "\n" + " | /   \\_/   \\  |" + "\n"
				+ " | \\  Hearts /  |" + "\n" + " |  \\       /   |" + "\n" + " |   \\     /    |" + "\n"
				+ " |2   \\___/    2|" + "\n" + " |______________|" + "\n";

		String heart3 = "______________" + "\n" + " |3 ___   ___  3|" + "\n" + " | /   \\_/   \\  |" + "\n"
				+ " | \\  Hearts /  |" + "\n" + " |  \\       /   |" + "\n" + " |   \\     /    |" + "\n"
				+ " |3   \\___/    3|" + "\n" + " |______________|" + "\n";

		String heart4 = "______________" + "\n" + " |4 ___   ___  4|" + "\n" + " | /   \\_/   \\  |" + "\n"
				+ " | \\  Hearts /  |" + "\n" + " |  \\       /   |" + "\n" + " |   \\     /    |" + "\n"
				+ " |4   \\___/    4|" + "\n" + " |______________|" + "\n";

		String heart5 = "______________" + "\n" + " |5 ___   ___  5|" + "\n" + " | /   \\_/   \\  |" + "\n"
				+ " | \\  Hearts /  |" + "\n" + " |  \\       /   |" + "\n" + " |   \\     /    |" + "\n"
				+ " |5   \\___/    5|" + "\n" + " |______________|" + "\n";

		String heart6 = "______________" + "\n" + " |6 ___   ___  6|" + "\n" + " | /   \\_/   \\  |" + "\n"
				+ " | \\  Hearts /  |" + "\n" + " |  \\       /   |" + "\n" + " |   \\     /    |" + "\n"
				+ " |6   \\___/    6|" + "\n" + " |______________|" + "\n";

		String heart7 = "______________" + "\n" + " |7 ___   ___  7|" + "\n" + " | /   \\_/   \\  |" + "\n"
				+ " | \\  Hearts /  |" + "\n" + " |  \\       /   |" + "\n" + " |   \\     /    |" + "\n"
				+ " |7   \\___/    7|" + "\n" + " |______________|" + "\n";

		String heart8 = "______________" + "\n" + " |8 ___   ___  8|" + "\n" + " | /   \\_/   \\  |" + "\n"
				+ " | \\  Hearts /  |" + "\n" + " |  \\       /   |" + "\n" + " |   \\     /    |" + "\n"
				+ " |8   \\___/    8|" + "\n" + " |______________|" + "\n";

		String heart9 = "______________" + "\n" + " |9 ___   ___  9|" + "\n" + " | /   \\_/   \\  |" + "\n"
				+ " | \\  Hearts /  |" + "\n" + " |  \\       /   |" + "\n" + " |   \\     /    |" + "\n"
				+ " |9   \\___/    9|" + "\n" + " |______________|" + "\n";

		String heart10 = "______________" + "\n" + " |10___   ___ 10|" + "\n" + " | /   \\_/   \\  |" + "\n"
				+ " | \\  Hearts /  |" + "\n" + " |  \\       /   |" + "\n" + " |   \\     /    |" + "\n"
				+ " |A   \\___/    A|" + "\n" + " |______________|" + "\n";

		String heartJ = "______________" + "\n" + " |J ___   ___  J|" + "\n" + " | /   \\_/   \\  |" + "\n"
				+ " | \\  Hearts /  |" + "\n" + " |  \\       /   |" + "\n" + " |   \\     /    |" + "\n"
				+ " |J   \\___/    J|" + "\n" + " |______________|" + "\n";

		String heartQ = "______________" + "\n" + " |Q ___   ___  Q|" + "\n" + " | /   \\_/   \\  |" + "\n"
				+ " | \\  Hearts /  |" + "\n" + " |  \\       /   |" + "\n" + " |   \\     /    |" + "\n"
				+ " |Q   \\___/    Q|" + "\n" + " |______________|" + "\n";

		String heartK = "______________" + "\n" + " |K ___   ___  K|" + "\n" + " | /   \\_/   \\  |" + "\n"
				+ " | \\  Hearts /  |" + "\n" + " |  \\       /   |" + "\n" + " |   \\     /    |" + "\n"
				+ " |K   \\___/    K|" + "\n" + " |______________|" + "\n";

		// Diamonds A-K
		String diamondAce = "______________" + "\n" + " |A  Diamonds  A|" + "\n" + " |      /\\      |" + "\n"
				+ " |     /  \\     |" + "\n" + " |    /    \\    |" + "\n" + " |    \\    /    |" + "\n"
				+ " |A    \\  /    A|" + "\n" + " |______\\/______|" + "\n";

		String diamond2 = "______________" + "\n" + " |2  Diamonds  2|" + "\n" + " |      /\\      |" + "\n"
				+ " |     /  \\     |" + "\n" + " |    /    \\    |" + "\n" + " |    \\    /    |" + "\n"
				+ " |2    \\  /    2|" + "\n" + " |______\\/______|" + "\n";

		String diamond3 = "______________" + "\n" + " |3  Diamonds  3|" + "\n" + " |      /\\      |" + "\n"
				+ " |     /  \\     |" + "\n" + " |    /    \\    |" + "\n" + " |    \\    /    |" + "\n"
				+ " |3    \\  /    3|" + "\n" + " |______\\/______|" + "\n";

		String diamond4 = "______________" + "\n" + " |4  Diamonds  4|" + "\n" + " |      /\\      |" + "\n"
				+ " |     /  \\     |" + "\n" + " |    /    \\    |" + "\n" + " |    \\    /    |" + "\n"
				+ " |4    \\  /    4|" + "\n" + " |______\\/______|" + "\n";

		String diamond5 = "______________" + "\n" + " |5  Diamonds  5|" + "\n" + " |      /\\      |" + "\n"
				+ " |     /  \\     |" + "\n" + " |    /    \\    |" + "\n" + " |    \\    /    |" + "\n"
				+ " |5    \\  /    5|" + "\n" + " |______\\/______|" + "\n";

		String diamond6 = "______________" + "\n" + " |6  Diamonds  6|" + "\n" + " |      /\\      |" + "\n"
				+ " |     /  \\     |" + "\n" + " |    /    \\    |" + "\n" + " |    \\    /    |" + "\n"
				+ " |6    \\  /    6|" + "\n" + " |______\\/______|" + "\n";

		String diamond7 = "______________" + "\n" + " |7  Diamonds  7|" + "\n" + " |      /\\      |" + "\n"
				+ " |     /  \\     |" + "\n" + " |    /    \\    |" + "\n" + " |    \\    /    |" + "\n"
				+ " |7    \\  /    7|" + "\n" + " |______\\/______|" + "\n";

		String diamond8 = "______________" + "\n" + " |8  Diamonds  8|" + "\n" + " |      /\\      |" + "\n"
				+ " |     /  \\     |" + "\n" + " |    /    \\    |" + "\n" + " |    \\    /    |" + "\n"
				+ " |8    \\  /    8|" + "\n" + " |______\\/______|" + "\n";

		String diamond9 = "______________" + "\n" + " |9  Diamonds  9|" + "\n" + " |      /\\      |" + "\n"
				+ " |     /  \\     |" + "\n" + " |    /    \\    |" + "\n" + " |    \\    /    |" + "\n"
				+ " |9    \\  /    9|" + "\n" + " |______\\/______|" + "\n";

		String diamond10 = "______________" + "\n" + " |10 Diamonds 10|" + "\n" + " |      /\\      |" + "\n"
				+ " |     /  \\     |" + "\n" + " |    /    \\    |" + "\n" + " |    \\    /    |" + "\n"
				+ " |10   \\  /   10|" + "\n" + " |______\\/______|" + "\n";

		String diamondJ = "______________" + "\n" + " |J  Diamonds  J|" + "\n" + " |      /\\      |" + "\n"
				+ " |     /  \\     |" + "\n" + " |    /    \\    |" + "\n" + " |    \\    /    |" + "\n"
				+ " |J    \\  /    J|" + "\n" + " |______\\/______|" + "\n";

		String diamondQ = "______________" + "\n" + " |Q  Diamonds  Q|" + "\n" + " |      /\\      |" + "\n"
				+ " |     /  \\     |" + "\n" + " |    /    \\    |" + "\n" + " |    \\    /    |" + "\n"
				+ " |Q    \\  /    Q|" + "\n" + " |______\\/______|" + "\n";

		String diamondK = "______________" + "\n" + " |K  Diamonds  K|" + "\n" + " |      /\\      |" + "\n"
				+ " |     /  \\     |" + "\n" + " |    /    \\    |" + "\n" + " |    \\    /    |" + "\n"
				+ " |K    \\  /    K|" + "\n" + " |______\\/______|" + "\n";

		// Spades A-K
		String spadeAce = "______________" + "\n" + " |A   Spades   A|" + "\n" + " |      _       |" + "\n"
				+ " |     / \\      |" + "\n" + " |    /   \\     |" + "\n" + " |   /     \\    |" + "\n"
				+ " |A  \\_/|\\_/   A|" + "\n" + " |______|_______|" + "\n";

		String spade2 = "______________" + "\n" + " |2   Spades   2|" + "\n" + " |      _       |" + "\n"
				+ " |     / \\      |" + "\n" + " |    /   \\     |" + "\n" + " |   /     \\    |" + "\n"
				+ " |2  \\_/|\\_/   2|" + "\n" + " |______|_______|" + "\n";

		String spade3 = "______________" + "\n" + " |3   Spades   3|" + "\n" + " |      _       |" + "\n"
				+ " |     / \\      |" + "\n" + " |    /   \\     |" + "\n" + " |   /     \\    |" + "\n"
				+ " |3  \\_/|\\_/   3|" + "\n" + " |______|_______|" + "\n";

		String spade4 = "______________" + "\n" + " |4   Spades   4|" + "\n" + " |      _       |" + "\n"
				+ " |     / \\      |" + "\n" + " |    /   \\     |" + "\n" + " |   /     \\    |" + "\n"
				+ " |4  \\_/|\\_/   4|" + "\n" + " |______|_______|" + "\n";

		String spade5 = "______________" + "\n" + " |5   Spades   5|" + "\n" + " |      _       |" + "\n"
				+ " |     / \\      |" + "\n" + " |    /   \\     |" + "\n" + " |   /     \\    |" + "\n"
				+ " |5  \\_/|\\_/   5|" + "\n" + " |______|_______|" + "\n";

		String spade6 = "______________" + "\n" + " |6   Spades   6|" + "\n" + " |      _       |" + "\n"
				+ " |     / \\      |" + "\n" + " |    /   \\     |" + "\n" + " |   /     \\    |" + "\n"
				+ " |6  \\_/|\\_/   6|" + "\n" + " |______|_______|" + "\n";

		String spade7 = "______________" + "\n" + " |7   Spades   7|" + "\n" + " |      _       |" + "\n"
				+ " |     / \\      |" + "\n" + " |    /   \\     |" + "\n" + " |   /     \\    |" + "\n"
				+ " |7  \\_/|\\_/   7|" + "\n" + " |______|_______|" + "\n";

		String spade8 = "______________" + "\n" + " |8   Spades   8|" + "\n" + " |      _       |" + "\n"
				+ " |     / \\      |" + "\n" + " |    /   \\     |" + "\n" + " |   /     \\    |" + "\n"
				+ " |8  \\_/|\\_/   8|" + "\n" + " |______|_______|" + "\n";

		String spade9 = "______________" + "\n" + " |9   Spades   9|" + "\n" + " |      _       |" + "\n"
				+ " |     / \\      |" + "\n" + " |    /   \\     |" + "\n" + " |   /     \\    |" + "\n"
				+ " |9  \\_/|\\_/   9|" + "\n" + " |______|_______|" + "\n";

		String spade10 = "______________" + "\n" + " |10  Spades  10|" + "\n" + " |      _       |" + "\n"
				+ " |     / \\      |" + "\n" + " |    /   \\     |" + "\n" + " |   /     \\    |" + "\n"
				+ " |10 \\_/|\\_/  10|" + "\n" + " |______|_______|" + "\n";

		String spadeJ = "______________" + "\n" + " |J   Spades   J|" + "\n" + " |      _       |" + "\n"
				+ " |     / \\      |" + "\n" + " |    /   \\     |" + "\n" + " |   /     \\    |" + "\n"
				+ " |J  \\_/|\\_/   J|" + "\n" + " |______|_______|" + "\n";

		String spadeQ = "______________" + "\n" + " |Q   Spades   Q|" + "\n" + " |      _       |" + "\n"
				+ " |     / \\      |" + "\n" + " |    /   \\     |" + "\n" + " |   /     \\    |" + "\n"
				+ " |Q  \\_/|\\_/   Q|" + "\n" + " |______|_______|" + "\n";

		String spadeK = "______________" + "\n" + " |K   Spades   K|" + "\n" + " |      _       |" + "\n"
				+ " |     / \\      |" + "\n" + " |    /   \\     |" + "\n" + " |   /     \\    |" + "\n"
				+ " |K  \\_/|\\_/   K|" + "\n" + " |______|_______|" + "\n";

		// Clubs A-K
		String clubAce = "______________" + "\n" + " |A     __     A|" + "\n" + " |  _  /  \\  _  |" + "\n"
				+ " | / \\/    \\/ \\ |" + "\n" + " | \\  Clubs   / |" + "\n" + " |  \\__/||\\__/  |" + "\n"
				+ " |A     ||     A|" + "\n" + " |______________|" + "\n";

		String club2 = "______________" + "\n" + " |2     __     2|" + "\n" + " |  _  /  \\  _  |" + "\n"
				+ " | / \\/    \\/ \\ |" + "\n" + " | \\  Clubs   / |" + "\n" + " |  \\__/||\\__/  |" + "\n"
				+ " |2     ||     2|" + "\n" + " |______________|" + "\n";

		String club3 = "______________" + "\n" + " |3     __     3|" + "\n" + " |  _  /  \\  _  |" + "\n"
				+ " | / \\/    \\/ \\ |" + "\n" + " | \\  Clubs   / |" + "\n" + " |  \\__/||\\__/  |" + "\n"
				+ " |3     ||     3|" + "\n" + " |______________|" + "\n";

		String club4 = "______________" + "\n" + " |4     __     4|" + "\n" + " |  _  /  \\  _  |" + "\n"
				+ " | / \\/    \\/ \\ |" + "\n" + " | \\  Clubs   / |" + "\n" + " |  \\__/||\\__/  |" + "\n"
				+ " |4     ||     4|" + "\n" + " |______________|" + "\n";

		String club5 = "______________" + "\n" + " |5     __     5|" + "\n" + " |  _  /  \\  _  |" + "\n"
				+ " | / \\/    \\/ \\ |" + "\n" + " | \\  Clubs   / |" + "\n" + " |  \\__/||\\__/  |" + "\n"
				+ " |5     ||     5|" + "\n" + " |______________|" + "\n";

		String club6 = "______________" + "\n" + " |6     __     6|" + "\n" + " |  _  /  \\  _  |" + "\n"
				+ " | / \\/    \\/ \\ |" + "\n" + " | \\  Clubs   / |" + "\n" + " |  \\__/||\\__/  |" + "\n"
				+ " |6     ||     6|" + "\n" + " |______________|" + "\n";

		String club7 = "______________" + "\n" + " |7     __     7|" + "\n" + " |  _  /  \\  _  |" + "\n"
				+ " | / \\/    \\/ \\ |" + "\n" + " | \\  Clubs   / |" + "\n" + " |  \\__/||\\__/  |" + "\n"
				+ " |7     ||     7|" + "\n" + " |______________|" + "\n";

		String club8 = "______________" + "\n" + " |8     __     8|" + "\n" + " |  _  /  \\  _  |" + "\n"
				+ " | / \\/    \\/ \\ |" + "\n" + " | \\  Clubs   / |" + "\n" + " |  \\__/||\\__/  |" + "\n"
				+ " |8     ||     8|" + "\n" + " |______________|" + "\n";

		String club9 = "______________" + "\n" + " |9     __     9|" + "\n" + " |  _  /  \\  _  |" + "\n"
				+ " | / \\/    \\/ \\ |" + "\n" + " | \\  Clubs   / |" + "\n" + " |  \\__/||\\__/  |" + "\n"
				+ " |9     ||     9|" + "\n" + " |______________|" + "\n";

		String club10 = "______________" + "\n" + " |10    __    10|" + "\n" + " |  _  /  \\  _  |" + "\n"
				+ " | / \\/    \\/ \\ |" + "\n" + " | \\  Clubs   / |" + "\n" + " |  \\__/||\\__/  |" + "\n"
				+ " |10    ||    10|" + "\n" + " |______________|" + "\n";

		String clubJ = "______________" + "\n" + " |J     __     J|" + "\n" + " |  _  /  \\  _  |" + "\n"
				+ " | / \\/    \\/ \\ |" + "\n" + " | \\  Clubs   / |" + "\n" + " |  \\__/||\\__/  |" + "\n"
				+ " |J     ||     J|" + "\n" + " |______________|" + "\n";

		String clubQ = "______________" + "\n" + " |Q     __     Q|" + "\n" + " |  _  /  \\  _  |" + "\n"
				+ " | / \\/    \\/ \\ |" + "\n" + " | \\  Clubs   / |" + "\n" + " |  \\__/||\\__/  |" + "\n"
				+ " |Q     ||     Q|" + "\n" + " |______________|" + "\n";

		String clubK = "______________" + "\n" + " |K     __     K|" + "\n" + " |  _  /  \\  _  |" + "\n"
				+ " | / \\/    \\/ \\ |" + "\n" + " | \\  Clubs   / |" + "\n" + " |  \\__/||\\__/  |" + "\n"
				+ " |K     ||     K|" + "\n" + " |______________|" + "\n";

		if (playerHand.contains("A Spades")) {
			displayCardsPlayer.add(spadeAce);
		}
		if (playerHand.contains("2 Spades")) {
			displayCardsPlayer.add(spade2);
		}
		if (playerHand.contains("3 Spades")) {
			displayCardsPlayer.add(spade3);
		}
		if (playerHand.contains("4 Spades")) {
			displayCardsPlayer.add(spade4);
		}
		if (playerHand.contains("5 Spades")) {
			displayCardsPlayer.add(spade5);
		}
		if (playerHand.contains("6 Spades")) {
			displayCardsPlayer.add(spade6);
		}
		if (playerHand.contains("7 Spades")) {
			displayCardsPlayer.add(spade7);
		}
		if (playerHand.contains("8 Spades")) {
			displayCardsPlayer.add(spade8);
		}
		if (playerHand.contains("9 Spades")) {
			displayCardsPlayer.add(spade9);
		}
		if (playerHand.contains("10 Spades")) {
			displayCardsPlayer.add(spade10);
		}
		if (playerHand.contains("J Spades")) {
			displayCardsPlayer.add(spadeJ);
		}
		if (playerHand.contains("Q Spades")) {
			displayCardsPlayer.add(spadeQ);
		}
		if (playerHand.contains("K Spades")) {
			displayCardsPlayer.add(spadeK);
		}
		// Diamonds
		if (playerHand.contains("A Diamonds")) {
			displayCardsPlayer.add(diamondAce);
		}
		if (playerHand.contains("2 Diamonds")) {
			displayCardsPlayer.add(diamond2);
		}
		if (playerHand.contains("3 Diamonds")) {
			displayCardsPlayer.add(diamond3);
		}
		if (playerHand.contains("4 Diamonds")) {
			displayCardsPlayer.add(diamond4);
		}
		if (playerHand.contains("5 Diamonds")) {
			displayCardsPlayer.add(diamond5);
		}
		if (playerHand.contains("6 Diamonds")) {
			displayCardsPlayer.add(diamond6);
		}
		if (playerHand.contains("7 Diamonds")) {
			displayCardsPlayer.add(diamond7);
		}
		if (playerHand.contains("8 Diamonds")) {
			displayCardsPlayer.add(diamond8);
		}
		if (playerHand.contains("9 Diamonds")) {
			displayCardsPlayer.add(diamond9);
		}
		if (playerHand.contains("10 Diamonds")) {
			displayCardsPlayer.add(diamond10);
		}
		if (playerHand.contains("J Diamonds")) {
			displayCardsPlayer.add(diamondJ);
		}
		if (playerHand.contains("Q Diamonds")) {
			displayCardsPlayer.add(diamondQ);
		}
		if (playerHand.contains("K Diamonds")) {
			displayCardsPlayer.add(diamondK);
		}
		// Clubs
		if (playerHand.contains("A Clubs")) {
			displayCardsPlayer.add(clubAce);
		}
		if (playerHand.contains("2 Clubs")) {
			displayCardsPlayer.add(club2);
		}
		if (playerHand.contains("3 Clubs")) {
			displayCardsPlayer.add(club3);
		}
		if (playerHand.contains("4 Clubs")) {
			displayCardsPlayer.add(club4);
		}
		if (playerHand.contains("5 Clubs")) {
			displayCardsPlayer.add(club5);
		}
		if (playerHand.contains("6 Clubs")) {
			displayCardsPlayer.add(club6);
		}
		if (playerHand.contains("7 Clubs")) {
			displayCardsPlayer.add(club7);
		}
		if (playerHand.contains("8 Clubs")) {
			displayCardsPlayer.add(club8);
		}
		if (playerHand.contains("9 Clubs")) {
			displayCardsPlayer.add(club9);
		}
		if (playerHand.contains("10 Clubs")) {
			displayCardsPlayer.add(club10);
		}
		if (playerHand.contains("J Clubs")) {
			displayCardsPlayer.add(clubJ);
		}
		if (playerHand.contains("Q Clubs")) {
			displayCardsPlayer.add(clubQ);
		}
		if (playerHand.contains("K Clubs")) {
			displayCardsPlayer.add(clubK);
		}
		// Hearts
		if (playerHand.contains("A Hearts")) {
			displayCardsPlayer.add(heartAce);
		}
		if (playerHand.contains("2 Hearts")) {
			displayCardsPlayer.add(heart2);
		}
		if (playerHand.contains("3 Hearts")) {
			displayCardsPlayer.add(heart3);
		}
		if (playerHand.contains("4 Hearts")) {
			displayCardsPlayer.add(heart4);
		}
		if (playerHand.contains("5 Hearts")) {
			displayCardsPlayer.add(heart5);
		}
		if (playerHand.contains("6 Hearts")) {
			displayCardsPlayer.add(heart6);
		}
		if (playerHand.contains("7 Hearts")) {
			displayCardsPlayer.add(heart7);
		}
		if (playerHand.contains("8 Hearts")) {
			displayCardsPlayer.add(heart8);
		}
		if (playerHand.contains("9 Hearts")) {
			displayCardsPlayer.add(heart9);
		}
		if (playerHand.contains("10 Hearts")) {
			displayCardsPlayer.add(heart10);
		}
		if (playerHand.contains("J Hearts")) {
			displayCardsPlayer.add(heartJ);
		}
		if (playerHand.contains("Q Hearts")) {
			displayCardsPlayer.add(heartQ);
		}
		if (playerHand.contains("K Hearts")) {
			displayCardsPlayer.add(heartK);
		}

		// Dealer
		// Spades
		if (dealerHand.contains("A Spades")) {
			displayCardsDealer.add(spadeAce);
		}
		if (dealerHand.contains("2 Spades")) {
			displayCardsDealer.add(spade2);
		}
		if (dealerHand.contains("3 Spades")) {
			displayCardsDealer.add(spade3);
		}
		if (dealerHand.contains("4 Spades")) {
			displayCardsDealer.add(spade4);
		}
		if (dealerHand.contains("5 Spades")) {
			displayCardsDealer.add(spade5);
		}
		if (dealerHand.contains("6 Spades")) {
			displayCardsDealer.add(spade6);
		}
		if (dealerHand.contains("7 Spades")) {
			displayCardsDealer.add(spade7);
		}
		if (dealerHand.contains("8 Spades")) {
			displayCardsDealer.add(spade8);
		}
		if (dealerHand.contains("9 Spades")) {
			displayCardsDealer.add(spade9);
		}
		if (dealerHand.contains("10 Spades")) {
			displayCardsDealer.add(spade10);
		}
		if (dealerHand.contains("J Spades")) {
			displayCardsDealer.add(spadeJ);
		}
		if (dealerHand.contains("Q Spades")) {
			displayCardsDealer.add(spadeQ);
		}
		if (dealerHand.contains("K Spades")) {
			displayCardsDealer.add(spadeK);
		}
		// Diamonds
		if (dealerHand.contains("A Diamonds")) {
			displayCardsDealer.add(diamondAce);
		}
		if (dealerHand.contains("2 Diamonds")) {
			displayCardsDealer.add(diamond2);
		}
		if (dealerHand.contains("3 Diamonds")) {
			displayCardsDealer.add(diamond3);
		}
		if (dealerHand.contains("4 Diamonds")) {
			displayCardsDealer.add(diamond4);
		}
		if (dealerHand.contains("5 Diamonds")) {
			displayCardsDealer.add(diamond5);
		}
		if (dealerHand.contains("6 Diamonds")) {
			displayCardsDealer.add(diamond6);
		}
		if (dealerHand.contains("7 Diamonds")) {
			displayCardsDealer.add(diamond7);
		}
		if (dealerHand.contains("8 Diamonds")) {
			displayCardsDealer.add(diamond8);
		}
		if (dealerHand.contains("9 Diamonds")) {
			displayCardsDealer.add(diamond9);
		}
		if (dealerHand.contains("10 Diamonds")) {
			displayCardsDealer.add(diamond10);
		}
		if (dealerHand.contains("J Diamonds")) {
			displayCardsDealer.add(diamondJ);
		}
		if (dealerHand.contains("Q Diamonds")) {
			displayCardsDealer.add(diamondQ);
		}
		if (dealerHand.contains("K Diamonds")) {
			displayCardsDealer.add(diamondK);
		}
		// Clubs
		if (dealerHand.contains("A Clubs")) {
			displayCardsDealer.add(clubAce);
		}
		if (dealerHand.contains("2 Clubs")) {
			displayCardsDealer.add(club2);
		}
		if (dealerHand.contains("3 Clubs")) {
			displayCardsDealer.add(club3);
		}
		if (dealerHand.contains("4 Clubs")) {
			displayCardsDealer.add(club4);
		}
		if (dealerHand.contains("5 Clubs")) {
			displayCardsDealer.add(club5);
		}
		if (dealerHand.contains("6 Clubs")) {
			displayCardsDealer.add(club6);
		}
		if (dealerHand.contains("7 Clubs")) {
			displayCardsDealer.add(club7);
		}
		if (dealerHand.contains("8 Clubs")) {
			displayCardsDealer.add(club8);
		}
		if (dealerHand.contains("9 Clubs")) {
			displayCardsDealer.add(club9);
		}
		if (dealerHand.contains("10 Clubs")) {
			displayCardsDealer.add(club10);
		}
		if (dealerHand.contains("J Clubs")) {
			displayCardsDealer.add(clubJ);
		}
		if (dealerHand.contains("Q Clubs")) {
			displayCardsDealer.add(clubQ);
		}
		if (dealerHand.contains("K Clubs")) {
			displayCardsDealer.add(clubK);
		}
		// Hearts
		if (dealerHand.contains("A Hearts")) {
			displayCardsDealer.add(heartAce);
		}
		if (dealerHand.contains("2 Hearts")) {
			displayCardsDealer.add(heart2);
		}
		if (dealerHand.contains("3 Hearts")) {
			displayCardsDealer.add(heart3);
		}
		if (dealerHand.contains("4 Hearts")) {
			displayCardsDealer.add(heart4);
		}
		if (dealerHand.contains("5 Hearts")) {
			displayCardsDealer.add(heart5);
		}
		if (dealerHand.contains("6 Hearts")) {
			displayCardsDealer.add(heart6);
		}
		if (dealerHand.contains("7 Hearts")) {
			displayCardsDealer.add(heart7);
		}
		if (dealerHand.contains("8 Hearts")) {
			displayCardsDealer.add(heart8);
		}
		if (dealerHand.contains("9 Hearts")) {
			displayCardsDealer.add(heart9);
		}
		if (dealerHand.contains("10 Hearts")) {
			displayCardsDealer.add(heart10);
		}
		if (dealerHand.contains("J Hearts")) {
			displayCardsDealer.add(heartJ);
		}
		if (dealerHand.contains("Q Hearts")) {
			displayCardsDealer.add(heartQ);
		}
		if (dealerHand.contains("K Hearts")) {
			displayCardsDealer.add(heartK);
		}

		// Player's 2nd hand
		// Spades
		if (playerHandSplit.contains("A Spades")) {
			displayCardsPlayer2nd.add(spadeAce);
		}
		if (playerHandSplit.contains("2 Spades")) {
			displayCardsPlayer2nd.add(spade2);
		}
		if (playerHandSplit.contains("3 Spades")) {
			displayCardsPlayer2nd.add(spade3);
		}
		if (playerHandSplit.contains("4 Spades")) {
			displayCardsPlayer2nd.add(spade4);
		}
		if (playerHandSplit.contains("5 Spades")) {
			displayCardsPlayer2nd.add(spade5);
		}
		if (playerHandSplit.contains("6 Spades")) {
			displayCardsPlayer2nd.add(spade6);
		}
		if (playerHandSplit.contains("7 Spades")) {
			displayCardsPlayer2nd.add(spade7);
		}
		if (playerHandSplit.contains("8 Spades")) {
			displayCardsPlayer2nd.add(spade8);
		}
		if (playerHandSplit.contains("9 Spades")) {
			displayCardsPlayer2nd.add(spade9);
		}
		if (playerHandSplit.contains("10 Spades")) {
			displayCardsPlayer2nd.add(spade10);
		}
		if (playerHandSplit.contains("J Spades")) {
			displayCardsPlayer2nd.add(spadeJ);
		}
		if (playerHandSplit.contains("Q Spades")) {
			displayCardsPlayer2nd.add(spadeQ);
		}
		if (playerHandSplit.contains("K Spades")) {
			displayCardsPlayer2nd.add(spadeK);
		}
		// Diamonds
		if (playerHandSplit.contains("A Diamonds")) {
			displayCardsPlayer2nd.add(diamondAce);
		}
		if (playerHandSplit.contains("2 Diamonds")) {
			displayCardsPlayer2nd.add(diamond2);
		}
		if (playerHandSplit.contains("3 Diamonds")) {
			displayCardsPlayer2nd.add(diamond3);
		}
		if (playerHandSplit.contains("4 Diamonds")) {
			displayCardsPlayer2nd.add(diamond4);
		}
		if (playerHandSplit.contains("5 Diamonds")) {
			displayCardsPlayer2nd.add(diamond5);
		}
		if (playerHandSplit.contains("6 Diamonds")) {
			displayCardsPlayer2nd.add(diamond6);
		}
		if (playerHandSplit.contains("7 Diamonds")) {
			displayCardsPlayer2nd.add(diamond7);
		}
		if (playerHandSplit.contains("8 Diamonds")) {
			displayCardsPlayer2nd.add(diamond8);
		}
		if (playerHandSplit.contains("9 Diamonds")) {
			displayCardsPlayer2nd.add(diamond9);
		}
		if (playerHandSplit.contains("10 Diamonds")) {
			displayCardsPlayer2nd.add(diamond10);
		}
		if (playerHandSplit.contains("J Diamonds")) {
			displayCardsPlayer2nd.add(diamondJ);
		}
		if (playerHandSplit.contains("Q Diamonds")) {
			displayCardsPlayer2nd.add(diamondQ);
		}
		if (playerHandSplit.contains("K Diamonds")) {
			displayCardsPlayer2nd.add(diamondK);
		}
		// Clubs
		if (playerHandSplit.contains("A Clubs")) {
			displayCardsPlayer2nd.add(clubAce);
		}
		if (playerHandSplit.contains("2 Clubs")) {
			displayCardsPlayer2nd.add(club2);
		}
		if (playerHandSplit.contains("3 Clubs")) {
			displayCardsPlayer2nd.add(club3);
		}
		if (playerHandSplit.contains("4 Clubs")) {
			displayCardsPlayer2nd.add(club4);
		}
		if (playerHandSplit.contains("5 Clubs")) {
			displayCardsPlayer2nd.add(club5);
		}
		if (playerHandSplit.contains("6 Clubs")) {
			displayCardsPlayer2nd.add(club6);
		}
		if (playerHandSplit.contains("7 Clubs")) {
			displayCardsPlayer2nd.add(club7);
		}
		if (playerHandSplit.contains("8 Clubs")) {
			displayCardsPlayer2nd.add(club8);
		}
		if (playerHandSplit.contains("9 Clubs")) {
			displayCardsPlayer2nd.add(club9);
		}
		if (playerHandSplit.contains("10 Clubs")) {
			displayCardsPlayer2nd.add(club10);
		}
		if (playerHandSplit.contains("J Clubs")) {
			displayCardsPlayer2nd.add(clubJ);
		}
		if (playerHandSplit.contains("Q Clubs")) {
			displayCardsPlayer2nd.add(clubQ);
		}
		if (playerHandSplit.contains("K Clubs")) {
			displayCardsPlayer2nd.add(clubK);
		}
		// Hearts
		if (playerHandSplit.contains("A Hearts")) {
			displayCardsPlayer2nd.add(heartAce);
		}
		if (playerHandSplit.contains("2 Hearts")) {
			displayCardsPlayer2nd.add(heart2);
		}
		if (playerHandSplit.contains("3 Hearts")) {
			displayCardsPlayer2nd.add(heart3);
		}
		if (playerHandSplit.contains("4 Hearts")) {
			displayCardsPlayer2nd.add(heart4);
		}
		if (playerHandSplit.contains("5 Hearts")) {
			displayCardsPlayer2nd.add(heart5);
		}
		if (playerHandSplit.contains("6 Hearts")) {
			displayCardsPlayer2nd.add(heart6);
		}
		if (playerHandSplit.contains("7 Hearts")) {
			displayCardsPlayer2nd.add(heart7);
		}
		if (playerHandSplit.contains("8 Hearts")) {
			displayCardsPlayer2nd.add(heart8);
		}
		if (playerHandSplit.contains("9 Hearts")) {
			displayCardsPlayer2nd.add(heart9);
		}
		if (playerHandSplit.contains("10 Hearts")) {
			displayCardsPlayer2nd.add(heart10);
		}
		if (playerHandSplit.contains("J Hearts")) {
			displayCardsPlayer2nd.add(heartJ);
		}
		if (playerHandSplit.contains("Q Hearts")) {
			displayCardsPlayer2nd.add(heartQ);
		}
		if (playerHandSplit.contains("K Hearts")) {
			displayCardsPlayer2nd.add(heartK);
		}
		if (split) {
			System.out.println("Dealer's hand:");
			System.out.println(displayCardsDealer + "\n");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Player's 2nd hand:");
			System.out.println(displayCardsPlayer2nd + "\n");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Player's hand:");
			System.out.println(displayCardsPlayer + "\n");
		} else {
			System.out.println("Dealer's hand:");
			System.out.println(displayCardsDealer + "\n");
			TimeUnit.SECONDS.sleep(1);
			System.out.println("Player's hand:");
			System.out.println(displayCardsPlayer + "\n");
		}
		if (visual && !split) {
			System.out.println("Dealer's score: " + dealerScore);
			System.out.println("Player's score: " + playerScore);
		} else if (visual && split) {
			System.out.println("Dealer's score: " + dealerScore);
			System.out.println("Player's 2nd hand score: " + playerSplitScore);
			System.out.println("Player's score: " + playerScore);
		}

		// Reset visuals
		// Create visuals for the hands
		displayCardsPlayer = new ArrayList<String>();
		displayCardsPlayer2nd = new ArrayList<String>();
		displayCardsDealer = new ArrayList<String>();

		visual = false;
	}

	// Calculates how long the player has played
	public static String timePlayed(int startTime) {
		// Calculates how long you have played
		int endTime = LocalTime.now().toSecondOfDay();
		int elapsedTimeSecond = ((endTime - startTime) < 60) ? (endTime - startTime) : ((endTime - startTime) % 60);
		int elapsedTimeMin = (endTime - startTime) / 60;
		String timeSecond = (elapsedTimeSecond < 10) ? "0" + elapsedTimeSecond : elapsedTimeSecond + "";
		String timePlayedMin = elapsedTimeMin + ":" + timeSecond;

		return timePlayedMin;
	}

	// Allows the player to bet
	public static void placeBet() {
		// Display's how much money you own
		System.out.println("Your money: $" + moneyOwned);
		while (betting) {
			System.out.print("Place your bet: ");
			int betMoney = bet.nextInt();
			storeBet = betMoney;
			if (betMoney > moneyOwned) {
				System.out.println("Invalid bet. You don't own that much money!");

			} else {
				moneyOwned -= betMoney;

				System.out.println("Your bet: $" + betMoney + "|" + "You own: $" + moneyOwned + "\n");

				betting = false;
			}
		}
	}

	// Game ends if player has no money
	public static void gameOver() throws InterruptedException {
		if (moneyOwned == 0) {
			System.out.println(
					" _ _ _ _ _ _ _         _         _           _   _ _ _ _ _ _ _       _ _ _ _ _ _ _   _           _   _ _ _ _ _ _ _   _ _ _ _ _ _ ");
			TimeUnit.MILLISECONDS.sleep(500);
			System.out.println(
					"|_|_|_|_|_|_|_|      _|_|_      |_|_       _|_| |_|_|_|_|_|_|_|     |_|_|_|_|_|_|_| |_|         |_| |_|_|_|_|_|_|_| |_|_|_|_|_|_|_ ");
			TimeUnit.MILLISECONDS.sleep(500);
			System.out.println(
					"|_|                _|_| |_|_    |_|_|_   _|_|_| |_|                 |_|         |_| |_|_       _|_| |_|             |_|         |_| ");
			TimeUnit.MILLISECONDS.sleep(500);
			System.out.println(
					"|_|      _ _ _   _|_|_ _ _|_|_  |_| |_|_|_| |_| |_|_ _ _ _ _        |_|         |_|   |_|     |_|   |_|_ _ _ _ _    |_|_ _ _ _ _|_| ");
			TimeUnit.MILLISECONDS.sleep(500);
			System.out.println(
					"|_|     |_|_|_| |_|_|_|_|_|_|_| |_|   |_|   |_| |_|_|_|_|_|_|       |_|         |_|   |_|_   _|_|   |_|_|_|_|_|_|   |_|_|_|_|_|_|");
			TimeUnit.MILLISECONDS.sleep(500);
			System.out.println(
					"|_|         |_| |_|         |_| |_|         |_| |_|                 |_|         |_|     |_| |_|     |_|             |_|_|_|_ _ ");
			TimeUnit.MILLISECONDS.sleep(500);
			System.out.println(
					"|_|_ _ _ _ _|_| |_|         |_| |_|         |_| |_|_ _ _ _ _ _      |_|_ _ _ _ _|_|     |_|_|_|     |_|_ _ _ _ _ _  |_|   |_|_|_ _ ");
			TimeUnit.MILLISECONDS.sleep(500);
			System.out.println(
					"|_|_|_|_|_|_|_| |_|         |_| |_|         |_| |_|_|_|_|_|_|_|     |_|_|_|_|_|_|_|       |_|       |_|_|_|_|_|_|_| |_|       |_|_|");
			TimeUnit.MILLISECONDS.sleep(500);
			// Creates random messages at the end
			String[] messages = { "Better luck next time :(", "You went bankrupt :(", "You can play again now",
					"There's always next time :)", "*Bots for the win*", "Game Over", "............." };
			Random rand = new Random();
			System.out.println(messages[rand.nextInt(7)]);
			playWithBet = false;
		}

	}

	// Tic-tac-toe Methods ahead
	public static void ticTacToe() throws InterruptedException {
		// Tic-tac-toe
		ArrayList<Integer> playerPositions = new ArrayList<Integer>();
		ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
		// Creates game board.
		char[][] gameBoard = { { '1', '|', '2', '|', '3' }, { '-', '+', '-', '+', '-' }, { '4', '|', '5', '|', '6' },
				{ '-', '+', '-', '+', '-' }, { '7', '|', '8', '|', '9' } };

		play = true;

		// TimeUnit adds more drama
		System.out.println("*|*|*|*|*|*|*|*|*|*|*|*");
		System.out.print("Welcome ");
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.print("to ");
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.print("Tic");
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.print("-");
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.print("Tac");
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.print("-");
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("Toe!");
		System.out.println("*|*|*|*|*|*|*|*|*|*|*|*");
		TimeUnit.MILLISECONDS.sleep(500);

		// Prints game board.
		printGameBoard(gameBoard);

		// Allows player and computer to place a piece.
		while (play) {

			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);

			System.out.println("Enter your placement (1-9) | quit = '0':");
			int playerPos = scan.nextInt();
			while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos) || playerPos > 9) {
				System.out.println("Position taken or invalid entry, choose again.");
				playerPos = scan.nextInt();
			}
			placePiece(playerPos, "player", gameBoard, playerPositions, cpuPositions);

			String result = checkWinner(playerPositions, cpuPositions);
			System.out.println(result);

			Random rand = new Random();
			int cpuPos = 0;
			// Makes the cpu slightly better
			if ((gameBoard[0][2] == 'X' && gameBoard[0][4] == 'X') || (gameBoard[2][2] == 'X' && gameBoard[4][4] == 'X')
					|| (gameBoard[2][0] == 'X' && gameBoard[4][0] == 'X')) {
				cpuPos = 1;
			} else if ((gameBoard[0][0] == 'X' && gameBoard[0][4] == 'X')
					|| (gameBoard[2][2] == 'X' && gameBoard[4][2] == 'X')) {
				cpuPos = 2;
			} else if ((gameBoard[0][0] == 'X' && gameBoard[0][2] == 'X')
					|| (gameBoard[2][2] == 'X' && gameBoard[4][0] == 'X')
					|| (gameBoard[4][4] == 'X' && gameBoard[2][4] == 'X')) {
				cpuPos = 3;
			} else if ((gameBoard[0][0] == 'X' && gameBoard[4][0] == 'X')
					|| (gameBoard[2][2] == 'X' && gameBoard[2][4] == 'X')) {
				cpuPos = 4;
			} else if ((gameBoard[0][2] == 'X' && gameBoard[4][2] == 'X')
					|| (gameBoard[2][0] == 'X' && gameBoard[2][4] == 'X')
					|| (gameBoard[4][0] == 'X' && gameBoard[0][4] == 'X')
					|| (gameBoard[0][0] == 'X' && gameBoard[4][4] == 'X')) {
				cpuPos = 5;
			} else if ((gameBoard[2][0] == 'X' && gameBoard[2][2] == 'X')
					|| (gameBoard[0][4] == 'X' && gameBoard[4][4] == 'X')) {
				cpuPos = 6;
			} else if ((gameBoard[0][0] == 'X' && gameBoard[2][0] == 'X')
					|| (gameBoard[2][2] == 'X' && gameBoard[0][4] == 'X')
					|| (gameBoard[4][2] == 'X' && gameBoard[4][4] == 'X')) {
				cpuPos = 7;
			} else if ((gameBoard[0][2] == 'X' && gameBoard[2][2] == 'X')
					|| (gameBoard[4][0] == 'X' && gameBoard[4][4] == 'X')) {
				cpuPos = 8;
			} else if ((gameBoard[0][0] == 'X' && gameBoard[2][2] == 'X')
					|| (gameBoard[2][4] == 'X' && gameBoard[0][4] == 'X')
					|| (gameBoard[4][0] == 'X' && gameBoard[4][2] == 'X')) {
				cpuPos = 9;
			} else {
				cpuPos = rand.nextInt(9) + 1;
			}
			while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
				cpuPos = rand.nextInt(9) + 1;
			}
			placePiece(cpuPos, "cpu", gameBoard, playerPositions, cpuPositions);

			printGameBoard(gameBoard);

			result = checkWinner(playerPositions, cpuPositions);
			System.out.println(result);
		}
	}

	// Prints game board
	public static void printGameBoard(char[][] gameBoard) {
		for (char[] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	// Allows user and CPU to place pieces
	public static void placePiece(int pos, String user, char[][] gameBoard, ArrayList<Integer> playerPositions,
			ArrayList<Integer> cpuPositions) {
		char symbol = ' ';
		if (user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		} else if (user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		switch (pos) {
		case 1:
			gameBoard[0][0] = symbol;
			break;
		case 2:
			gameBoard[0][2] = symbol;
			break;
		case 3:
			gameBoard[0][4] = symbol;
			break;
		case 4:
			gameBoard[2][0] = symbol;
			break;
		case 5:
			gameBoard[2][2] = symbol;
			break;
		case 6:
			gameBoard[2][4] = symbol;
			break;
		case 7:
			gameBoard[4][0] = symbol;
			break;
		case 8:
			gameBoard[4][2] = symbol;
			break;
		case 9:
			gameBoard[4][4] = symbol;
			break;
		case 0:
			play = false;
			break;
		default:
			break;
		}
	}

	// Check for a winner
	public static String checkWinner(ArrayList<Integer> playerPositions, ArrayList<Integer> cpuPositions) {

		List<Integer> topRow = Arrays.asList(1, 2, 3);
		List<Integer> midRow = Arrays.asList(4, 5, 6);
		List<Integer> botRow = Arrays.asList(7, 8, 9);
		List<Integer> leftCol = Arrays.asList(1, 4, 7);
		List<Integer> midCol = Arrays.asList(2, 5, 8);
		List<Integer> rightCol = Arrays.asList(3, 6, 9);
		List<Integer> cross1 = Arrays.asList(1, 5, 9);
		List<Integer> cross2 = Arrays.asList(3, 5, 7);

		@SuppressWarnings("rawtypes")
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(rightCol);
		winning.add(midCol);
		winning.add(cross1);
		winning.add(cross2);
		for (@SuppressWarnings("rawtypes")
		List l : winning) {
			if (playerPositions.containsAll(l) || (playerPositions.containsAll(l) && cpuPositions.containsAll(l))) {
				play = false;
				return "Congratulations you won!";
			} else if (cpuPositions.containsAll(l)) {
				play = false;
				return "You lost!";
			} else if (cpuPositions.size() + playerPositions.size() == 8) {
				play = false;
				return "No loss, no gain";
			}
		}
		return "";
	}

	// Everything that runs the game is here
	public static void playGame() throws InterruptedException {
		// Deals 2 cards each and checks for a potential blackjack
		startGame();

		// Starts the timer for how long you have played
		int startTime = LocalTime.now().toSecondOfDay();
		while (playWithBet) {
			if (play) {
				int hitStandSplit = scan.nextInt();
				switch (hitStandSplit) {
				// Hit
				case 1:
					switchCaseHitNumber1();
					break;
				// Stand
				case 2:
					switchCaseStandNumber2();
					break;
				// Split
				case 3:
					switchCaseSplitNumber3();
					break;
				// Reset/play again
				case 4:
					moneyOwned += storeBet;
					reset();
					break;
				// Create a visual of the hands
				case 5:
					visual = true;
					makeCards();
					break;
				default:
					break;
				}
			} else if (!play) {
				System.out.println("---------------------------------------- \n");

				System.out.println("You played for " + timePlayed(startTime) + " mins \n");

				System.out.println("To play again click '4' or 2(3 x 2 - 4) + (112 - 111)");

				int hitStandSplit = scan.nextInt();
				switch (hitStandSplit) {
				// Reset/play again
				case 4:
					reset();
					break;
				// Play tic-tac-toe
				case 5:
					// Enter a password (Goated) to play tic tac toe
					Scanner scan = new Scanner(System.in);
					System.out.print("Enter password: ");
					String password = scan.next();

					char[] decode = password.toCharArray();

					int charCount = 0;

					if (decode[0] == 'G') {
						charCount++;
					} else {
						System.out.println("Wrong \n");
						reset();
					}
					if (decode[1] == 'o') {
						charCount++;
					} else {
						System.out.println("Wrong \n");
						reset();
					}
					if (decode[2] == 'a') {
						charCount++;
					} else {
						System.out.println("Wrong \n");
						reset();
					}
					if (decode[3] == 't') {
						charCount++;
					} else {
						System.out.println("Wrong \n");
						reset();
					}
					if (decode[4] == 'e') {
						charCount++;
					} else {
						System.out.println("Wrong \n");
						reset();
					}
					if (decode[5] == 'd') {
						charCount++;
					} else {
						System.out.println("Wrong \n");
						reset();
					}

					if (charCount == 6) {
						ticTacToe();
					} else {
						System.out.println("Wrong \n");
						reset();
					}
					break;
				default:
					break;
				}
			}
		}
	}
}
