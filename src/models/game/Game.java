package models.game;

import models.card.Card;
import models.card.top.MooseBait;
import models.player.Player;
import remote.Remote;
import view.CardObserver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private static Player[] players;
    private static List<Move> moveHistory;
    private static Deck deck;
    private CardObserver cardObserver;

    /**
     * Gets the number of opponents and deals to all opponents.
     * Assumes a minimum of two opponents
     *
     * @param players opponents to add
     */
    public Game(Player[] players) {
        Game.players = players;
        deck = new Deck();
        moveHistory = new ArrayList<Move>();
        cardObserver = null;

        for (Player player : players) {
            player.addCardsToHand(deck.deal(4));
        }
    }//end constructor

    public void gameLoop(boolean reportStatistics, int timeDelay) {

        if (reportStatistics) {
            Remote.newGameID();

            if (!Remote.initGame()) {
                
                System.err.println("Game init failed!");
            }
        }

        int roundCount = 0;
        boolean gameOver = false;

        do {// Main loop

            System.out.println("ROUND " + roundCount++);

            for (int i = 0; i <= (players.length - 1) && ! gameOver; i++) {

                // Deal cards
                players[i].addCardsToHand(deck.deal(1));
                updateHandObserver();
                updateDeckObserver();

                Move playerMove = players[i].makeMove();          //player makes the move
                processMove(playerMove);

                updateHandObserver();
                updateHouseObserver();

                if (timeDelay > 0) {
                    try {
                        Thread.sleep(timeDelay);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }

                if (reportStatistics) {
                    Remote.uploadMove(playerMove);                         //Uploads move to game database
                    Remote.uploadScores();
                }

                moveHistory.add(playerMove);                    //adds the move to our move history for stats

                gameOver = gameOver();
            }//end for all opponents
        } while  ( ! gameOver);
    }

    private void processMove(Move move) {
        moveHistory.add(move);                    //adds the move to our move history for stats

        if (move != null) {
            if (move.getReceivingPlayerID() == Move.DISCARD_PILE) {
                deck.discard(move.getCard());
                updateDiscardPileObserver();
            } else {
                players[move.getReceivingPlayerID()].setCardInHouse(move);
                Player recievingPlayer = players[move.getReceivingPlayerID()];
                Card pHand[] = recievingPlayer.getHand();

                if ((!move.getCard().isBottomCard()) && (move.getCard().getCardClass() != null)) {
                    boolean foundBait = false;
                    for (int i = 0; (i <= pHand.length - 1) && (!foundBait); i++) {
                        if (pHand[i].isBait()) {
                            Move baitMove = new Move(move.getReceivingPlayerID(), new MooseBait(), move.getCardPlayerID(), move.getHousePosition());
                            moveHistory.add(baitMove);
                            recievingPlayer.removeCardFromHand(new MooseBait());
                            recievingPlayer.addCardsToHand(deck.deal(1));
                            foundBait = true;
                        }
                    }
                }
            }
            System.out.println(move);
        }
        updatePointsObserver();
    }

    /**
     * If the deck is empty and no moves are left end the game.
     *
     * @return true if all opponents have skipped their turn and the deck is empty
     */
    private boolean gameOver() {
        if (deck.size() == 0) {
            for (int i = 0; i < players.length; i++) {
                if (!Move.skipped(moveHistory.get(moveHistory.size() - i - 1))) {
                    return false;
                }
            }
            JOptionPane.showMessageDialog(null, getWinners());
            Remote.uploadEnd();
            return true;
        } else {
            return false;
        }
    }//end allPlayersPassed

    public static boolean reportGame()
    {
        return true;
    }

    public static String getWinners() {

        int lowestPoints = 99;

        for (Player player : players) {
            if (player.getPoints() < lowestPoints) {
                lowestPoints = player.getPoints();
            }
        }

        String returnString = "Player";
        final int originalLength = returnString.length();

        for (Player player : players) {
            if (player.getPoints() == lowestPoints) {
                if (returnString.length() > originalLength) {
                    returnString += " and ";
                }
                returnString += " " + player.getID();
            }
        }

        return returnString + " won!";
    }

    /**
     * Returns the player with the given id.
     *
     * @param id id of desired player
     * @return player with matching id if found
     */
    public static Player getPlayerByID(int id) {
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

    public static Player[] getPlayersExcept(int id) {

        Player[] returnPlayers = new Player[players.length - 1];
        int offset = 0;

        for (int i = 0; i < players.length; i++) {
            if (players[i].getID() != id) {
                returnPlayers[offset] = players[i];
                offset++;
            }
        }

        return returnPlayers;
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

    private void updatePointsObserver() {
        if (cardObserver != null)
            cardObserver.updatePoints();
    }

    public static Player getHuman() {
        return players[0];
    }
}//end Game Class
