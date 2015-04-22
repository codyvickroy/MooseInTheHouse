package models.player.ai.strategies.discard;

import models.card.Card;
import models.game.Move;

/**
 * Finds the card of the lowest value
 */
public class ValueBasedDiscard extends Discard {

    @Override
    public Move action() {

        Card[] hand = player.getHand();
        Move move = new Move(player.getID(), hand[0], Move.DISCARD_PILE, 0);

        for (Card card : hand) {
            if (discardValue(move.getCard()) > discardValue(card)) {
                move = new Move(player.getID(), card, Move.DISCARD_PILE, 0);
            }
        }

        return move;
    }

    private int discardValue(Card card) {
        if (card.isMoose()) {
            return 0;
        } else if (card.isBottomCard()) {
            return 2;
        } else if (card.isDefensive()) {
            return 3;
        } else {
            return 1;
        }
    }
}
