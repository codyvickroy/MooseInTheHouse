package models.player.ai.behaviors;

import models.player.ai.Behavior;
import models.player.ai.strategies.MoveStrategy;
import models.player.ai.strategies.defense.BruteForceDefense;
import models.player.ai.strategies.discard.ValueBasedDiscard;
import models.player.ai.strategies.offense.BruteForceOffense;
import models.player.ai.strategies.targeting.BestScoreFirst;

import java.util.LinkedList;

/**
 * Uses brute force offense and defense and considers offense first
 * with a Best Score First target strategy.
 */
public class BruteForceOffensive extends Behavior {

    public BruteForceOffensive() {
        super(new LinkedList<MoveStrategy>(), new ValueBasedDiscard());

        addStrategy(new BruteForceOffense(new BestScoreFirst()));
        addStrategy(new BruteForceDefense());
    }
}
