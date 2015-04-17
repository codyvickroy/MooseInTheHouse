package models.player.ai;

import models.player.ai.defense.BruteForceDefense;
import models.player.ai.discard.ValueBasedDiscard;
import models.player.ai.offense.BruteForceOffense;
import models.player.ai.threatAlgorithm.BestScoreFirst;

/**
 * Created by brandt on 3/23/15.
 */
public final class StandardBehavior extends Behavior {

    public StandardBehavior() {
        super(new BruteForceOffense(), new BestScoreFirst(), new BruteForceDefense(), new ValueBasedDiscard());
    }
}
