package models.player.ai.strategies.discard;

import models.player.Player;
import models.player.ai.strategies.MoveStrategy;

/**
 * Created by Brandt Newton on 4/17/2015.
 */
public abstract class Discard implements MoveStrategy {

    protected Player player;

    public Discard(Player player) {
        this.player = player;
    }
}
