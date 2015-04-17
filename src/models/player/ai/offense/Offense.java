package models.player.ai.offense;

import models.game.Move;
import models.player.Player;

/**
 * Created by brandt on 3/27/15.
 */
public interface Offense {
    public Move chooseOffense(Player[] players, Player player);
}
