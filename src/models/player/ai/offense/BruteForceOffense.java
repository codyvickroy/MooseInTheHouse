package models.player.ai.offense;

import models.card.Card;
import models.game.Move;
import models.player.Player;

/**
 * Created by brandt on 3/30/15.
 */
public class BruteForceOffense implements Offense{

    @Override
    public Move chooseOffense(Player[] players, Player player) {

        for (Player target : players) {
            for (int i = 0; i < player.getHand().length; i++) {

                Card card = player.getHand()[i];

                if ( ! card.isDefensive()) {

                    int position = card.validate(target.getHouse());

                    if (position > -1) {
//                        player.removeCardFromHand(i);
//                        return new Move(player.getID(), card, target.getID(), position);
                    }
                }
            }
        }

        return null;
    }
}
