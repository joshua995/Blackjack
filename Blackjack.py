"""
Joshua Liu
2022
Blackjack
"""

from random import shuffle

HIT_INPUT, STAND_INPUT, SPLIT_INPUT = "1", "2", "3"

NUMS = ["A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"]
SUITS = ["Hearts", "Spades", "Clubs", "Diamonds"]

BASIC_INSTRUCTIONS = "Press '1' (hit) | '2' (stand)"

BLACKJACK_SCORE, DEALER_HIT_LIMIT = 21, 16

deck, dealerHand, playerHand, playerSplitHand = [], [], [], []

money, bet, gamesPlayed = 3000, 0, 0

canSplit, hasSplit, playWithSplitHand, dealerTurn, play = False, False, False, False, True


def has_letters(input_string):
    return any(char.isalpha() for char in input_string)


def create_deck(amountOfDecks):
    [[[deck.append(num + "-" + suit) for num in NUMS] for suit in SUITS] for _ in range(amountOfDecks)]
    shuffle(deck)


def store_scores(scores, card):
    if card[0] == NUMS[10] or card[0] == NUMS[11] or card[0] == NUMS[12] or card[:2] == NUMS[9]:
        scores.append(10)
    elif card[0].isdigit():
        scores.append(int(card[0]))


def deal_cards():
    playerHand.append(deck.pop(len(deck) - 1)), dealerHand.append(deck.pop(len(deck) - 1))
    playerHand.append(deck.pop(len(deck) - 1)), dealerHand.append(deck.pop(len(deck) - 1))
    scores = []
    for card in playerHand:
        store_scores(scores, card)
    if len(scores) >= 2:
        if scores[0] == scores[1]:
            return True
    if playerHand[0][0] == playerHand[1][0] or \
            (playerHand[0].__contains__(NUMS[0]) and playerHand[1].__contains__(NUMS[0])):
        return True
    return False


def place_bet(bet_amount):
    try:
        if int(bet_amount) <= money:
            return int(bet_amount)
        else:
            num = place_bet(input("Place your bet:"))
            if 0 < num <= money:
                return num
    except:
        num = place_bet(input("Place your bet:"))
        if 0 < num <= money:
            return num


def count_score(hand):
    scores, has_ace = [], False
    for card in hand:
        if card[0] != NUMS[0]:
            store_scores(scores, card)
        else:
            has_ace = True
            scores.append(1)
    score = sum(scores)
    if has_ace and score <= 11:
        score += 10
    return score


def simplify_display(hand, hand_text, score_text):
    display_hand = ""
    for card in hand:
        display_hand += card + " "
    print(hand_text, display_hand)
    print(score_text, count_score(hand))


def display_hands_and_score_while_player_turn():
    print("Dealer's Hand: ", dealerHand[1])
    scores, has_ace = [], False
    for card in dealerHand:
        if card[0] != NUMS[0]:
            store_scores(scores, card)
        else:
            has_ace = True
    score = sum(scores)
    if has_ace and score <= 10:
        score += 11
        scores.append(11)
    print("Dealer's Score: ", scores[len(scores) - 1], "\n")
    simplify_display(playerHand, "Your Hand:", "Your Score:")
    if len(playerSplitHand) > 0 and playWithSplitHand:
        simplify_display(playerSplitHand, "Your 2nd Hand:", "Your 2nd Score:")


def display_hands_and_score_dealer_turn():
    simplify_display(dealerHand, "Dealer's Hand:", "Dealer's Score:")
    simplify_display(playerHand, "Your Hand:", "Your Score:")
    if len(playerSplitHand) > 0:
        simplify_display(playerSplitHand, "Your 2nd Hand:", "Your 2nd Score:")


