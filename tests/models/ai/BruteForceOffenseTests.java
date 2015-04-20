package models.ai;

import junit.framework.TestCase;
import models.card.Card;
import models.card.bottom.*;
import models.card.top.Door;
import models.card.top.MooseInBathroom;
import models.card.top.MooseInBedroom;
import models.card.top.MooseInKitchen;
import models.player.Bot;
import models.player.Player;
import models.player.ai.strategies.offense.BruteForceOffense;


/**
 * Created by Brandt Newton on 3/25/2015.
 */
public class BruteForceOffenseTests extends TestCase {

    Player[] players;
    Bot player;
    BruteForceOffense bruteForceOffense;

    public void setUp() throws Exception {
        super.setUp();

        players = new Player[] {
                new Bot(null),
                new Bot(null),
                new Bot(null)
        };

        // Create bot with standard behavior
        player = new Bot();
        player.addCardsToHand(new Card[] {
                new MooseInBedroom(),
                new MooseInKitchen(),
                new MooseInBathroom(),
                new MooseInBedroom(),
                new Moose(),
        });

        // Initialize Player 0 with LOWEST POINTS and NO MOOSE
        players[0].setCardInHouse(0, new EmptyLivingRoom());
        players[0].setCardInHouse(1, new EmptyBathroom());
        players[0].setCardInHouse(2, new EmptyBedroom());
        players[0].setCardInHouse(3, new EmptyBathroom());
        players[0].setCardInHouse(4, new EmptyKitchen());
        players[0].setPoints(1);

        // Initialize Player 1 with HIGHEST POINTS and MOOSE
        players[1].setCardInHouse(0, new Moose());
        players[1].setCardInHouse(1, new Door());
        players[1].setCardInHouse(2, new EmptyBedroom());
        players[1].setCardInHouse(3, new EmptyKitchen());
        players[1].setPoints(3);

        // Initialize Player 2 with 2ND LOWEST POINTS and NO MOOSE
        players[2].setCardInHouse(0, new EmptyLivingRoom());
        players[2].setCardInHouse(1, new EmptyBathroom());
        players[2].setCardInHouse(2, new EmptyBedroom());
        players[2].setCardInHouse(3, new Moose());
        players[2].setPoints(2);
    }
}
