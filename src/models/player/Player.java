package models.player;

import models.card.Card;
import models.game.Move;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Player {

    private static int idCounter = 0;
    private int points = 0;
    private int id;

    protected ArrayList<Card> hand = new ArrayList<Card>();
    protected ArrayList<Card> house = new ArrayList<Card>();

    public Player() {
        // Set ID to global counter
        id = idCounter++;
        hand = new ArrayList<Card>();
        house = new ArrayList<Card>();
    }

    public abstract Move makeMove();

    /**
     * Adds an array of cards to the opponents hand.
     * Called during dealing or drawing cards.
     *
     * @param cards    cards to be added to hand
     */
    public void addCardsToHand(Card[] cards) {
        hand.addAll(Arrays.asList(cards));
    }

    /**
     * Sets the given card in the player's house.
     * If the card is an occupied room then increment the opponents points.
     * If the index is larger than size of the house it is added to the house.
     * If the index within the size of the house it will overwrite the card at that position.
     *
     * @param index position of card to be played
     * @param card  card to be played
     */
    public void setCardInHouse(int index, Card card) {
        if ( ! card.isBottomCard() && ! card.isDefensive()) {
            points++;
        }

        if (index >= house.size())
            house.add(card);
        else
            house.set(index, card);
    }

    public void setCardInHouse(Move move) {
        setCardInHouse(move.getHousePosition(), move.getCard());
    }

    public int getID() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public Card[] getHand() {
        return hand.toArray(new Card[hand.size()]);
    }

    public Card[] getHouse() {
        return house.toArray(new Card[house.size()]);
    }

    public void setPoints(int points) {
        this.points = points;
    }


    public static Player findPlayerByID(Player[] players, int id) {
        for (Player player : players) {
            if (player.getID() == id) {
                return player;
            }
        }
        System.err.println("No player by ID " + id);
        return null;
    }


    protected void removeCardFromHand(Card card) {
        for (int i = 0; i < hand.size(); i++) {
            if (card.equals(hand.get(i))) {
                hand.remove(i);
                break;
            }
        }
    }
}
