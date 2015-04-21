package models.player.ai;

import models.game.Move;
import models.player.Player;
import models.player.ai.difficulties.Easy;
import models.player.ai.difficulties.Hard;
import models.player.ai.difficulties.Normal;
import models.player.ai.strategies.MoveStrategy;
import models.player.ai.strategies.discard.Discard;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Brandt Newton on 4/17/2015.
 */
public class Behavior {

    public static final int EASY_AI = 0;
    public static final int NORMAL_AI = 1;
    public static final int HARD_AI = 2;

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

    public static Behavior getAI(int level) {

        Behavior ai;

        switch (level) {
            case EASY_AI:
                ai = new Easy();
                break;
            case NORMAL_AI:
                ai = new Normal();
                break;
            case HARD_AI:
                ai = new Hard();
                break;
            default:
                ai = new Easy();
                break;
        }

        return ai;
    }

    public void addStrategy(MoveStrategy moveStrategy) {
        moveStrategies.add(moveStrategy);
    }

    public void setMoveStrategies(LinkedList<MoveStrategy> moveStrategies) {
        this.moveStrategies = moveStrategies;
    }
}
