package models.player.ai.strategies.offense;

import models.card.Card;
import models.game.Move;
import models.player.Player;
import models.player.ai.strategies.targeting.TargetStrategy;

import java.util.Queue;

/**
 * Plays the first offensive move if any.
 */
public class BruteForceOffense extends Offense{

    public BruteForceOffense(TargetStrategy targetStrategy) {
        super(targetStrategy);
    }

    @Override
    public Move action() {

        Card[] hand = player.getHand();
        Queue<Integer> priorities = targetStrategy.prioritize(opponents);

        for (Player opponent : opponents) {
            Player target = Player.findPlayerByID(opponents, priorities.remove());

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
