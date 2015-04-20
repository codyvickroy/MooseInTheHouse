package models.player.ai;

import models.game.Move;
import models.player.Player;
import models.player.ai.strategies.MoveStrategy;
import models.player.ai.strategies.discard.Discard;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Brandt Newton on 4/17/2015.
 */
public class Behavior {

    private LinkedList<MoveStrategy> moveStrategies;
    private Iterator<MoveStrategy> strategyIterator;
    private Discard discard;

    public Behavior(Discard discard) {
        moveStrategies = new LinkedList<MoveStrategy>();
        this.discard = discard;
    }

    /**
     * Executes the next strategy and returns it's move.
     *
     * If there are no strategies left, the player cannot think of any move to make and must discard.
     *
     * @return  move
     */
    public Move nextStrategy() {
        if (strategyIterator.hasNext()) {
            return strategyIterator.next().action();
        } else {
            // Out of strategies
            return discard.action();
        }
    }

    /**
     * Resets the move strategy iterator and updates the player information for the strategies.
     *
     * Must be called at the beginning of a new turn.
     */
    public void refresh(Player player, Player[] opponents) {
        MoveStrategy.update(player, opponents);

        strategyIterator = moveStrategies.iterator();
    }

    public void addStrategy(MoveStrategy moveStrategy) {
        moveStrategies.add(moveStrategy);
    }

    public void setMoveStrategies(LinkedList<MoveStrategy> moveStrategies) {
        this.moveStrategies = moveStrategies;
    }
}
