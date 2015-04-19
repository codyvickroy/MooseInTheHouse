package models.card.bottom;

import models.card.Card;
import models.card.top.MooseInBathroom;
import models.card.top.MooseInBedroom;

/**
 * Created by brandt on 3/13/15.
 */
public abstract class BottomCard extends Card {

    public BottomCard(CardClass cardClass, String imagePath, int value) {
        super(cardClass, imagePath, value);
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
            if (card.isBottomCard() && ! Card.isMoose(card)) {
                emptyRoomCount++;
            }
        }

        if (emptyRoomCount >= 3) {
            return INVALID_POSITION;
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


    public static void main(String[] args) {
        Card[] hand = new Card[]{
            new EmptyBathroom(),
                new EmptyBedroom(),
                new MooseInBedroom(),
                new MooseInBathroom(),
        };

        Card playingCard = new EmptyBathroom();

        playingCard.validate(hand);
    }
}
