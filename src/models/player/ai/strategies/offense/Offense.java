package models.player.ai.strategies.offense;

import models.player.Player;
import models.player.ai.strategies.MoveStrategy;
import models.player.ai.strategies.targeting.TargetStrategy;

/**
 * Created by brandt on 3/27/15.
 */
public abstract class Offense implements MoveStrategy {

    protected Player[] players;
    protected TargetStrategy targetStrategy;
    protected Player player;

    public Offense(Player[] players, TargetStrategy targetStrategy, Player player) {
        this.players = players;
        this.targetStrategy = targetStrategy;
        this.player = player;
    }
}
