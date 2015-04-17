package models.player.ai;

import models.card.Card;
import models.game.Move;
import models.player.Player;
import models.player.ai.defense.Defense;
import models.player.ai.discard.Discard;
import models.player.ai.offense.Offense;
import models.player.ai.threatAlgorithm.ThreatAlgorithm;

/**
 * Created by Brandt Newton on 4/17/2015.
 */
public class Behavior {

    private Offense offense;
    private ThreatAlgorithm threatAlgorithm;
    private Defense defense;
    private Discard discard;

    public Behavior(Offense offense, ThreatAlgorithm threatAlgorithm, Defense defense, Discard discard) {
        this.offense = offense;
        this.threatAlgorithm = threatAlgorithm;
        this.defense = defense;
        this.discard = discard;
    }

    public Move chooseDefense(Player player) {
        return defense.chooseDefense(player);
    }

    public Card chooseDiscard(Card[] hand) {
        return discard.chooseDiscard(hand);
    }

    public Move chooseOffense(Player[] players, Player player) {
        return offense.chooseOffense(players, threatAlgorithm(players), player);
    }

    public int[] threatAlgorithm(Player[] players) {
        return threatAlgorithm.threatAlgorithm(players);
    }
}
