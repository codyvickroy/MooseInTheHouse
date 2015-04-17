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
public class Behavior implements Defense, Discard, Offense, ThreatAlgorithm {

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

    public Offense getOffense() {
        return offense;
    }

    public void setOffense(Offense offense) {
        this.offense = offense;
    }

    public ThreatAlgorithm getThreatAlgorithm() {
        return threatAlgorithm;
    }

    public void setThreatAlgorithm(ThreatAlgorithm threatAlgorithm) {
        this.threatAlgorithm = threatAlgorithm;
    }

    public Defense getDefense() {
        return defense;
    }

    public void setDefense(Defense defense) {
        this.defense = defense;
    }

    public Discard getDiscard() {
        return discard;
    }

    public void setDiscard(Discard discard) {
        this.discard = discard;
    }

    @Override
    public Move chooseDefense(Player player) {
        return defense.chooseDefense(player);
    }

    @Override
    public Card chooseDiscard(Card[] hand) {
        return discard.chooseDiscard(hand);
    }

    @Override
    public Move chooseOffense(Player[] players, Player player) {
        return offense.chooseOffense(players, player);
    }

    @Override
    public int[] threatAlgorithm(Player[] players) {
        return threatAlgorithm.threatAlgorithm(players);
    }
}
