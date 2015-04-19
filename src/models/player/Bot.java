package models.player;

import models.card.Card;
import models.game.Game;
import models.game.Move;
import models.player.ai.Behavior;
import models.player.ai.behaviors.StandardBehavior;

/**
 * Created by brandt on 3/23/15.
 */
public class Bot extends Player {

    Behavior behavior;

    public Bot(int id) {
        super(id);

    }

    public Bot(int id, Behavior behavior) {
        super(id);
        this.behavior = behavior;
    }

    @Override
    public Move makeMove() {

        // Initialize new behavior
        Behavior behavior = new StandardBehavior(this, Game.getPlayersExcept(getID()));

        Move move = null;

        while(move == null && hand.size() > 0) {
            move = behavior.nextStrategy();
        }

        if (move != null) {
            removeCardFromHand(move.getCard());
        }

        return move;
    }

    private void removeCardFromHand(Card card) {
        for (int i = 0; i < hand.size(); i++) {
            if (card.equals(hand.get(i))) {
                hand.remove(i);
                break;
            }
        }
    }
}
