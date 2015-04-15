package models.game;

import models.card.Card;

/**
 * Created by brandt on 3/23/15.
 */
public class Move {
    public static final int DISCARD_PILE = -1;
    private Card card;
    private int cardPlayerID;
    private int receivingPlayerID;
    private int housePosition;

    public Move (int cardPlayerID, Card card, int receivingPlayerID, int housePosition) {
        this.cardPlayerID = cardPlayerID;
        this.card = card;
        this.receivingPlayerID = receivingPlayerID;

        if (receivingPlayerID == DISCARD_PILE) {
            this.housePosition = 0;
        } else {
            this.housePosition = housePosition;
        }
    }

    public Card getCard() {
        return card;
    }

    public int getCardPlayerID() {
        return cardPlayerID;
    }

    public int getReceivingPlayerID() {
        return receivingPlayerID;
    }

    public int getHousePosition() {
        return housePosition;
    }

    public static boolean skipped(Move move) {
        return move.getReceivingPlayerID() == DISCARD_PILE;
    }

    @Override
    public String toString() {
        if (receivingPlayerID == DISCARD_PILE) {
            return "Player " + cardPlayerID + " discarded a " + card;
        } else {
            return "Player " + cardPlayerID + " played a " + card + " on player " + receivingPlayerID + " at position " + housePosition;
        }
    }
}
