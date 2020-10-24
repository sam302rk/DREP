package de.gamingcraft.client;

import javax.swing.*;
import java.awt.*;

public class DREPClient {
    public static void run(boolean minimal, Colors colorSet) {
        JFrame frame = new JFrame("DREP Beta");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int width = 1200, height = 800;

        if(minimal) {
            width = 600;
            height = 400;
        }

        frame.setPreferredSize(new Dimension(width, height));
        frame.pack();
        frame.setVisible(true);
        frame.getContentPane().setBackground(colorSet.background());
    }
}
