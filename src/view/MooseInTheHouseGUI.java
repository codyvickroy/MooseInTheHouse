package view;

import models.card.Card;
import models.game.Game;
import models.player.Bot;
import models.player.Human;
import models.player.Player;
import models.player.ai.Behavior;

import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceMotionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * GUI class
 */
public class MooseInTheHouseGUI extends JPanel implements CardObserver {
    //Define the panels needed to hold the cards, and houses
    JPanel c1HandPanel = new JPanel();
    JPanel playerHousePanel = new JPanel();
    JPanel c2HandPanel = new JPanel();
    LayoutManager c2cardPanelLayout = new BoxLayout(c2HandPanel, BoxLayout.Y_AXIS);
    JPanel c2housePanel = new JPanel();
    LayoutManager c2housePanelLayout = new BoxLayout(c2housePanel, BoxLayout.Y_AXIS);
    JPanel c3HandPanel = new JPanel();
    LayoutManager c3cardPanelLayout = new BoxLayout(c3HandPanel, BoxLayout.Y_AXIS);
    JPanel c3housePanel = new JPanel();
    LayoutManager c3housePanelLayout = new BoxLayout(c3housePanel, BoxLayout.Y_AXIS);
    DragPanel pcardPanel = new DragPanel();
    JPanel c1HousePanel = new JPanel();
    JPanel deckPanel = new JPanel();

    Game game;

    //Add a mouse listener to enable dragging
    MouseListener handler = new Handler();
    //Add a LabelTransferHandler to enable dragging AND dropping
    LabelTransferHandler th = new LabelTransferHandler();


    //Define the labels to label which cards are whose
    JLabel c1cardlabel = new JLabel();
    JLabel c2cardlabel = new JLabel();
    JLabel c3cardlabel = new JLabel();
    JLabel pcardlabel = new JLabel();


    //Define the labels of the cards for the game
    final int HAND_SIZE = 4;
    final int HOUSE_SIZE = 4;

    JLabel[] playerHand = new JLabel[HAND_SIZE];
    JLabel[] c1Hand = new JLabel[HAND_SIZE];
    JLabel[] c2Hand = new JLabel[HAND_SIZE];
    JLabel[] c3Hand = new JLabel[HAND_SIZE];

    JLabel[] playerHouse = new JLabel[HOUSE_SIZE];
    JLabel[] c1House = new JLabel[HOUSE_SIZE];
    JLabel[] c2House = new JLabel[HOUSE_SIZE];
    JLabel[] c3House = new JLabel[HOUSE_SIZE];
    JLabel deck;
    JLabel discard;

