package models.card;


import javax.swing.*;

/**
 * Created by brandt on 3/13/15.
 */
public abstract class Card {

    private int value;

    public static enum CardClass {
        MOOSE, BATHROOM, LIVINGROOM, BEDROOM, KITCHEN
    }

    protected CardClass cardClass;
    private String imagePath;
    public static final int INVALID_POSITION = -1;

    public Card(CardClass cardClass, String imagePath, int value) {
        this.cardClass = cardClass;
        this.imagePath = imagePath;
    }

    public CardClass getCardClass() {
        return cardClass;
    }

    public String getImagePath() {
        return imagePath;
    }

    /**
     * Validates moving this card into the specified house.
     *
     * Returns a number greater than the length of house to be placed in an empty space.
     *
     * Returns -1 if no position available.
     *
     * @param house     Cards in house
     * @return          Position card should be placed.
     */
    public abstract int validate(Card[] house);

    public abstract boolean isBottomCard();

    public abstract boolean isDefensive();

    public static boolean isMoose(Card card) {
        return card.isBottomCard() && card.getCardClass() == CardClass.MOOSE;
    }

    @Override
    public String toString() {

        if (cardClass == null) {
            return "Door";
        }

        switch(cardClass) {
            case MOOSE:
                return ((isBottomCard()? "Moose" : "Moose bait"));
            case BATHROOM:
                return ((isBottomCard()? "Empty" : "Moose in")) + " Bathroom";
            case LIVINGROOM:
                return ((isBottomCard()? "Empty" : "Moose in")) + " Livingroom";
            case BEDROOM:
                return ((isBottomCard()? "Empty" : "Moose in")) + " Bedroom";
            case KITCHEN:
                return ((isBottomCard()? "Empty" : "Moose in")) + " Kitchen";
        }

        return "CARD NOT FOUND";
    }

    public int getValue() {
        return value;
    }

    public ImageIcon getImage() {
        return new ImageIcon(imagePath);
    }

    public ImageIcon getCardBack() {
        return new ImageIcon("/cards/back.png");
    }
}
