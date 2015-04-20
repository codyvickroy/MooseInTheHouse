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
public class StandardAdaptive extends Behavior {

    public StandardAdaptive() {
        super(new LinkedList<MoveStrategy>(), new ValueBasedDiscard());

        addStrategy(new BruteForceOffense(new BestScoreFirst()));
        addStrategy(new BruteForceDefense());
    }

    @Override
    public void refresh(Player player, Player[] opponents) {

        if (player.getPoints() < 2) {
            setMoveStrategies(new LinkedList<MoveStrategy>());
            addStrategy(new BruteForceOffense(new BestScoreFirst()));
            addStrategy(new BruteForceDefense());
        } else {
            setMoveStrategies(new LinkedList<MoveStrategy>());
            addStrategy(new BruteForceDefense());
            addStrategy(new BruteForceOffense(new BestScoreFirst()));
        }

        super.refresh(player, opponents);
    }
}
