package models.card.bottom;

import models.card.Card;

/**
 * Created by brandt on 3/13/15.
 */
public abstract class BottomCard extends Card {

    public BottomCard(CardClass cardClass, String imagePath) {
        super(cardClass, imagePath);
    }

    /**
     * Returns a new position if the player does not already have 3 empty rooms in their house.
     *
     * @param house     Cards in house
     * @return          Position card should be placed
     */
    @Override
    public int validate(Card[] house) {

        int emptyRoomCount = 0;

        for (Card card : house ){
            if (isBottomCard() && ! Card.isMoose(card)) {
                emptyRoomCount++;
            }
        }

        if (emptyRoomCount >= 3) {
            return -1;
        } else {
            return house.length;
        }
    }

    @Override
    public boolean isBottomCard() {
        return true;
    }

    @Override
    public boolean isDefensive() {
        return false;
    }
}
