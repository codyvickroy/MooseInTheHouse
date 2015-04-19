package models.player.ai.behaviors;

import models.player.Player;
import models.player.ai.Behavior;
import models.player.ai.strategies.MoveStrategy;
import models.player.ai.strategies.defense.BruteForceDefense;
import models.player.ai.strategies.discard.ValueBasedDiscard;
import models.player.ai.strategies.offense.BruteForceOffense;
import models.player.ai.strategies.targeting.BestScoreFirst;

import java.util.LinkedList;

/**
 * Created by Brandt Newton on 4/19/2015.
 */
public class StandardBehavior extends Behavior {

    public StandardBehavior(Player player, Player[] players) {
        super(new LinkedList<MoveStrategy>(), new ValueBasedDiscard(player));

        if (player.getPoints() > 2) {
            // Prioritize defense if player has more than 2 points
            addStrategy(new BruteForceDefense(player));
            addStrategy(new BruteForceOffense(players, new BestScoreFirst(players), player));
        } else {
            // Prioritize offense if player has 2 points or less
            addStrategy(new BruteForceOffense(players, new BestScoreFirst(players), player));
            addStrategy(new BruteForceDefense(player));
        }
    }
}
