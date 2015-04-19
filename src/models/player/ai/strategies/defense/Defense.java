package models.player.ai.strategies.defense;

import models.player.Player;
import models.player.ai.strategies.MoveStrategy;

/**
 * Created by brandt on 3/27/15.
 */
public abstract class Defense implements MoveStrategy {

    protected Player player;

    public Defense(Player player) {
        this.player = player;
    }
}
