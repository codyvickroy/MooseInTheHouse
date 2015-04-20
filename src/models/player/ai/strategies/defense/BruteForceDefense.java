package models.player.ai.strategies.defense;

import models.card.Card;
import models.game.Move;

/**
 * Plays the first defensive move found if any.
 */
public class BruteForceDefense extends Defense {

    @Override
    public Move action() {

        Card[] hand = player.getHand();

        for (Card card : hand) {
            int position = card.validate(player.getHouse());
            if (card.isDefensive() && position != Card.INVALID_POSITION) {
                return new Move(player.getID(), card, player.getID(), position);
            }
        }

        // No defensive moves found.
        return null;
    }
}
