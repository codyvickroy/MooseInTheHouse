package models.game;

/**
 * Created By Software Engineering Team 9
 * UVM
 * Last Updated March 22nd, 2015
 * First Created March 13th, 2015
 */

import models.card.Card;
import models.card.bottom.*;
import models.card.top.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    List<Card> cards = new ArrayList<Card>();
    List<Card> discard = new ArrayList<Card>();

    /*
     * Default constructor builds a deck containing,
     * 20 Empty Room cards (5 each of Kitchen, Bathroom, Living Room
	 * and Bedroom)
	 * 20 Moose in the Room cards (same as above, but with Moose)
	 * 10 Theres a Moose in the House cards
	 * 5 Door cards
	 * 3 Moose Bait cards
     */
    Deck(){
        for(int i = 1 ; i <= 5 ; i++){                                  //Loops though 5 times so it adds a card of each type each pass

            cards.add(new EmptyBathroom());                             //Empty room cards
            cards.add(new EmptyLivingRoom());
            cards.add(new EmptyBedroom());
            cards.add(new EmptyKitchen());

            cards.add(new MooseInBathroom());                          //moose room cards
            cards.add(new MooseInLivingRoom());
            cards.add(new MooseInBedroom());
            cards.add(new MooseInKitchen());

            cards.add(new Moose());                                    //creates two moose cards per loop though
            cards.add(new Moose());

            cards.add(new Door());                                     //Door cards

        }//end for i <= 5


        for(int i = 1 ; i <= 3 ; i++){                                //seperate loop for Moose Bait beacuse there is only 3
            cards.add(new MooseBait());
        }//end for i <= 3

        shuffle();
    } //end for i <= 5}//end Deck

    /*
     * Shuffles the deck for the game
     */
    private void shuffle(){
        Collections.shuffle(cards);
    }//end shuffle

    /*
     * deals the ammount of cards needed for a turn
     * @return cardsDelt
     */
    public Card[] deal(int count) {
        Card[] cardsDelt;

        if (count > cards.size()) {
            cardsDelt = new Card[cards.size()];  //makes an array of size count
        } else {
            cardsDelt = new Card[count];  //makes an array of size count
        }
        for(int i = 0; i < cardsDelt.length ; i++){
            cardsDelt[i] = cards.get(0);    //adds the bottom card of the deck to the array
            cards.remove(0);                //deletes the card from the deck
        }//end for count

        return cardsDelt;

    }//end deal

    /*
     * returns the size of the deck
     * @return cards
     */
    public int size(){
        int deckSize = cards.size();

        return deckSize;
    }//end size

    /*
     *  returns the main dec
     *  @return cards
     */
    public List<Card> getCards(){
        return cards;
    }//end getCards

    /*
     * returns the discard pile
     * @return discard
     */
    public List<Card> getDiscard(){
        return discard;
    }//end getCards

    /**
     * Places a card in the discard pile
     *
     * @param card
     */
    public void discard(Card card) {
        discard.add(card);
    }

}//end Deck Class