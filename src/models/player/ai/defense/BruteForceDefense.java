package models.player.ai.defense;

import models.card.Card;
import models.game.Move;
import models.player.Player;

/**
 * Created by brandt on 3/27/15.
 */
public class BruteForceDefense implements Defense {

    public Move chooseDefense(Player player) {

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
