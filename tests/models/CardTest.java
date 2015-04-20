package models;

import junit.framework.TestCase;
import models.card.Card;
import models.card.bottom.*;
import models.card.top.*;
import models.card.bottom.Moose;

public class CardTest extends TestCase {

    Card[] houseWithMoose;
    Card[] houseEmpty;

    public void setUp() throws Exception {
        super.setUp();

        houseWithMoose = new Card[]{
                new EmptyBathroom(),
                new Moose(),
                new EmptyKitchen(),
                new MooseInBathroom(),
        };

        houseEmpty = new Card[]{
                new EmptyBathroom(),
                new EmptyLivingRoom(),
        };
    }


    // ======================
    // === Top Card Tests ===
    // ======================

    public void testMooseInRoomValidatesToCorrectPosition_v1() {
        // Tests valid-top-card condition
        Card card = new MooseInBathroom();
        int position = card.validate(houseWithMoose);

        assertEquals(0, position);
    }

    public void testMooseInRoomValidatesToCorrectPosition_v2() {
        // Tests valid-top-card condition
        Card card = new MooseInKitchen();
        int position = card.validate(houseWithMoose);

        assertEquals(2, position);
    }

    public void testMooseBaitValidatesToCorrectPosition() {
        Card card = new MooseBait();
        int position = card.validate(houseWithMoose);

        assertEquals(1, position);
    }

    public void testMooseInRoomDoesNotFindCorrectBottom() {
        // Tests valid-top-card condition
        Card card = new MooseInBedroom();
        int position = card.validate(houseWithMoose);

        assertEquals(-1, position);
    }

    public void testMooseInRoomWithNoMoose() {
        // Tests valid-top-card condition
        Card card = new MooseInBedroom();
        int position = card.validate(houseWithMoose);

        assertEquals(-1, position);
    }

    // =========================
    // === Bottom Card Tests ===
    // =========================

    public void testBottomCardSlotAssignment_v1() {
        Card card = new EmptyLivingRoom();
        int position = card.validate(houseEmpty);

        assertEquals(houseEmpty.length, position);
    }

    public void testBottomCardSlotAssignment_v2() {
        Card card = new Moose();
        int position = card.validate(houseEmpty);

        assertEquals(houseEmpty.length, position);
    }

    public void testDoorValidatesToFirstEmptyRoom() {
        Card card = new Door();
        int position = card.validate(houseWithMoose);

        assertEquals(0, position);
    }
}