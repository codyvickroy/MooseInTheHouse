package models.player.ai.strategies.targeting;

import models.player.Player;

/**
 * Created by brandt on 3/30/15.
 */
public abstract class TargetStrategy {

    protected Player[] players;

    public TargetStrategy(Player[] players) {
        this.players = players;
    }

    public abstract int[] prioritize();
}
