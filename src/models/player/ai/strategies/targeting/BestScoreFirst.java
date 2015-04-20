package models.player.ai.strategies.targeting;

import models.player.Player;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Prioritizes players by their score in ascending order.
 *
 * Note: If multiple bots are using this targeting strategy they will target the same
 * player until the scores change. This causes a lot of moves against one player in a row.
 */
public class BestScoreFirst extends TargetStrategy {

    @Override
    public Queue<Integer> prioritize(Player[] opponents) {
        int[] order = new int[opponents.length];

        // Organize threats in non-ascending points order.
        for (int i = 1; i < opponents.length; i++) {
            int j = i;
            while (j > 0 && opponents[j - 1].getPoints() > opponents[i].getPoints()) {
                order[j] = order[j - 1];
                j--;
            }
            order[j] = i;
        }

        Queue<Integer> results = new LinkedList<Integer>();

        // Swaps index for player id
        for (int index : order) {
            results.add(opponents[index].getID());
        }

        return results;
    }
}
