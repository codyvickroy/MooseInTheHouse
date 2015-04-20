package models.player.ai.difficulties;

import models.player.Player;
import models.player.ai.Behavior;
import models.player.ai.strategies.defense.BruteForceDefense;
import models.player.ai.strategies.discard.ValueBasedDiscard;
import models.player.ai.strategies.offense.BruteForceOffense;
import models.player.ai.strategies.targeting.BotTargeting;
import models.player.ai.strategies.targeting.HumanTargeting;

/**
 * Consider offensive moves against Bots first then considers defensive moves
 * and finally offensive moves against players.
 */
public class Easy extends Behavior {

    public Easy() {
        super(new ValueBasedDiscard());
    }

    @Override
    public void refresh(Player player, Player[] opponents) {

        addStrategy(new BruteForceOffense(new BotTargeting()));
        addStrategy(new BruteForceDefense());
        addStrategy(new BruteForceOffense(new HumanTargeting()));

        super.refresh(player, opponents);
    }
}