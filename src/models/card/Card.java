package models.card;


import javax.swing.*;

/**
 * Created by brandt on 3/13/15.
 */
public abstract class Card {



    public static enum CardClass {
        MOOSE, BATHROOM, LIVINGROOM, BEDROOM, KITCHEN;
    }

    private int value;
    protected CardClass cardClass;
    private ImageIcon imageIcon;
    public static final int INVALID_POSITION = -1;
    private static final String CARD_IMAGE_PATH = "/cards/";

    public Card(CardClass cardClass, String imageName, int value) {
        this.cardClass = cardClass;
        imageIcon = new ImageIcon(getClass().getResource(CARD_IMAGE_PATH + imageName));
        this.value = value;
    }

    public CardClass getCardClass() {
        return cardClass;
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

    public boolean equals(Card card) {
        return (this.getCardClass() == card.getCardClass()) && ! (this.isBottomCard() ^ card.isBottomCard());
    }

    public int getValue() {
        return value;
    }

    /**
     * Returns an ImageIcon of the card.
     *
     * @return  card's image icon
     */
    public ImageIcon getImage() {
        return imageIcon;
    }

    /**
     * Returns an ImageIcon of the back of a card.
     * @return
     */
    public ImageIcon getCardBack() {
        return new ImageIcon(getClass().getResource(CARD_IMAGE_PATH + "back.png"));
    }
}
