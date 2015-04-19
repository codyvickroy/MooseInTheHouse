package models.player.ai;

import models.game.Move;
import models.player.ai.strategies.MoveStrategy;
import models.player.ai.strategies.discard.Discard;

import java.util.Queue;

/**
 * Created by Brandt Newton on 4/17/2015.
 */
public class Behavior {

    private Queue<MoveStrategy> moveStrategies;
    private Discard discard;

    public Behavior(Queue<MoveStrategy> moveStrategies, Discard discard) {
        this.moveStrategies = moveStrategies;
        this.discard = discard;
    }

    /**
     * Executes the next strategy and returns it's move.
     *
     * If there are no strategies left, the player cannot think of any move to make and must discard.
     *
     * @return move
     */
    public Move nextStrategy() {
        if (moveStrategies.size() > 0) {
            return moveStrategies.remove().action();
        } else {
            // Out of strategies
            return discard.action();
        }
    }

    public void addStrategy(MoveStrategy moveStrategy) {
        moveStrategies.add(moveStrategy);
    }
}
