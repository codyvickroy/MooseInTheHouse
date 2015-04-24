package view;

import remote.Remote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

/**
 * Class to create the menu bar.
 * Created by Kaila Gervais
 */


public class GameGUI extends JFrame
{
    MenuObserver menuObserver;

    static boolean isLogged = false;
    static String playerName = "";

    /**
     * Initialize GameGUI
     */
    public GameGUI() {

        initUI();
    }

    public void setMenuObserver(MenuObserver menuObserver) {
        this.menuObserver = menuObserver;
    }
    /**
     * Create the menu bar and set the paramters
     */
    private void initUI() {

        //Initialize
        createMenuBar();

        //Set parameters
        setTitle("Moose In The House Game");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Create menu bar and add the needed items.
     */
    private void createMenuBar() {

        //Create new JMenuBar named menubar
        JMenuBar menubar = new JMenuBar();

        //Top most menu items
        final JMenu file = new JMenu("File");

        //Submenu items, under file
        JMenuItem nMenuItem = new JMenuItem("New Game");
        final JMenuItem lMenuItem = new JMenuItem("Login");
        final JMenuItem rMenuItem = new JMenuItem("Register");
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

            file.add(rMenuItem);
            file.add(lMenuItem);

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
                        "\nHow to Play:\nAll players start the game with an invisible empty house in front of them on the playing surface." +
                        "\nAs the game progresses, players fill opponents' houses with rooms and then their rooms with moose" +
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

        /**
         * ActionListener for when New Game is selected.
         */
        nMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String[] buttons = {"Easy", "Medium", "Hard"};
                int messageType = JOptionPane.QUESTION_MESSAGE;
                int difficulty = JOptionPane.showOptionDialog(null, "Please select level: ", "New Game Options", 0, messageType, null, buttons, buttons[2]);
                String[] pbuttons = {"2", "3", "4", "AI ONLY"};
                int opponents = JOptionPane.showOptionDialog(null, "Please select number of players: ", "New Game Options", 0, messageType, null, pbuttons, pbuttons[2]);

                menuObserver.newGame(opponents + 2, difficulty);
            }
        });

        /**
         * ActionListener for when Login is selected.
         */
        lMenuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                JLabel username = new JLabel("Username");
                JTextField userName = new JTextField();
                JLabel password = new JLabel("Password");
                JTextField pword = new JPasswordField();
                Object[] input = {username, userName, password, pword};

                int result = JOptionPane.showConfirmDialog(null, input, "Login Information", JOptionPane.OK_CANCEL_OPTION);


                if (Remote.validUser(userName.getText(), pword.getText()))
                {
                    JOptionPane.showMessageDialog(null, "User Logged in", "User Logged in", JOptionPane.PLAIN_MESSAGE);
                    isLogged = true;
                    playerName = userName.getText();
                    file.remove(rMenuItem);
                    file.remove(lMenuItem);
                    setTitle("Moose In The House Game | " + playerName);

                }
                else {
                    JOptionPane.showMessageDialog(null, "Failed to login", "Failed to log in", JOptionPane.ERROR_MESSAGE);
                }



            }
        });

        /**
         * ActionListener for when Register is selected.
         */
        rMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JLabel username = new JLabel("Username");
                JTextField userName = new JTextField();
                JLabel password = new JLabel("Password");
                JTextField pword = new JPasswordField();
                JLabel email = new JLabel("Email");
                JTextField mail = new JTextField();
                Object[] input = {username, userName, password, pword, email, mail};
                int result = JOptionPane.showConfirmDialog(null, input, "Register Information", JOptionPane.OK_CANCEL_OPTION);

                if (Remote.registerUser(userName.getText(), pword.getText(), mail.getText()))
                {
                    JOptionPane.showMessageDialog(null, "User Registered", "User Registered", JOptionPane.PLAIN_MESSAGE);


                } else {
                    JOptionPane.showMessageDialog(null, "Failed To register", "Failed To register", JOptionPane.ERROR_MESSAGE);
                }
        }
    });

        /**
         * Action listener to perform event when statistics menu item is selected.
         */
        sMenuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                    try {
                       desktop.browse(new URI(Remote.getMasterServer()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        setJMenuBar(menubar);
    }
    /**
     * Function to get the active player logged in.
     */
    public static String getPlayerName()
    {
        return playerName;
    }
    /**
     * Function to check for an active player.
     */
    public static Boolean isPlayerLogged()
    {
        return isLogged;
    }

    /**
     * Main function to display the menu.
     * @param args
     */
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                GameGUI ex = new GameGUI();
                ex.setVisible(true);
            }
        });
    }
}



