package models.player.ai.strategies.targeting;

import models.player.Player;

/**
 * Created by brandt on 3/30/15.
 */
public class FirstComeFirstServed extends TargetStrategy {

    public FirstComeFirstServed(Player[] players) {
        super(players);
    }

    @Override
    public int[] prioritize() {
        int[] threats = new int[players.length];

        for (int i = 0; i < players.length; i++) {
            threats[i] = players[i].getID();
        }

        return threats;
    }
}
