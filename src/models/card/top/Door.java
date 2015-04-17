package models.card.top;

import models.card.Card;

/**
 * Created by Brandt Newton on 3/17/2015.
 */
public class Door extends TopCard {

    public Door() {
        super(null, "asdf", 5);
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
            if (house[i].isBottomCard() && !  Card.isMoose(house[i])) {
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
