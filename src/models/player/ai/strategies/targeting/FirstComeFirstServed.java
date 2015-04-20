package models.player.ai.strategies.targeting;

import models.player.Player;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Prioritizes players in the same order they were presented.
 */
public class FirstComeFirstServed extends TargetStrategy {

    @Override
    public Queue<Integer> prioritize(Player[] opponents) {

        Queue<Integer> results = new LinkedList<Integer>();

        for (Player player : opponents) {
            results.add(player.getID());
        }

        return results;
    }
}
