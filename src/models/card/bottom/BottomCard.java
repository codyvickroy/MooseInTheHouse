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
     * Returns true.
     *
     * All bottom cards only require an empty space to be played. There is no limit to the number of cards that may be
     * played in any house.
     *
     * @param house     Cards in house
     * @return          Position card should be placed
     */
    @Override
    public int validate(Card[] house) {
        return house.length;
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
