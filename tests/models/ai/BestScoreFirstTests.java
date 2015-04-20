package models.ai;

import junit.framework.TestCase;
import models.player.Bot;
import models.player.Player;
import models.player.ai.strategies.targeting.BestScoreFirst;

import java.util.Queue;

/**
 * Created by Brandt Newton on 4/17/2015.
 */
public class BestScoreFirstTests extends TestCase{

    Player[] players;
    BestScoreFirst bestScoreFirst = new BestScoreFirst();

    public void setUp() throws Exception {
        super.setUp();

        players = new Player[] {
                new Bot(),
                new Bot(),
                new Bot()
        };

        bestScoreFirst = new BestScoreFirst();
    }

    public void testThreatPrioritization_LowestPointsFirst_v1() {

        players[0].setPoints(2);
        players[1].setPoints(2);
        players[2].setPoints(1);

        Queue<Integer> priorities = bestScoreFirst.prioritize(players);

        assertEquals(3, (int) priorities.remove());
        assertEquals(1, (int) priorities.remove());
        assertEquals(2, (int) priorities.remove());
    }

    public void testThreatPrioritization_LowestPointsFirst_v2() {

        players[0].setPoints(1);
        players[1].setPoints(2);
        players[2].setPoints(1);

        Queue<Integer> priorities = bestScoreFirst.prioritize(players);

        assertEquals(1, (int) priorities.remove());
        assertEquals(3,(int) priorities.remove());
        assertEquals(2, (int) priorities.remove());
    }

    public void testThreatPrioritization_LowestPointsFirst_v3() {

        players[0].setPoints(3);
        players[1].setPoints(2);
        players[2].setPoints(1);

        Queue<Integer> priorities = bestScoreFirst.prioritize(players);

        assertEquals(3, (int) priorities.remove());
        assertEquals(2, (int) priorities.remove());
        assertEquals(1, (int) priorities.remove());
    }
}
