package models.player.ai.defense;

import models.card.Card;
import models.game.Move;
import models.player.Player;

/**
 * Created by brandt on 3/27/15.
 */
public class BruteForceDefense implements Defense {

    public Move action(Player player) {
        // Defensive
        for (Card card : player.getHand()) {
            int position = card.validate(player.getHouse());

            if (card.isDefensive() && position > -1) {
                return new Move(player.getID(), card, player.getID(), position);
            }
        }

        return null;
    }

}
