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

/**
 * GUI class
 */
public class MooseInTheHouseGUI extends JPanel implements CardObserver {
    //Define the panels needed to hold the cards, and houses
    JPanel c1cardPanel = new JPanel();
    JPanel c1housePanel = new JPanel();
    JPanel c2cardPanel = new JPanel();
    LayoutManager c2cardPanelLayout = new BoxLayout(c2cardPanel, BoxLayout.Y_AXIS);
    JPanel c2housePanel = new JPanel();
    LayoutManager c2housePanelLayout = new BoxLayout(c2housePanel, BoxLayout.Y_AXIS);
    JPanel c3cardPanel = new JPanel();
    LayoutManager c3cardPanelLayout = new BoxLayout(c3cardPanel, BoxLayout.Y_AXIS);
    JPanel c3housePanel = new JPanel();
    LayoutManager c3housePanelLayout = new BoxLayout(c3housePanel, BoxLayout.Y_AXIS);
    DragPanel pcardPanel = new DragPanel();
    JPanel phousePanel = new JPanel();
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
    JLabel c1card1;
    JLabel c1card2;
    JLabel c1card3;
    JLabel c1card4;
    JLabel c2card1;
    JLabel c2card2;
    JLabel c2card3;
    JLabel c2card4;
    JLabel c3card1;
    JLabel c3card2;
    JLabel c3card3;
    JLabel c3card4;
    JLabel[] comp1House = new JLabel[HOUSE_SIZE];
    JLabel c2house1;
    JLabel c2house2;
    JLabel c2house3;
    JLabel c2house4;
    JLabel c3house1;
    JLabel c3house2;
    JLabel c3house3;
    JLabel c3house4;
    JLabel phouse1;
    JLabel phouse2;
    JLabel phouse3;
    JLabel phouse4;
    JLabel deck;
    JLabel discard;

