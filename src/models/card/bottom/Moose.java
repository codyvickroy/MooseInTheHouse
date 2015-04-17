package models.card.bottom;

import models.card.Card;

/**
 * Created by Brandt Newton on 3/17/2015.
 */
public class Moose extends BottomCard {
    public Moose() {
        super(CardClass.MOOSE, "asdf", 4);
    }

    /**
     * Returns a new position if the player does not already have a moose in their house.
     *
     * @param house     Cards in house
     * @return          Position card should be placed
     */
    @Override
    public int validate(Card[] house) {
        for (Card card : house) {
            if (Card.isMoose(card)) {
                return INVALID_POSITION;
            }
        }
        return house.length;
    }
}
