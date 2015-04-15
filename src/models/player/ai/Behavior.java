package models.player.ai;

import models.player.Player;

/**
 * Created by brandt on 3/23/15.
 */
public interface Behavior {
    public abstract int[] organizeThreats(Player[] players);
}
