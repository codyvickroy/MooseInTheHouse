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

        // Defensive
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            int position = card.validate(getHouse());
            if (card.isDefensive() && position != Card.INVALID_POSITION) {
                hand.remove(i);
                return new Move(getID(), card, getID(), position);
            }
        }

        // Index of players in order of priority
        int[] priorities = behavior.threatAlgorithm(Game.getPlayersExcept(getID()));

        for (int i = 0; i < Game.getPlayers().length - 1; i++) {
            Player target = Game.getPlayer(priorities[i]);

            for (int j = 0; j < hand.size(); j++) {
                Card card = hand.get(j);
                int position = card.validate(target.getHouse());
                if (!card.isDefensive() && position != Card.INVALID_POSITION) {
                    hand.remove(j);
                    return new Move(getID(), card, target.getID(), position);
                }
            }
        }

        // No moves left. Discard a card.
        Card discardCard = hand.get(0);
        hand.remove(0);
        return new Move(getID(), discardCard, Move.DISCARD_PILE, 0);
    }
}