def detect_win(money):
    player_hand_score, player_split_hand_score = count_score(playerHand), count_score(playerSplitHand)
    dealer_score = count_score(dealerHand)
    if dealer_score == BLACKJACK_SCORE and len(dealerHand) == 2:
        display_hands_and_score_dealer_turn()
        print("Dealer Blackjack! You Lose!")
        return True, money
    elif player_hand_score == BLACKJACK_SCORE and len(playerHand) == 2 and not hasSplit:
        display_hands_and_score_dealer_turn()
        print("Blackjack! You Win!")
        money += bet * 2.5
        return True, money
    elif (player_hand_score > BLACKJACK_SCORE and not hasSplit) or \
            (hasSplit and count_score(playerSplitHand) > BLACKJACK_SCORE and player_hand_score > BLACKJACK_SCORE):
        display_hands_and_score_dealer_turn()
        print("Bust! You Lose!")
        return True, money
    elif dealer_score > BLACKJACK_SCORE:
        display_hands_and_score_dealer_turn()
        print("Dealer Bust! You Win!")
        money += bet * 2
        return True, money
    elif ((player_hand_score < dealer_score <= BLACKJACK_SCORE and not hasSplit) or
          (player_hand_score < dealer_score <= BLACKJACK_SCORE and 
           player_split_hand_score < dealer_score <= BLACKJACK_SCORE)) and not play:
        display_hands_and_score_dealer_turn()
        print("You Lose!")
        return True, money
    elif (dealer_score < player_hand_score <= BLACKJACK_SCORE or 
          dealer_score < player_split_hand_score <= BLACKJACK_SCORE) and not play:
        display_hands_and_score_dealer_turn()
        print("You Win!")
        money += bet * 2
        return True, money
    elif dealer_score == player_hand_score and not hasSplit and not play:
        display_hands_and_score_dealer_turn()
        print("It is a Draw!")
        money += bet
        return True, money
    return False, money


def hit_stand_split(user_input, has_split):
    if user_input == HIT_INPUT and not playWithSplitHand:
        playerHand.append(deck.pop(len(deck) - 1))
    elif user_input == HIT_INPUT and playWithSplitHand:
        playerSplitHand.append(deck.pop(len(deck) - 1))
    elif user_input == STAND_INPUT:
        return True, has_split
    elif user_input == SPLIT_INPUT and canSplit and not has_split:
        playerSplitHand.append(playerHand.pop(len(playerHand) - 1))
        hasSplit = True
        return False, hasSplit
    return False, has_split


def run_dealer_turn():
    while (count_score(dealerHand) < DEALER_HIT_LIMIT or count_score(dealerHand) < count_score(playerHand) or
           count_score(dealerHand) < count_score(playerSplitHand)) and count_score(dealerHand) < BLACKJACK_SCORE:
        dealerHand.append(deck.pop(len(deck) - 1))
    return False


def reset():
    deck, dealer_hand, player_hand, player_split_hand = [], [], [], []
    has_split, play_with_split_hand, dealer_turn, play = False, False, False, True
    return deck, dealer_hand, player_hand, player_split_hand, has_split, play_with_split_hand, dealer_turn, play


if __name__ == "__main__":
    try:
        save = open("SaveGameState.txt", "r")
        gamesPlayed = int(save.readline())
        money = int(float(save.readline()))
        save.close()
    except:
        pass
    if money <= 0:
        money, gamesPlayed = 3000, 0
    while money > 0:
        deck, dealerHand, playerHand, playerSplitHand, hasSplit, playWithSplitHand, dealerTurn, play = reset()
        create_deck(1)
        print("Money $", money)
        bet = place_bet(input("Place your bet:"))
        money -= bet
        canSplit = deal_cards()
        display_hands_and_score_while_player_turn()
        isWin, money = detect_win(money)
        while not isWin and play:
            if not dealerTurn:
                if not hasSplit and not playWithSplitHand:
                    dealerTurn, hasSplit = hit_stand_split(input(BASIC_INSTRUCTIONS + " | '3' (split)"), hasSplit)
                    if count_score(playerHand) >= BLACKJACK_SCORE:
                        dealerTurn = True
                elif hasSplit and not playWithSplitHand:
                    playWithSplitHand, hasSplit = hit_stand_split(input(BASIC_INSTRUCTIONS), hasSplit)
                    if count_score(playerHand) >= BLACKJACK_SCORE:
                        playWithSplitHand = True
                elif playWithSplitHand:
                    dealerTurn, hasSplit = hit_stand_split(input(BASIC_INSTRUCTIONS), hasSplit)
                    if count_score(playerSplitHand) >= BLACKJACK_SCORE:
                        dealerTurn = True
                print("\n" * 6, "-" * 20)
                print("Money $", money)
                display_hands_and_score_while_player_turn()
                print("-" * 20)
            elif dealerTurn:
                play = run_dealer_turn()
            isWin, money = detect_win(money)
        print("-" * 20, "\n\n")
        gamesPlayed += 1
        save = open("SaveGameState.txt", "w")
        save.close()
        save = open("SaveGameState.txt", "a")
        save.write(str(gamesPlayed) + "\n")
        save.write(str(money))
        save.close()
    print("You went Bankrupt!!!", "Games Played:", gamesPlayed)
    save = open("SaveGameState.txt", "w")
    save.close()
