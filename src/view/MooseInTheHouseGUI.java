package view;

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

public class MooseInTheHouseGUI extends JPanel implements CardObserver {
    //Define the panels needed to hold the cards, and houses
    JPanel c1cardPanel = new JPanel();
    JPanel c1housePanel = new JPanel();
    JPanel c2cardPanel = new JPanel();
    LayoutManager c2cardPanelLayout = new BoxLayout(c2cardPanel, BoxLayout.Y_AXIS);
    JPanel c2housePanel = new JPanel();
    JPanel c3cardPanel = new JPanel();
    LayoutManager c3cardPanelLayout = new BoxLayout(c3cardPanel, BoxLayout.Y_AXIS);
    JPanel c3housePanel = new JPanel();
    DragPanel pcardPanel = new DragPanel();
    JPanel phousePanel = new JPanel();
    JPanel deckPanel = new JPanel();

    //Add a mouse listener to enable dragging
    MouseListener handler = new Handler();
    //Add a LabelTransferHandler to enable dragging AND dropping
    LabelTransferHandler th = new LabelTransferHandler();


    //Define the labels to label which cards are whose
    JLabel c1cardlabel = new JLabel();
    JLabel c2cardlabel = new JLabel();
    JLabel c3cardlabel = new JLabel();
    JLabel pcardlabel = new JLabel();
    JLabel c1houselabel = new JLabel();
    JLabel c2houselabel = new JLabel();
    JLabel c3houselabel = new JLabel();
    JLabel phouselabel = new JLabel();

    //Define the hand of cards for each player
    //Hand c1 = new Hand();
    //Hand c2 = new Hand();
    //Hand c3 = new Hand();
    //Hand player = new Hand();

    //Whatever the game needs for input?
    //Game ngame = new Game(c1, c2, c3, player);

    //Define the labels of the cards for the game
    JLabel playercard1;
    JLabel playercard2;
    JLabel playercard3;
    JLabel playercard4;
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
    JLabel deck;


    private static ImageIcon createImageIcon(String path,String description) {
        java.net.URL imgURL = MooseInTheHouseGUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

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

        //Set layout
        c2cardPanel.setLayout(c2cardPanelLayout);
        c2cardPanel.add(Box.createRigidArea(new Dimension(20,0)));

        c3cardPanel.setLayout(c3cardPanelLayout);
        c3cardPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));

        c1housePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        c2housePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        c3housePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        phousePanel.setBorder(BorderFactory.createLineBorder(Color.black));

        //Set the text for each label
        c1cardlabel.setText(" Computer 1 Cards: ");
        //c2cardlabel.setText(" Computer 2 Cards: \n");
        //c3cardlabel.setText(" Computer 3 Cards: ");
        pcardlabel.setText(" Player Cards: ");

        c1houselabel.setText(" Computer 1 House: ");
        c2houselabel.setText(" Computer 2 House: ");
        c3houselabel.setText(" Computer 3 House: ");
        phouselabel.setText(" Player House: ");

        //Add labels to JPanel
        c1cardPanel.add(c1cardlabel);
        c1housePanel.add(c1houselabel);
        c2cardPanel.add(c2cardlabel);
        c2housePanel.add(c2houselabel);
        c3cardPanel.add(c3cardlabel);
        c3housePanel.add(c3houselabel);
        pcardPanel.add(pcardlabel);
        phousePanel.add(phouselabel);
        
        //Add back images to each hand
        c1card1 = new JLabel(new ImageIcon("back.png"));
        c1card2 = new JLabel(new ImageIcon("back.png"));
        c1card3 = new JLabel(new ImageIcon("back.png"));
        c1card4 = new JLabel(new ImageIcon("back.png"));
        c1cardPanel.add(c1card1);
        c1cardPanel.add(c1card2);
        c1cardPanel.add(c1card3);
        c1cardPanel.add(c1card4);


        c2card1 = new JLabel(new ImageIcon("back.png"));
        c2card2 = new JLabel(new ImageIcon("back.png"));
        c2card3 = new JLabel(new ImageIcon("back.png"));
        c2card4 = new JLabel(new ImageIcon("back.png"));
        c2cardPanel.add(c2card1);
        c2cardPanel.add(c2card2);
        c2cardPanel.add(c2card3);
        c2cardPanel.add(c2card4);

        c3card1 = new JLabel(new ImageIcon("back.png"));
        c3card2 = new JLabel(new ImageIcon("back.png"));
        c3card3 = new JLabel(new ImageIcon("back.png"));
        c3card4 = new JLabel(new ImageIcon("back.png"));
        c3cardPanel.add(c3card1);
        c3cardPanel.add(c3card2);
        c3cardPanel.add(c3card3);
        c3cardPanel.add(c3card4);

        playercard1 = new JLabel(new ImageIcon("card1.png"));
        playercard2 = new JLabel(new ImageIcon("card2.png"));
        playercard3 = new JLabel(new ImageIcon("card3.png"));
        playercard4 = new JLabel(new ImageIcon("card4.png"));
        pcardPanel.add(playercard1);
        pcardPanel.add(playercard2);
        pcardPanel.add(playercard3);
        pcardPanel.add(playercard4);

        //deck = new JLabel(new ImageIcon("back.png"));
        //deckPanel.add(deck);

        //enable all houses to be dragged to
        phousePanel.setTransferHandler(th);
        c1housePanel.setTransferHandler(th);
        c2housePanel.setTransferHandler(th);
        c3housePanel.setTransferHandler(th);
        pcardPanel.setTransferHandler(th);

        //enable the user to drag from their cards
        pcardPanel.addMouseListener(handler);

        //Create layout and assign areas for JPanels
        setLayout(new BorderLayout());
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
        add(deckPanel,BorderLayout.CENTER);
    }

    //Show the screen
    public void display()
    {
        GameGUI mithFrame = new GameGUI();
        mithFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mithFrame.setContentPane(this);
        mithFrame.setPreferredSize(new Dimension(1200,900));

        //Display
        mithFrame.pack();
        mithFrame.setVisible(true);

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

    @Override
    public void updateHands(){

    }

    @Override
    public void updateHouses(){

    }

    @Override
    public void updateDeck(){

    }

    @Override
    public void updateDiscardPile(){

    }



}