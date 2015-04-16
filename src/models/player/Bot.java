package models.player;

import models.card.Card;
import models.game.Game;
import models.game.Move;
import models.player.ai.Behavior;
import models.player.ai.StandardBehavior;

import java.util.ArrayList;

/**
 * Created by brandt on 3/23/15.
 */
public class Bot extends Player {

    Behavior behavior;

    public Bot(int id, ArrayList<Card> hand) {
        super(id, hand);
        this.behavior = new StandardBehavior();
    }

    public Bot(int id, ArrayList<Card> hand, Behavior behavior) {
        super(id, hand);
        this.behavior = behavior;
    }

    @Override
    public Move makeMove() {

        // Defensive
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            int position = card.validate(getHouse());
            if (card.isDefensive() && position > -1) {
                hand.remove(i);
                return new Move(getID(), card, getID(), position);
            }
        }

        // Index of players in order of priority
        int[] priorities = behavior.organizeThreats(Game.getPlayers());

        for (int i = 0; i < Game.getPlayers().length; i++) {
            Player target = Game.getPlayers()[ priorities[i] ];

            for (int j = 0; j < hand.size(); j++) {
                Card card = hand.get(j);
                int position = card.validate(target.getHouse());
                if ( ! card.isDefensive() && position > -1) {
                    System.out.println(hand.size());
                    hand.remove(j);
                    System.out.println(hand.size());
                    return new Move(getID(), card, target.getID(), position);
                }
            }
        }

        // No moves left. Discard a card.
        Card discardCard = hand.get(0);
        hand.remove(0);
        return new Move(getID(), discardCard, Move.DISCARD_PILE, 0);
    }

    public int[] getPriorities(Player[] players) {
        return behavior.organizeThreats(players);
    }
}
