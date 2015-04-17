package models.player.ai.threatAlgorithm;

import models.player.Player;

/**
 * Created by brandt on 3/30/15.
 */
public interface ThreatAlgorithm {
    public int[] threatAlgorithm(Player[] players);
}
