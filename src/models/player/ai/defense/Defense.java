package models.player.ai.defense;

import models.game.Move;
import models.player.Player;

/**
 * Created by brandt on 3/27/15.
 */
public interface Defense {

    public Move action(Player player);
}
