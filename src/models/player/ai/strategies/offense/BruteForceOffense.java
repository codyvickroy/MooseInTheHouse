package models.player.ai.strategies.offense;

import models.card.Card;
import models.game.Move;
import models.player.Player;
import models.player.ai.strategies.targeting.TargetStrategy;

/**
 * Created by brandt on 3/30/15.
 */
public class BruteForceOffense extends Offense{

    public BruteForceOffense(Player[] players, TargetStrategy targetStrategy, Player player) {
        super(players, targetStrategy, player);
    }

    @Override
    public Move action() {

        Card[] hand = player.getHand();
        int[] priorities = targetStrategy.prioritize();

        for (int i = 0; i < players.length - 1; i++) {
            Player target = Player.findPlayerByID(players, priorities[i]);

            for (Card card : hand) {
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
