package models.player.ai.threatAlgorithm;

import models.player.Player;

/**
 * Created by brandt on 3/30/15.
 */
public class BestScoreFirst implements ThreatAlgorithm {

    @Override
    public Player[] threatAlgorithm(Player[] players) {

        // Organize threats in non-ascending points order.
        for (int i = 1; i < players.length; i++) {
            int j = i;

            Player temp = players[i];

            while (j > 0 && players[j - 1].getPoints() > temp.getPoints()) {
                players[j] = players[j - 1];
                j--;
            }
            players[j] = temp;
        }

        return players;
    }
}
