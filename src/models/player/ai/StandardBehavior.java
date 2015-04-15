package models.player.ai;

import models.player.Player;

/**
 * Created by brandt on 3/23/15.
 */
public class StandardBehavior implements Behavior {

    @Override
    public int[] organizeThreats(Player[] players) {

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

            return order;
    }
}
