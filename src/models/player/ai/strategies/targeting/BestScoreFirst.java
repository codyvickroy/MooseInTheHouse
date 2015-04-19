package models.player.ai.strategies.targeting;

import models.player.Player;

/**
 * Created by brandt on 3/30/15.
 */
public class BestScoreFirst extends TargetStrategy {

    public BestScoreFirst(Player[] players) {
        super(players);
    }

    @Override
    public int[] prioritize() {
        int[] order = new int[players.length];

        // Organize threats in non-ascending points order.
        for (int i = 1; i < players.length; i++) {
            int j = i;
            while (j > 0 && players[j - 1].getPoints() > players[i].getPoints()) {
                order[j] = order[j - 1];
                j--;
            }
            order[j] = i;
        }

        // Swaps index for player id
        for (int i = 0; i < order.length; i++) {
            order[i] = players[order[i]].getID();
        }

        return order;
    }
}
