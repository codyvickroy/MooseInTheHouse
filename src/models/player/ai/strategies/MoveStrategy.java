package models.player.ai.strategies;

import models.game.Move;
import models.player.Player;

/**
 * Created by Brandt Newton on 4/19/2015.
 */
public abstract class MoveStrategy {

    protected static Player player;
    protected static Player[] opponents;

    public abstract Move action();

    /**
     * Update player information
     *
     * @param player    player
     * @param opponents player's opponents
     */
    public static void update(Player player, Player[] opponents) {
        MoveStrategy.player = player;
        MoveStrategy.opponents = opponents;
    }
}
