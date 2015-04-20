package models.player.ai.strategies.targeting;

import models.player.Human;
import models.player.Player;

import java.util.ArrayList;
import java.util.Queue;

/**
 * Organize humans by score
 */
public class HumanTargeting extends TargetStrategy {

    @Override
    public Queue<Integer> prioritize(Player[] opponents) {

        ArrayList<Player> humans = new ArrayList<Player>();

        for (Player opponent : opponents) {
            if (opponent instanceof Human) {
                humans.add(opponent);
            }
        }

        // Organize humans by score
        return new BestScoreFirst().prioritize(humans.toArray(new Player[humans.size()]));
    }
}
