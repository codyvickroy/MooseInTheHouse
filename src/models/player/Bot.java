package models.player;

import models.card.Card;
import models.game.Game;
import models.game.Move;
import models.player.ai.Behavior;
import models.player.ai.StandardBehavior;

/**
 * Created by brandt on 3/23/15.
 */
public class Bot extends Player {

    Behavior behavior;

    public Bot(int id) {
        super(id);
        this.behavior = new StandardBehavior();
    }

    public Bot(int id, Behavior behavior) {
        super(id);
        this.behavior = behavior;
    }

    @Override
    public Move makeMove() {

        Move move;

        move = behavior.chooseDefense(this);

        if (move != null) {
            removeCardFromHand(move.getCard());
            return move;
        }

        move = behavior.chooseOffense(Game.getPlayersExcept(getID()), this);

        if (move != null) {
            removeCardFromHand(move.getCard());
            return move;
        }

        // No moves left. Discard a card.
        Card discardCard = hand.get(0);
        hand.remove(0);
        return new Move(getID(), discardCard, Move.DISCARD_PILE, 0);
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
