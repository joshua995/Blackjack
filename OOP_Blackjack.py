"""
Joshua Liu
2022-December-10
Blackjack using 'object-oriented programming'
Player can hit, stand and split
"""

from random import shuffle


class Hand(object):
    def __init__(self, _player, display_every_card):
        self.player, self.hand, self.scores, self.score = _player, [], [], 0
        self.isBust, self.isBlackjackScore, self.displayEveryCard = False, False, display_every_card

    def display_hand(self):
        self.count_score()
        if self.displayEveryCard:
            print(self.player + "'s Hand:", ' '.join(self.hand), "\n" + self.player + "'s Score:", self.score)
        else:
            print(self.player + "'s Hand:", self.hand[0])
            print(self.player + "'s Score:", self.score - self.scores[1] - 10) if self.hand[1].__contains__(NUMS[0]) \
                else print(self.player + "'s Score:", self.score - self.scores[1])

    def run_turn(self, user_input):
        if self.player == PLAYER_TITLE:
            if user_input == HIT_INPUT:
                self.hit(), self.count_score()
            display_all_hands()
        elif self.player == DEALER_TITLE:
            self.displayEveryCard = True
            while self.score <= DEALER_HIT_LIMIT or self.score < player.score or self.score < playerSplit.score:
                self.hit(), self.count_score()

    def hit(self):
        self.hand.append(deck.pop(0))

    def count_score(self):
        has_ace, self.score = False, 0
        self.scores.clear()
        for card in self.hand:
            if card[0] != NUMS[0]:
                if card[0] == NUMS[10] or card[0] == NUMS[11] or card[0] == NUMS[12] or card[:2] == NUMS[9]:
                    self.scores.append(10)
                elif card[0].isdigit():
                    self.scores.append(int(card[0]))
            else:
                has_ace = True
                self.scores.append(1)
        self.score = sum(self.scores)
        if has_ace and self.score <= 11:
            self.score += 10
        self.detect_max_score_or_bust()

    def detect_max_score_or_bust(self):
        if self.score > BLACKJACK_SCORE:
            self.isBust = True
        elif self.score == BLACKJACK_SCORE:
            self.isBlackjackScore = True


