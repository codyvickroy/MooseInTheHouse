package models.player.ai.offense;

import models.card.Card;
import models.game.Move;
import models.player.Player;

/**
 * Created by brandt on 3/30/15.
 */
public class BruteForceOffense implements Offense{

    @Override
    public Move chooseOffense(Player[] players, int[] priorities, Player player) {

        Card[] hand = player.getHand();

        for (int i = 0; i < players.length - 1; i++) {
            Player target = Player.findPlayerByID(players, priorities[i]);

            for (int j = 0; j < hand.length; j++) {
                Card card = hand[j];
                int position = card.validate(target.getHouse());
                if (!card.isDefensive() && position != Card.INVALID_POSITION) {
                    return new Move(player.getID(), card, target.getID(), position);
                }
            }
        }

        // No offensive moves found
        return null;
    }
}