    //Construct the playing screen
    public MooseInTheHouseGUI (){
        //Set the background color
        c1cardPanel.setBackground(new Color(255, 250, 245));
        c1housePanel.setBackground(new Color(255, 250, 245));
        c2cardPanel.setBackground(new Color(255, 250, 245));
        c2housePanel.setBackground(new Color(255, 250, 245));
        c3cardPanel.setBackground(new Color(255, 250, 245));
        c3housePanel.setBackground(new Color(255, 250, 245));
        pcardPanel.setBackground(new Color(255, 250, 245));
        phousePanel.setBackground(new Color(255, 250, 245));
        deckPanel.setBackground(new Color(255, 250, 245));


        //Set layout for c2 and c3 will space between cards
        c2cardPanel.setLayout(c2cardPanelLayout);
        c2cardPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 10, 10));
        c2housePanel.setLayout(c2housePanelLayout);
        c2housePanel.setBorder(BorderFactory.createEmptyBorder(20,10,10,20));
        c3cardPanel.setLayout(c3cardPanelLayout);
        c3cardPanel.setBorder(BorderFactory.createEmptyBorder(5,10,10,20));
        c3housePanel.setLayout(c3housePanelLayout);
        c2housePanel.setBorder(BorderFactory.createEmptyBorder(20,10,10,20));



        //Set the name for each players area and add them to the JPanel
        c1cardlabel.setText(" Computer 1: ");
        pcardlabel.setText(" Player: ");
        c2cardlabel.setText(" Computer 2: ");
        c3cardlabel.setText(" Computer 3: ");
        c1cardPanel.add(c1cardlabel);
        c2cardPanel.add(c2cardlabel);
        c3cardPanel.add(c3cardlabel);
        pcardPanel.add(pcardlabel);


        //Add card areas to each JPanel player house
        for (int i = 0; i < comp1House.length; i++) {
            comp1House[i] = new JLabel(Card.getEmptyCard());
            c1housePanel.add(comp1House[i]);
        }

        c2house1 = new JLabel(new ImageIcon(getClass().getResource("/cards/empty.png")));
        c2house1.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        c2house2 = new JLabel(new ImageIcon(getClass().getResource("/cards/empty.png")));
        c2house3 = new JLabel(new ImageIcon(getClass().getResource("/cards/empty.png")));
        c2house4 = new JLabel(new ImageIcon(getClass().getResource("/cards/empty.png")));
        c2housePanel.add(c2house1);
        c2housePanel.add(c2house2);
        c2housePanel.add(c2house3);
        c2housePanel.add(c2house4);

        c3house1 = new JLabel(new ImageIcon(getClass().getResource("/cards/empty.png")));
        c3house1.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        c3house2 = new JLabel(new ImageIcon(getClass().getResource("/cards/empty.png")));
        c3house3 = new JLabel(new ImageIcon(getClass().getResource("/cards/empty.png")));
        c3house4 = new JLabel(new ImageIcon(getClass().getResource("/cards/empty.png")));
        c3housePanel.add(c3house1);
        c3housePanel.add(c3house2);
        c3housePanel.add(c3house3);
        c3housePanel.add(c3house4);

        phouse1 = new JLabel(new ImageIcon(getClass().getResource("/cards/empty.png")));
        phouse2 = new JLabel(new ImageIcon(getClass().getResource("/cards/empty.png")));
        phouse3 = new JLabel(new ImageIcon(getClass().getResource("/cards/empty.png")));
        phouse4 = new JLabel(new ImageIcon(getClass().getResource("/cards/empty.png")));
        phousePanel.add(phouse1);
        phousePanel.add(phouse2);
        phousePanel.add(phouse3);
        phousePanel.add(phouse4);


        //Add back images to each hand
        c1card1 = new JLabel(new ImageIcon(getClass().getResource("/cards/back.png")));
        c1card2 = new JLabel(new ImageIcon(getClass().getResource("/cards/back.png")));
        c1card3 = new JLabel(new ImageIcon(getClass().getResource("/cards/back.png")));
        c1card4 = new JLabel(new ImageIcon(getClass().getResource("/cards/back.png")));

        //Add cards to JPanel
        c1cardPanel.add(c1card1);
        c1cardPanel.add(c1card2);
        c1cardPanel.add(c1card3);
        c1cardPanel.add(c1card4);


        c2card1 = new JLabel(new ImageIcon(getClass().getResource("/cards/back.png")));
        c2card1.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        c2card2 = new JLabel(new ImageIcon(getClass().getResource("/cards/back.png")));
        c2card2.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        c2card3 = new JLabel(new ImageIcon(getClass().getResource("/cards/back.png")));
        c2card3.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        c2card4 = new JLabel(new ImageIcon(getClass().getResource("/cards/back.png")));
        c2card4.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        c2cardPanel.add(c2card1);
        c2cardPanel.add(c2card2);
        c2cardPanel.add(c2card3);
        c2cardPanel.add(c2card4);

        c3card1 = new JLabel(new ImageIcon(getClass().getResource("/cards/back.png")));
        c3card1.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        c3card2 = new JLabel(new ImageIcon(getClass().getResource("/cards/back.png")));
        c3card2.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        c3card3 = new JLabel(new ImageIcon(getClass().getResource("/cards/back.png")));
        c3card3.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        c3card4 = new JLabel(new ImageIcon(getClass().getResource("/cards/back.png")));
        c3card4.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        c3cardPanel.add(c3card1);
        c3cardPanel.add(c3card2);
        c3cardPanel.add(c3card3);
        c3cardPanel.add(c3card4);

        for (int i = 0; i < playerHand.length; i++) {
            playerHand[i] = new JLabel(Card.getEmptyCard());
            pcardPanel.add(playerHand[i]);
        }

        deck = new JLabel(Card.getCardBack());
        deck.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        deckPanel.add(deck);

        discard = new JLabel(Card.getEmptyCard());
        discard.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        deckPanel.add(discard);

        //enable all houses to be dragged to
        phousePanel.setTransferHandler(th);
        c1housePanel.setTransferHandler(th);
        c2housePanel.setTransferHandler(th);
        c3housePanel.setTransferHandler(th);
        pcardPanel.setTransferHandler(th);

        //enable the user to drag from their cards
        pcardPanel.addMouseListener(handler);

        //Create layout and assign areas for JPanels
        /*setLayout(new BorderLayout());
        JPanel c1All = new JPanel(new BorderLayout());
        c1All.add(c1cardPanel,BorderLayout.NORTH);
        c1All.add(c1housePanel,BorderLayout.SOUTH);
        add(c1All,BorderLayout.NORTH);
        JPanel c2All = new JPanel(new BorderLayout());
        c2All.add(c2cardPanel,BorderLayout.WEST);
        c2All.add(c2housePanel,BorderLayout.EAST);
        add(c2All,BorderLayout.WEST);
        JPanel c3All = new JPanel(new BorderLayout());
        c3All.add(c3cardPanel,BorderLayout.EAST);
        c3All.add(c3housePanel,BorderLayout.WEST);
        add(c3All,BorderLayout.EAST);
        JPanel pAll = new JPanel(new BorderLayout());
        pAll.add(pcardPanel,BorderLayout.SOUTH);
        pAll.add(phousePanel,BorderLayout.NORTH);
        add(pAll,BorderLayout.SOUTH);
        add(deckPanel,BorderLayout.CENTER);*/

        setLayout(new BorderLayout());
        JPanel c2All = new JPanel(new BorderLayout());
        c2All.add(c2cardPanel,BorderLayout.WEST);
        c2All.add(c2housePanel,BorderLayout.EAST);
        add(c2All,BorderLayout.WEST);
        JPanel c3All = new JPanel(new BorderLayout());
        c3All.add(c3cardPanel,BorderLayout.EAST);
        c3All.add(c3housePanel, BorderLayout.WEST);
        add(c3All,BorderLayout.EAST);
        JPanel centerArea = new JPanel(new BorderLayout());
        JPanel c1All = new JPanel(new BorderLayout());
        c1All.add(c1cardPanel,BorderLayout.NORTH);
        c1All.add(c1housePanel,BorderLayout.SOUTH);
        centerArea.add(c1All, BorderLayout.NORTH);
        JPanel pAll = new JPanel(new BorderLayout());
        pAll.add(pcardPanel,BorderLayout.SOUTH);
        pAll.add(phousePanel,BorderLayout.NORTH);
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
        mithFrame.setPreferredSize(new Dimension(1200,900));

        newGame(4, 1);

        //Display
        mithFrame.pack();
        mithFrame.setVisible(true);
    }

    /**
     * Call to start a new game
     * @param totalPlayerCount
     * @param difficultyLevel
     */
    public void newGame(int totalPlayerCount, int difficultyLevel) {

        Player[] players = new Player[totalPlayerCount];
        players[0] = new Human();

        // Get AI level from game
        Behavior aiDifficulty = Behavior.getAI(difficultyLevel);

        // Create bots with specified difficulty level
        for (int i = 1; i < totalPlayerCount; i++) {
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
        public DragPanel() {
            super();
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
                    cardImage = hand[j].getCardBack();
                }

                playerHand[j].setIcon(cardImage);
            }
        }
    }

    @Override
    public void updateHouses(){

        Player[] players = Game.getPlayers();

        for (int i = 0; i < players.length; i++) {
            Card[] house = players[i].getHouse();
            for (int j = 0; j < house.length; j++) {
                ImageIcon cardImage = house[j].getImage();
                // TODO display card in house
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