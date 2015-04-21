package models.player.ai.difficulties;

import models.player.Player;
import models.player.ai.Behavior;
import models.player.ai.strategies.defense.BruteForceDefense;
import models.player.ai.strategies.discard.ValueBasedDiscard;
import models.player.ai.strategies.offense.BruteForceOffense;
import models.player.ai.strategies.targeting.BotTargeting;
import models.player.ai.strategies.targeting.HumanTargeting;

/**
 * Target player first then defend then attack other bots
 */
public class Hard extends Behavior {

    public Hard() {
        super(new ValueBasedDiscard());
    }

    @Override
    public void refresh(Player player, Player[] opponents) {

        addStrategy(new BruteForceOffense(new HumanTargeting()));
        addStrategy(new BruteForceDefense());
        addStrategy(new BruteForceOffense(new BotTargeting()));

        super.refresh(player, opponents);
    }
}
