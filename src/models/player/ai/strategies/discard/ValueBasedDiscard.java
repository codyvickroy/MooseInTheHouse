package models.player.ai.strategies.discard;

import models.card.Card;
import models.game.Move;
import models.player.Player;

/**
 * Created by Brandt Newton on 4/17/2015.
 */
public class ValueBasedDiscard extends Discard{


    public ValueBasedDiscard(Player player) {
        super(player);
    }

    @Override
    public Move action() {

        Card[] hand = player.getHand();
        Move move = new Move(player.getID(), hand[0], Move.DISCARD_PILE, 0);

        for (Card card : hand) {
            if (move.getCard().getValue() > card.getValue()) {
                move = new Move(player.getID(), card, Move.DISCARD_PILE, 0);
            }
        }

        return move;
    }
}
