package models.player.ai.difficulties;

import models.player.Player;
import models.player.ai.Behavior;
import models.player.ai.strategies.defense.BruteForceDefense;
import models.player.ai.strategies.discard.ValueBasedDiscard;
import models.player.ai.strategies.offense.BruteForceOffense;
import models.player.ai.strategies.targeting.BestScoreFirst;

/**
 * indiscriminate targeting
 */
public class Normal extends Behavior {

    public Normal() {
        super(new ValueBasedDiscard());
    }

    @Override
    public void refresh(Player player, Player[] opponents) {

        addStrategy(new BruteForceOffense(new BestScoreFirst()));
        addStrategy(new BruteForceDefense());

        super.refresh(player, opponents);
    }
}
