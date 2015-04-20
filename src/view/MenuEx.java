package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class MenuEx extends JFrame {

    public MenuEx() {

        initUI();
    }

    private void initUI() {

        //Initialize
        createMenuBar();

        //Set parameters
        setTitle("Moose In The House Game");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMenuBar() {

        //Create new JMenuBar named menubar
        JMenuBar menubar = new JMenuBar();

        //Top most menu items
        JMenu file = new JMenu("File");

        //Submenu items, under file
        JMenuItem nMenuItem = new JMenuItem("New Game");
        JMenuItem sMenuItem = new JMenuItem("Game Statistics");
        JMenuItem eMenuItem = new JMenuItem("Exit Game");
        eMenuItem.setToolTipText("Exit game");

        //ActionListener for when Exit Game menuItem is clicked
        eMenuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                System.exit(0);
            }
        });

        //Add all submenu items to menu
        file.add(nMenuItem);
        file.add(sMenuItem);
        file.add(eMenuItem);
        menubar.add(file);

        //Top most menuItem Help
        JMenu help = new JMenu("Help");

        //Submenu under Help
        JMenuItem hmenuItem = new JMenuItem("Game Help");

        //ActionListener for when Game Help is selected
        hmenuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                String helpMSG = ("Object:\nThe object of the game is to keep moose out of the rooms of your house," +
                        "\nput moose into your opponents' houses, and have the fewest moose in your house when the last card is played.\n\n" +
                        "\nHow to Play:\nAll opponents start the game with an invisible empty house in front of them on the playing surface." +
                        "\nAs the game progresses, opponents fill opponents' houses with rooms and then their rooms with moose" +
                        "\nThe player to the left of the dealer goes first and play continues clockwise. Start each turn by drawing" +
                        "\none card from the face down deck. Then take ONE of the following actions:" +
                        "\n1. Play a There's a Moose in the House card on any opponent that doesn't already have one."+
                        "\n2. Play an empty room card on any opponent that has less than three empty rooms."+
                        "\n3. Play a matching Moose in the Room card on top of any opponent's empty room (providing they have a There's a Moose in the House card in front of them)."+
                        "\n4. Play a Door card on one of your empty rooms. If you can't play any of your cards, simply discard one into a face up pile next to the draw pile." +
                        "\nThis ends your turn, and play moves to the next player.");
                JOptionPane.showMessageDialog(null, helpMSG, "Moose In the House Help Menu", JOptionPane.PLAIN_MESSAGE);
            }
        });

        //Add menuItems to menu
        help.add(hmenuItem);
        menubar.add(help);

        setJMenuBar(menubar);
    }

    //Main function to display the created menu
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                MenuEx ex = new MenuEx();
                ex.setVisible(true);
            }
        });
    }
}



