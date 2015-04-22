package models.player.ai.strategies.offense;

import models.player.ai.strategies.MoveStrategy;
import models.player.ai.strategies.targeting.TargetStrategy;

/**
 * Strategy that primarily considers the player's opponents and prioritizes them
 * by using it's TargetStrategy
 */
public abstract class Offense extends MoveStrategy {

    protected TargetStrategy targetStrategy;

    public Offense(TargetStrategy targetStrategy) {
        this.targetStrategy = targetStrategy;
    }

    public void setTargetStrategy(TargetStrategy targetStrategy) {
        this.targetStrategy = targetStrategy;
    }
}