    //Construct the playing screen
    public MooseInTheHouseGUI (){
        //Set the background color
        c1HandPanel.setBackground(new Color(255, 250, 245));
        playerHousePanel.setBackground(new Color(255, 250, 245));
        c2HandPanel.setBackground(new Color(255, 250, 245));
        c2housePanel.setBackground(new Color(255, 250, 245));
        c3HandPanel.setBackground(new Color(255, 250, 245));
        c3housePanel.setBackground(new Color(255, 250, 245));
        pcardPanel.setBackground(new Color(255, 250, 245));
        c1HousePanel.setBackground(new Color(255, 250, 245));
        deckPanel.setBackground(new Color(255, 250, 245));


        //Set layout for c2 and c3 will space between cards
        c2HandPanel.setLayout(c2cardPanelLayout);
        c2HandPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 10, 10));
        c2housePanel.setLayout(c2housePanelLayout);
        c2housePanel.setBorder(BorderFactory.createEmptyBorder(20,10,10,20));
        c3HandPanel.setLayout(c3cardPanelLayout);
        c3HandPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 20));
        c3housePanel.setLayout(c3housePanelLayout);
        c2housePanel.setBorder(BorderFactory.createEmptyBorder(20,10,10,20));



        //Set the name for each players area and add them to the JPanel
        c1cardlabel.setText(" Computer 1: ");
        pcardlabel.setText(" Player: ");
        c2cardlabel.setText(" Computer 2: ");
        c3cardlabel.setText(" Computer 3: ");
        c1HandPanel.add(c1cardlabel);
        c2HandPanel.add(c2cardlabel);
        c3HandPanel.add(c3cardlabel);
        pcardPanel.add(pcardlabel);


        //Add card areas to each JPanel player house
        for (int i = 0; i < playerHouse.length; i++) {
            playerHouse[i] = new JLabel(Card.getEmptyCard());
            playerHousePanel.add(playerHouse[i]);
        }

        for (int i = 0; i < c2House.length; i++) {
            c2House[i] = new JLabel(Card.getEmptyCard());
            c2housePanel.add(c2House[i]);
        }
        // Formatting
        c2House[0].setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        for (int i = 0; i < c3House.length; i++) {
            c3House[i] = new JLabel(Card.getEmptyCard());
            c3housePanel.add(c3House[i]);
        }
        // Formatting
        c3House[0].setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        for (int i = 0; i < c1House.length; i++) {
            c1House[i] = new JLabel(Card.getEmptyCard());
            c1HousePanel.add(c1House[i]);
        }

        // Hand
        for (int i = 0; i < playerHand.length; i++) {
            playerHand[i] = new JLabel(Card.getEmptyCard());
            pcardPanel.add(playerHand[i]);
        }

        for (int i = 0; i < c1Hand.length; i++) {
            c1Hand[i] = new JLabel(Card.getEmptyCard());
            c1HandPanel.add(c1Hand[i]);
        }

        for (int i = 0; i < c1Hand.length; i++) {
            c1Hand[i] = new JLabel(Card.getEmptyCard());
            c1HandPanel.add(c1Hand[i]);
        }

        for (int i = 0; i < c2Hand.length; i++) {
            c2Hand[i] = new JLabel(Card.getEmptyCard());
            c2HandPanel.add(c2Hand[i]);
        }
        c2Hand[0].setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        c2Hand[1].setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        c2Hand[2].setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        c2Hand[3].setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        for (int i = 0; i < c3Hand.length; i++) {
            c3Hand[i] = new JLabel(Card.getEmptyCard());
            c3HandPanel.add(c3Hand[i]);
        }
        c3Hand[0].setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        c3Hand[1].setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        c3Hand[2].setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        c3Hand[3].setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));


        deck = new JLabel(Card.getCardBack());
        deck.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        deckPanel.add(deck);

        discard = new JLabel(Card.getEmptyCard());
        discard.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        deckPanel.add(discard);

        //enable all houses to be dragged to
        c1HousePanel.setTransferHandler(th);
        playerHousePanel.setTransferHandler(th);
        c2housePanel.setTransferHandler(th);
        c3housePanel.setTransferHandler(th);
        pcardPanel.setTransferHandler(th);

        //enable the user to drag from their cards
        pcardPanel.addMouseListener(handler);

        //Create layout and assign areas for JPanels
        /*setLayout(new BorderLayout());
        JPanel c1All = new JPanel(new BorderLayout());
        c1All.add(c1HandPanel,BorderLayout.NORTH);
        c1All.add(playerHousePanel,BorderLayout.SOUTH);
        add(c1All,BorderLayout.NORTH);
        JPanel c2All = new JPanel(new BorderLayout());
        c2All.add(c2HandPanel,BorderLayout.WEST);
        c2All.add(c2housePanel,BorderLayout.EAST);
        add(c2All,BorderLayout.WEST);
        JPanel c3All = new JPanel(new BorderLayout());
        c3All.add(c3HandPanel,BorderLayout.EAST);
        c3All.add(c3housePanel,BorderLayout.WEST);
        add(c3All,BorderLayout.EAST);
        JPanel pAll = new JPanel(new BorderLayout());
        pAll.add(pcardPanel,BorderLayout.SOUTH);
        pAll.add(c1HousePanel,BorderLayout.NORTH);
        add(pAll,BorderLayout.SOUTH);
        add(deckPanel,BorderLayout.CENTER);*/

        setLayout(new BorderLayout());
        JPanel c2All = new JPanel(new BorderLayout());
        c2All.add(c2HandPanel,BorderLayout.WEST);
        c2All.add(c2housePanel,BorderLayout.EAST);
        add(c2All,BorderLayout.WEST);
        JPanel c3All = new JPanel(new BorderLayout());
        c3All.add(c3HandPanel,BorderLayout.EAST);
        c3All.add(c3housePanel, BorderLayout.WEST);
        add(c3All,BorderLayout.EAST);
        JPanel centerArea = new JPanel(new BorderLayout());
        JPanel c1All = new JPanel(new BorderLayout());
        c1All.add(c1HandPanel,BorderLayout.NORTH);
        c1All.add(playerHousePanel,BorderLayout.SOUTH);
        centerArea.add(c1All, BorderLayout.NORTH);
        JPanel pAll = new JPanel(new BorderLayout());
        pAll.add(pcardPanel,BorderLayout.SOUTH);
        pAll.add(c1HousePanel,BorderLayout.NORTH);
        centerArea.add(pAll, BorderLayout.SOUTH);
        centerArea.add(deckPanel, BorderLayout.CENTER);
        add(centerArea, BorderLayout.CENTER);





    }

    //Show the screen
    public void display()
    {
        GameGUI mithFrame = new GameGUI();
        mithFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mithFrame.setContentPane(this);
        mithFrame.setPreferredSize(new Dimension(1200, 900));

        //Display
        mithFrame.pack();
        mithFrame.setVisible(true);

        newGame(4, Behavior.EASY_AI);
    }

    /**
     * Call to start a new game
     * @param totalPlayerCount
     * @param difficultyLevel
     */
    public void newGame(int totalPlayerCount, int difficultyLevel) {

        Player[] players = new Player[totalPlayerCount];
//        players[0] = new Human();

        // Get AI level from game
        Behavior aiDifficulty = Behavior.getAI(difficultyLevel);

        // Create bots with specified difficulty level
        for (int i = 0; i < totalPlayerCount; i++) {
            players[i] = new Bot(aiDifficulty);
        }

        game = new Game(players);
        game.setCardObserver(this);
    }

    public static void main(String[] args){

        MooseInTheHouseGUI frame = new MooseInTheHouseGUI();
        frame.display();

    }

    class DragPanel extends JPanel {
        private UUID panelID;

        public DragPanel() {
            super();
            generateID();
        }

        private void generateID(){
            this.panelID = UUID.randomUUID();
        }

        public UUID getID(){
            return panelID;
        }
        public JLabel draggingLabel;
    }

    class Handler extends MouseAdapter {
        @Override public void mousePressed(MouseEvent e) {
            DragPanel p = (DragPanel)e.getSource();
            Component c = SwingUtilities.getDeepestComponentAt(p, e.getX(), e.getY());
            if(c!=null && c instanceof JLabel) {
                p.draggingLabel = (JLabel)c;
                p.getTransferHandler().exportAsDrag(p, e, TransferHandler.MOVE);
            }
        }
    }

    class LabelTransferHandler extends TransferHandler {
        private final DataFlavor localObjectFlavor;
        private final JLabel label = new JLabel() {
            @Override public boolean contains(int x, int y) {
                return false;
            }
        };

        private final JWindow window = new JWindow();
        public LabelTransferHandler() {
            //System.out.println("LabelTransferHandler");
            localObjectFlavor = new ActivationDataFlavor(
                    DragPanel.class, DataFlavor.javaJVMLocalObjectMimeType, "JLabel");
            window.add(label);
            window.setAlwaysOnTop(true);
            window.setBackground(new Color(0,true));
            DragSource.getDefaultDragSource().addDragSourceMotionListener(
                    new DragSourceMotionListener() {
                        @Override public void dragMouseMoved(DragSourceDragEvent dsde) {
                            Point pt = dsde.getLocation();
                            pt.translate(5, 5); // offset
                            window.setLocation(pt);
                        }
                    });
        }

        @Override protected Transferable createTransferable(JComponent c) {
            //System.out.println("createTransferable");
            DragPanel p = (DragPanel)c;
            JLabel l = p.draggingLabel;
            String text = l.getText();
            //TEST
            //if(text==null) {
            //    text = l.getIcon().toString();
            //}
            //return new StringSelection(text+"\n");
            final DataHandler dh = new DataHandler(c, localObjectFlavor.getMimeType());
            if(text==null) return dh;
            final StringSelection ss = new StringSelection(text+"\n");
            return new Transferable() {
                @Override public DataFlavor[] getTransferDataFlavors() {
                    ArrayList<DataFlavor> list = new ArrayList<DataFlavor>();
                    for(DataFlavor f:ss.getTransferDataFlavors()) {
                        if(f != null)
                            list.add(f);
                    }
                    for(DataFlavor f:dh.getTransferDataFlavors()) {
                        list.add(f);
                    }
                    return list.toArray(dh.getTransferDataFlavors());
                }
                public boolean isDataFlavorSupported(DataFlavor flavor) {
                    for (DataFlavor f: getTransferDataFlavors()) {
                        if (flavor.equals(f)) {
                            return true;
                        }
                    }
                    return false;
                }
                public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                    if(flavor.equals(localObjectFlavor)) {
                        return dh.getTransferData(flavor);
                    } else {
                        return ss.getTransferData(flavor);
                    }
                }
            };
        }

        @Override public boolean canImport(TransferSupport support) {
            if(!support.isDrop()) {
                return false;
            }
            return true;
        }

        @Override public int getSourceActions(JComponent c) {
            //System.out.println("getSourceActions");
            DragPanel p = (DragPanel)c;
            label.setIcon(p.draggingLabel.getIcon());
            label.setText(p.draggingLabel.getText());
            window.pack();
            Point pt = p.draggingLabel.getLocation();
            SwingUtilities.convertPointToScreen(pt, p);
            window.setLocation(pt);
            window.setVisible(true);
            return MOVE;
        }

        @Override public boolean importData(TransferSupport support) {
            //System.out.println("importData");
            if(!canImport(support)) return false;
            DragPanel target = (DragPanel)support.getComponent();
            try {
                DragPanel src = (DragPanel)support.getTransferable().getTransferData(localObjectFlavor);
                JLabel l = new JLabel();
                l.setIcon(src.draggingLabel.getIcon());
                l.setText(src.draggingLabel.getText());
                target.add(l);
                target.revalidate();
                return true;
            } catch(UnsupportedFlavorException ufe) {
                ufe.printStackTrace();
            } catch(java.io.IOException ioe) {
                ioe.printStackTrace();
            }
            return false;
        }

        @Override protected void exportDone(JComponent c, Transferable data, int action) {
            //System.out.println("exportDone");
            DragPanel src = (DragPanel)c;
            if(action == TransferHandler.MOVE) {
                src.remove(src.draggingLabel);
                src.revalidate();
                src.repaint();
            }
            src.draggingLabel = null;
            window.setVisible(false);
        }


    }

    /**
     * Loop through the contents of every players hand and display the card front or back
     * depending on what is appropriate.
     */
    @Override
    public void updateHands(){
        Player[] players = Game.getPlayers();

        for(int i = 0; i < 1; i++){
            Card[] hand = players[i].getHand();
            for(int j = 0; j < hand.length; j++){
                ImageIcon cardImage;
                if (players[i] instanceof Human) {
                    // Show front of card if user
                    cardImage = hand[j].getImage();
                } else {
                    // Show back of card if computer
                    cardImage = Card.getCardBack();
                }

                playerHand[j].setIcon(cardImage);
            }
        }
    }

    @Override
    public void updateHouses(){

        // User
        Card[] house = Game.getPlayers()[0].getHouse();
        for (int i = 0; i < house.length; i++) {
            playerHouse[i].setIcon(house[i].getImage());
        }
        for (int i = house.length; i < HOUSE_SIZE; i++) {
            playerHouse[i].setIcon(Card.getEmptyCard());
        }

        house = Game.getPlayers()[1].getHouse();
        for (int i = 0; i < house.length; i++) {
            c1House[i].setIcon(house[i].getImage());
        }
        for (int i = house.length; i < HOUSE_SIZE; i++) {
            c1House[i].setIcon(Card.getEmptyCard());
        }

        if (Game.getPlayers().length > 2) {
            house = Game.getPlayers()[2].getHouse();
            for (int i = 0; i < house.length; i++) {
                c2House[i].setIcon(house[i].getImage());
            }
            for (int i = house.length; i < HOUSE_SIZE; i++) {
                c2House[i].setIcon(Card.getEmptyCard());
            }
        }

        if (Game.getPlayers().length == 4) {
            house = Game.getPlayers()[3].getHouse();
            for (int i = 0; i < house.length; i++) {
                c3House[i].setIcon(house[i].getImage());
            }
            for (int i = house.length; i < HOUSE_SIZE; i++) {
                c3House[i].setIcon(Card.getEmptyCard());
            }
        }
    }

    @Override
    public void updateDeck(){
        ImageIcon deckImage;

        if (Game.getDeck().size() > 0) {
            deckImage = Card.getCardBack();
        } else {
            deckImage = Card.getEmptyCard();
        }

        deck.setIcon(deckImage);
    }

    @Override
    public void updateDiscardPile(){
        ImageIcon discardImage;

        Card topCard = Game.getDeck().getTopDiscard();

        if (topCard != null) {
            discardImage = topCard.getImage();
        } else {
            discardImage = Card.getEmptyCard();
        }

        discard.setIcon(discardImage);
    }

    @Override
    public void updatePoints() {

        Player[] players = Game.getPlayers();

        for (int i = 0; i < players.length; i++) {
            int points = players[i].getPoints();

            // TODO display points
        }
    }
}