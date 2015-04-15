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
     *
     *
     * @param index
     * @param card
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
