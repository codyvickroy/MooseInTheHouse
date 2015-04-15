package models.player;

import models.card.Card;
import models.game.Move;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by brandt on 3/20/15.
 */
public abstract class Player {

    private int points = 0;
    private int id;

    protected ArrayList<Card> hand = new ArrayList<Card>();
    protected ArrayList<Card> house = new ArrayList<Card>();

    public Player(int id, ArrayList<Card> hand) {
        this.id = id;
        this.hand = hand;
    }

    public abstract Move makeMove();

    /**
     * Adds an array of cards to the players hand.
     * Called during dealing or drawing cards.
     *
     * @param cards    cards to be added to hand
     */
    public void addCardsToHand(Card[] cards) {
        hand.addAll(Arrays.asList(cards));
    }

    /**
     * Sets the given card in the player's house.
     * If the card is an occupied room then increment the players points.
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

    public boolean hasMooseInHouse() {
        for (int i = 0; i < house.size(); i++) {
            if (Card.isMoose(house.get(i))) {
                return true;
            }
        }

        return false;
    }

    public void setCardInHouse(Move move) {
        setCardInHouse(move.getHousePosition(), move.getCard());
    }

    /**
     * Compares player ids
     * @param player    player to compare
     * @return          true if player ids match
     */
    public boolean equals(Player player) {
        return id == player.getID();
    }

    public int getID() {
        return id;
    }

    public int getPoints() {
        return points;
    }

    public Card[] getHand() {
        return house.toArray(new Card[hand.size()]);
    }

    public Card[] getHouse() {
        return house.toArray(new Card[house.size()]);
    }
}
