package models.card.top;

import models.card.Card;
import models.card.bottom.Moose;

/**
 * Created by Brandt Newton on 3/17/2015.
 */
public class Door extends TopCard {

    public Door() {
        super(null, "asdf");
    }

    /**
     * A Door may be played on any empty room (Bottom card & not a moose).
     *
     * @param house     Cards in house
     * @return          First position of empty room
     */
    @Override
    public int validate(Card[] house) {

        for (int i = 0; i < house.length; i++) {
            if (house[i].isBottomCard() && ! (house[i] instanceof Moose)) {
                return i;
            }
        }

        return INVALID_POSITION;
    }

    @Override
    public boolean isDefensive() {
        return true;
    }
}
