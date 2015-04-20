package models.player.ai.strategies.targeting;

import models.player.Player;

import java.util.Queue;

/**
 * Created by brandt on 3/30/15.
 */
public abstract class TargetStrategy {

    /**
     * Returns a queue of player IDs
     *
     * @param opponents     opponents
     * @return              queue of player IDs
     */
    public abstract Queue<Integer> prioritize(Player[] opponents);
}
