package models.player;

import models.game.Game;
import models.game.Move;
import models.player.ai.Behavior;
import models.player.ai.behaviors.StandardAdaptive;

/**
 * Created by brandt on 3/23/15.
 */
public class Bot extends Player {

    Behavior behavior;

    /**
     * Initializes the bot with StandardAdaptive behavior
     */
    public Bot() {
        behavior = new StandardAdaptive();
    }

    public Bot(Behavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public Move makeMove() {

        // Initialize new behavior
        behavior.refresh(this, Game.getPlayersExcept(getID()));

        Move move = null;

        // Apply all strategies until one works
        while(move == null && hand.size() > 0) {
            move = behavior.nextStrategy();
        }

        if (move != null) {
            removeCardFromHand(move.getCard());
        }

        return move;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }
}
