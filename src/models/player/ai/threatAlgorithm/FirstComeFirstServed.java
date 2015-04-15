package models.player.ai.threatAlgorithm;

import models.player.Player;

/**
 * Created by brandt on 3/30/15.
 */
public class FirstComeFirstServed implements ThreatAlgorithm {
    @Override
    public Player[] threatAlgorithm(Player[] players) {
        return players;
    }
}
