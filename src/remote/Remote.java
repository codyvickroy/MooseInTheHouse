package remote;

import models.game.Game;
import models.game.Move;
import models.player.Player;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.Scanner;

public class Remote {

    public static Boolean initGame()
    {
        String inputLine ="";
        int size = Game.getPlayers().length;

        try{
            String site = getMasterServer() + "initGame.php?id=";
            site +=currentGameID();
            site +="&ip=";
            site +=getIP();
            //System.out.println(site);
            URL web = new URL(site);
            URLConnection gate = web.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(gate.getInputStream()));
            inputLine = in.readLine();
            //System.out.println(inputLine);
        }
        catch(Exception e)
        {
            error(e);
        }
        return Boolean.parseBoolean(inputLine);
    }

    public static Boolean uploadScores()
    {
        String inputLine ="";
        Player[] players = Game.getPlayers();
        int size = players.length;

        try{
            String site = getMasterServer() + "updateScores.php?id=";
            site +=currentGameID();
            if (size == 2)
            {
                site +="&p1=";
                site += players[0].getPoints();
                site +="&p2=";
                site += players[1].getPoints();
            }
            if (size == 3)
            {
                site +="&p1=";
                site += players[0].getPoints();
                site +="&p2=";
                site += players[1].getPoints();
                site +="&p3=";
                site += players[2].getPoints();

            }
            if (size == 4)
            {
                site +="&p1=";
                site += players[0].getPoints();
                site +="&p2=";
                site += players[1].getPoints();
                site +="&p3=";
                site += players[2].getPoints();
                site +="&p4=";
                site += players[3].getPoints();
            }

            //System.out.println(site);
            URL web = new URL(site);
            URLConnection gate = web.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(gate.getInputStream()));
            inputLine = in.readLine();
            //System.out.println(inputLine);
        }
        catch(Exception e)
        {
            error(e);
        }
        return Boolean.parseBoolean(inputLine);
    }

    public static Boolean uploadMove(Move move)
    {
        String inputLine ="";
        String Makemove = "" + move;
        Makemove = Makemove.replace(" ", "%");
        try{
            String site = getMasterServer() + "moveHistory.php?hist=" + Makemove;
            site +="&h=";
            site +=currentGameID();
            //System.out.println(site);
            URL web = new URL(site);
            URLConnection gate = web.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(gate.getInputStream()));
            inputLine = in.readLine();
            //System.out.println(inputLine);
        }
        catch(Exception e)
        {
            error(e);
        }
        return Boolean.parseBoolean(inputLine);
    }

    public static String newGameID()
    {
        String ID = sha1(Long.toString(System.currentTimeMillis()) + getIP());
        try
        {

            PrintWriter writer = new PrintWriter("game.ID", "UTF-8");
            writer.println(ID);
            writer.close();
        }
        catch(Exception e)
        {
            error(e);
        }
        return ID;
    }

    public static String currentGameID()
    {
        String ID = "";
        try{
            ID = new Scanner(new File("game.ID")).useDelimiter("\\Z").next();
        }
        catch(Exception e)
        {
            error(e);
        }
        return ID;
    }
    public static String getMasterServer()
    {
        String server = "";
        try{
            server = new Scanner(new File("server.ID")).useDelimiter("\\Z").next();
        }
        catch(Exception e)
        {
            error(e);
        }
        return server;
    }
    public static String sha1(String input)
    {
        try{
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(input.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < result.length; i++)
            {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
        catch(Exception e)
        {
            error(e);

        }
        return "err";
    }
    public static String getIP()
    {
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            String ip = in.readLine();
            return ip;
        }
        catch (Exception e)
        {
            error(e);
        }
        return "err";
    }
    public static void error(Exception e)
    {
        try{
            JWindow window = new JWindow();
            window.getContentPane().add(
                    new JLabel("", new ImageIcon(new URL("http://i.imgur.com/0HhBKxv.jpg")), SwingConstants.CENTER));
            window.setBounds(500, 150, 300, 200);
            window.setVisible(true);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException x) {
                x.printStackTrace();
            }
            window.setVisible(false);

            System.out.println(e);
        }
        catch (Exception b)
        {

        }
    }
}
