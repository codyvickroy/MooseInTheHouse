package models.player.ai.strategies.defense;

import models.card.Card;
import models.game.Move;
import models.player.Player;

/**
 * Created by brandt on 3/27/15.
 */
public class BruteForceDefense extends Defense {

    public BruteForceDefense(Player player) {
        super(player);
    }

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
