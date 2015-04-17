package models.player;

import models.card.Card;
import models.game.Game;
import models.game.Move;

import java.util.ArrayList;

public class Human extends Player {
    private boolean takingTurn;
    private Move move;

    public Human(int id, ArrayList<Card> hand) {
        super(id);

        takingTurn = false;
    }

    /**
     * Method waits for setMove to set a valid move.
     *
     * @return move made.
     */
    @Override
    public Move makeMove() {
        move = null;
        takingTurn = true;

        while(move == null) {

        }

        takingTurn = false;
        return move;
    }

    /**
     * Called by UI when a card is played.
     *
     * If the card is played at a valid position the move will be made and true is returned.
     * If the move is invalid false is returned.
     *
     * @param card      this player's card played
     * @param playerID  player to receive card
     * @return          validity of move
     */
    public boolean setMove(Card card, int playerID) {
        // TODO validate move
        if (takingTurn) {
            if (playerID == Move.DISCARD_PILE) {
                move = new Move(getID(), card, Move.DISCARD_PILE, 0);
            } else if (card.validate(Game.getPlayer(playerID).getHouse()) != Card.INVALID_POSITION) {
                move = new Move(getID(), card, playerID, card.validate(Game.getPlayer(playerID).getHouse()));
            }
        }

        return move != null;
    }

    public boolean isTakingTurn() {
        return takingTurn;
    }
}
