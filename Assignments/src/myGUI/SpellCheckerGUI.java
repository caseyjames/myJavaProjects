package myGUI;

import javax.swing.*;

/**
 * Created by Casey on 7/21/2014.
 */
public class SpellCheckerGUI extends JFrame {

    public static void main(String[] args) {
        JFrame topWindow = new JFrame();
        topWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        topWindow.setSize(800, 1000);
        int x = 1920 / 2 - 400;
        int y = 1080 / 2 - 525;
        topWindow.setLocation(x, y);
        GUI mainG = new GUI();
        topWindow.add(mainG);
        topWindow.setVisible(true);
    }
}
