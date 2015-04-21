package view;

public interface CardObserver {

    /**
     * Updates the view with the contents of every player's hands.
     */
    public void updateHands();

    /**
     * Updates the view with the contents of every player's houses.
     */
    public void updateHouses();

    /**
     * Updates the view with the contents of the Deck.
     */
    public void updateDeck();

    /**
     * Updates the view with the contents of the discard pile.
     */
    public void updateDiscardPile();

    /**
     * Updates the view with each user's points.
     */
    public void updatePoints();
}