package models.player.ai.strategies.targeting;

import models.player.Bot;
import models.player.Player;

import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by brandt on 4/20/15.
 */
public class BotTargeting extends TargetStrategy {

    @Override
    public Queue<Integer> prioritize(Player[] opponents) {

        ArrayList<Player> bots = new ArrayList<Player>();

        for (Player opponent : opponents) {
            if (opponent instanceof Bot) {
                bots.add(opponent);
            }
        }

        // Organize bots by score
        return new BestScoreFirst().prioritize(bots.toArray(new Player[bots.size()]));
    }
}
