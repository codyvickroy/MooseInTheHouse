package models.player.ai.discard;

import models.card.Card;

/**
 * Created by Brandt Newton on 4/17/2015.
 */
public interface Discard {

    public Card chooseDiscard(Card[] hand);

}
