package models.game;

import models.player.Player;
import view.CardObserver;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static Player[] players;
    private static List<Move> moveHistory = new ArrayList<Move>();
    private static Deck deck;
    private CardObserver cardObserver;

    /**
     * Gets the number of players and deals to all players.
     * Assumes a minimum of two players
     *
     * @param p players to add
     */
    public Game(Player[] p){
        players = p;
        deck = new Deck();

        for (int i = 0; i < players.length; i++) {
            players[i].addCardsToHand(deck.deal(4));
        }
    }//end constructor

    /**
     *  The meat of the game.
     *
     *  move history
     *  points of all players
     */
    public void gameLoop(){

        do {// Main loop
            for(int i = 0; i <= (players.length - 1) ; i++){
                // Deal cards
                players[i].addCardsToHand(deck.deal(1));
                updateHandObserver();
                updateDeckObserver();

                Move playerMove = players[i].makeMove();          //player makes the move
                updateAllObservers();

                moveHistory.add(playerMove);                    //adds the move to our move history for stats
            }//end for all players
        } while( ! gameOver());

        // TODO process stats here
    }

    /*
     *  If the deck is empty and no moves are left end the game.
     */

    /**
     *  If the deck is empty and no moves are left end the game.
     *
     * @return  true if all players have skipped their turn and the deck is empty
     */
    private boolean gameOver() {
        if(deck.size() == 0){
            for (int i = 0; i < players.length; i++) {
                if ( ! Move.skipped(moveHistory.get(moveHistory.size() - i - 1))) {
                    return false;
                }
            }
        }
        return true;
    }//end allPlayersPassed

    /**
     * Returns the player with the given id.
     *
     * @param id    id of desired player
     * @return      player with matching id if found
     */
    public static Player getPlayer(int id) {
        for (Player player : players) {
            if (player.getID() == id) {
                return player;
            }
        }
        return null;
    }

    public static Player[] getPlayers() {
        return players;
    }

    /**
     * Returns the game deck.
     * Can be used to check the contents of the deck or discard pile.
     *
     * @return game deck
     */
    public static Deck getDeck() {
        return deck;
    }

    /**
     * Returns the game's move history.
     * This is the complete history of the current game session.
     *
     * @return game move history
     */
    public static List<Move> getMoveHistory() {
        return moveHistory;
    }

    /*
     | ------------------------------------------
     | Observer Methods
     | ------------------------------------------
     |
     | Everything to do with updating the game observers (view) goes here.
     */

    /**
     * Sets the card observer and syncs it.
     *
     * @param cardObserver view that observes the cards
     */
    public void setCardObserver(CardObserver cardObserver) {
        this.cardObserver = cardObserver;
        updateAllObservers();
    }

    /**
     * Updates every observer
     */
    private void updateAllObservers() {
        updateDeckObserver();
        updateHandObserver();
        updateHouseObserver();
        updateDiscardPileObserver();
    }

    private void updateHandObserver() {
        if (cardObserver != null)
            cardObserver.updateHands();
    }

    private void updateHouseObserver() {
        if (cardObserver != null)
            cardObserver.updateHouses();
    }

    private void updateDeckObserver() {
        if (cardObserver != null)
            cardObserver.updateDeck();
    }

    private void updateDiscardPileObserver() {
        if (cardObserver != null)
            cardObserver.updateDiscardPile();
    }
}//end Game Class