NUMS = ['A', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K']
SUITS = ['Hearts', 'Spades', 'Clubs', 'Diamonds']
BLACKJACK_SCORE, DEALER_HIT_LIMIT, HIT_INPUT, STAND_INPUT, SPLIT_INPUT = 21, 16, "1", "2", "3"
PLAYER_TITLE, DEALER_TITLE, DEFAULT_MONEY = "Player", "Dealer", 3000
player, dealer, playerSplit = Hand(PLAYER_TITLE, True), Hand(DEALER_TITLE, False), Hand(PLAYER_TITLE, True)
deck, canPlayerSplit, hasPlayerSplit, playWithFirstHand, playGame = [], False, False, True, True
playerMoney, playerBet, saveFileLocation = DEFAULT_MONEY, 0, "SaveGameStateOOP.txt"


def load_save_data():
    player_money = playerMoney
    try:
        save = open(saveFileLocation, "r")
        player_money = int(float(save.readline()))
        save.close()
    except IOError:
        pass
    return player_money


def create_deck(amount_of_decks):
    [[[deck.append(num + "-" + suit) for num in NUMS] for suit in SUITS] for _ in range(amount_of_decks)]
    shuffle(deck)


def place_bet():
    user_input = input("Your money: %d$\nEnter a bet: " % playerMoney)
    while user_input.isalpha() or (float(user_input) > playerMoney or float(user_input) <= 0):
        user_input = input("Error %s is an invalid bet. Enter a valid bet: " % user_input)
    player_bet = float(user_input)
    player_money = playerMoney - player_bet
    return player_money, player_bet


def deal_initial_cards():
    player.hand.append(deck.pop(0)), dealer.hand.append(deck.pop(0))
    player.hand.append(deck.pop(0)), dealer.hand.append(deck.pop(0))


def dealer_turn():
    dealer.run_turn(userInput)
    return check_winner_v2()


def display_all_hands():
    print("-" * 40, "\n" * 5, f"\nYour money: {playerMoney}$"), dealer.display_hand(), print(), player.display_hand()
    playerSplit.display_hand() if hasPlayerSplit else print()


def check_blackjack(player_bet):
    if dealer.isBlackjackScore:
        return ending_display(f"{DEALER_TITLE} Blackjack!\n"), 0
    elif player.isBlackjackScore:
        return ending_display(f"{PLAYER_TITLE} Blackjack!\n"), player_bet * 2.5
    return True, 0


def check_winner_v2():
    checkWinner = check_winner(playerBet)
    play_game, player_money = checkWinner[0], playerMoney + checkWinner[1]
    return play_game, player_money


def check_winner(player_bet):
    if player.score > BLACKJACK_SCORE and not hasPlayerSplit:
        return ending_display(f"{PLAYER_TITLE} Bust, {DEALER_TITLE} Won!\n"), 0
    elif dealer.score > BLACKJACK_SCORE:
        return ending_display(f"{DEALER_TITLE} Bust, {PLAYER_TITLE} Won!\n"), player_bet * 2
    elif (player.score > BLACKJACK_SCORE and playerSplit.score > BLACKJACK_SCORE) and hasPlayerSplit:
        return ending_display(f"{PLAYER_TITLE} Bust, {DEALER_TITLE} Won!\n"), 0
    elif (dealer.score < player.score <= BLACKJACK_SCORE) or (dealer.score < playerSplit.score <= BLACKJACK_SCORE):
        return ending_display(f"{PLAYER_TITLE} Won!\n"), player_bet * 2
    elif dealer.score >= player.score and dealer.score >= playerSplit.score:
        return ending_display(f"{DEALER_TITLE} Won!\n"), 0
    return True, 0


def ending_display(message):
    dealer.displayEveryCard = True
    display_all_hands(), print(message)
    return False


def save_data():
    save = open(saveFileLocation, "w")
    save.close()
    save = open(saveFileLocation, "a")
    save.write(str(playerMoney))
    save.close()


def reset():
    _player, _dealer, player_split = Hand(PLAYER_TITLE, True), Hand(DEALER_TITLE, False), Hand(PLAYER_TITLE, True)
    _deck, can_player_split, has_player_split, play_with_first_hand, play_game = [], False, False, True, True
    return _player, _dealer, player_split, _deck, can_player_split, has_player_split, play_with_first_hand, play_game


if __name__ == "__main__":
    playerMoney = load_save_data()
    if playerMoney <= 0:
        playerMoney = DEFAULT_MONEY
    while playerMoney > 0:
        playerMoney, playerBet = place_bet()
        create_deck(1), deal_initial_cards(), display_all_hands()
        checkBlackjack = check_blackjack(playerBet)
        playGame, playerMoney = checkBlackjack[0], playerMoney + checkBlackjack[1]
        if player.scores[0] == player.scores[1]:
            canPlayerSplit = True
        while playGame:
            userInput = input("Enter: '1' = Hit | '2' = Stand | '3' = Split")
            if userInput == HIT_INPUT:
                if playWithFirstHand:
                    player.run_turn(userInput)
                else:
                    playerSplit.run_turn(userInput)
            elif userInput == STAND_INPUT:
                if not hasPlayerSplit or not playWithFirstHand:
                    playGame, playerMoney = dealer_turn()
                else:
                    playWithFirstHand = False
                    display_all_hands()
            elif userInput == SPLIT_INPUT and canPlayerSplit:
                canPlayerSplit, hasPlayerSplit = False, True
                playerSplit.hand.append(player.hand.pop(len(player.hand) - 1)), display_all_hands()
            if player.score == BLACKJACK_SCORE and not hasPlayerSplit:
                playGame, playerMoney = dealer_turn()
            elif player.score > BLACKJACK_SCORE and not hasPlayerSplit:
                playGame, playerMoney = check_winner_v2()
            elif player.score >= BLACKJACK_SCORE and playWithFirstHand and hasPlayerSplit:
                playWithFirstHand = False
            elif playerSplit.score == BLACKJACK_SCORE and hasPlayerSplit:
                playGame, playerMoney = dealer_turn()
            elif playerSplit.score > BLACKJACK_SCORE and hasPlayerSplit:
                playGame, playerMoney = check_winner_v2()
        save_data()
        player, dealer, playerSplit, deck, canPlayerSplit, hasPlayerSplit, playWithFirstHand, playGame = reset()
