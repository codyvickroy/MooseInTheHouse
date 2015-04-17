package models.player.ai.threatAlgorithm;

import models.player.Player;

/**
 * Created by brandt on 3/30/15.
 */
public class FirstComeFirstServed implements ThreatAlgorithm {
    @Override
    public int[] threatAlgorithm(Player[] players) {

        int[] threats = new int[players.length];

        for (int i = 0; i < players.length; i++) {
            threats[i] = players[i].getID();
        }

        return threats;
    }
}
