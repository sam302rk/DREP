package de.gamingcraft.client;

import de.gamingcraft.DREP;
import de.gamingcraft.DiscordRP;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class DREPClient {

    private static ArrayList<Class> classes = new ArrayList<Class>();

    public static void run(boolean minimal, Colors colorSet) throws Exception {
        JFrame frame = new JFrame("DREP Beta");

        int width = 645, height = 365;

        if(minimal) {
            height = 150;
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(width, height));
        frame.getContentPane().setLayout(null);

        JLabel selectRPText = new JLabel("Select Rich-Presence, you want to use:");
        selectRPText.setBounds(25, 5, 400, 30);
        selectRPText.setForeground(colorSet.text());

        ArrayList<Object> items = new ArrayList<Object>();

        System.out.println(System.getenv("ProgramFiles"));

        File rps = new File(DREP.workingDirectory + "richpresences\\");

        URL url = rps.toURI().toURL();
        URL[] urls = new URL[]{url};
        ClassLoader cl = new URLClassLoader(urls);

        for (File f : rps.listFiles()) {
            if(!f.isDirectory()) {
                classes.add(cl.loadClass(f.getName().replace(".class", "")));
            }
        }

        ((URLClassLoader)cl).close();

        for (Class c : classes) {
            items.add(c.getDeclaredMethod("getName").invoke(c.newInstance()) + " by " + c.getDeclaredMethod("getAuthor").invoke(c.newInstance()));
        }

        JComboBox selectRP = new JComboBox(items.toArray());
        selectRP.setBounds(25, 30, 400, 30);
        selectRP.setForeground(colorSet.text());
        selectRP.setBackground(colorSet.primary());

        JButton runRP = new JButton("Use this Rich-Presence");
        runRP.setBounds(425, 30, 175, 30);
        // 575 + 25 + 25
        runRP.setForeground(colorSet.text());
        runRP.setBackground(colorSet.primary());
        runRP.addActionListener((actionEvent) -> {
            try {
                for (Class c : classes) {
                    if(selectRP.getSelectedItem().equals(c.getDeclaredMethod("getName").invoke(c.newInstance()) + " by " + c.getDeclaredMethod("getAuthor").invoke(c.newInstance()))) {
                        DiscordRP.run(c);
                        return;
                    }
                }
            }catch (Exception e) {

            }

        });

        if(true) {
            frame.add(selectRPText);
            frame.add(selectRP);
            frame.add(runRP);
        }

        if(!minimal) {
            // Rest will come there.

            JLabel lbl = new JLabel("Edit Values");
            lbl.setForeground(colorSet.text());
            lbl.setBounds(25, 100 /*125*/, 400, 30);

            frame.add(lbl);

            JTextField topText = new JTextField("Top Text..."),
                    bottomText = new JTextField("Bottom Text..."),
                    bigImgText = new JTextField("Big Image Text..."),
                    smallImgText = new JTextField("Small Image Text...");

            topText.setForeground(colorSet.text());
            bottomText.setForeground(colorSet.text());
            bigImgText.setForeground(colorSet.text());
            smallImgText.setForeground(colorSet.text());

            topText.setBackground(colorSet.primary());
            bottomText.setBackground(colorSet.primary());
            bigImgText.setBackground(colorSet.primary());
            smallImgText.setBackground(colorSet.primary());

            topText.setBounds(25, 125, 575, 30);
            bottomText.setBounds(25, 125+35, 575, 30);
            bigImgText.setBounds(25, 125+70, 575, 30);
            smallImgText.setBounds(25, 125+105, 575, 30);

            frame.add(topText);
            frame.add(bottomText);
            frame.add(bigImgText);
            frame.add(smallImgText);

            JButton updateButton = new JButton("Update Values");

            updateButton.setBackground(colorSet.primary());
            updateButton.setForeground(colorSet.text());
            updateButton.setBounds(25, 125+140, 575, 30); // 125+140+35

            updateButton.addActionListener((action) -> {
                try {
                    DiscordRP.updatePresence(topText.getText(), bottomText.getText(), bigImgText.getText(), smallImgText.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            frame.add(updateButton);

        }

        frame.pack();

        frame.setVisible(true);
        frame.getContentPane().setBackground(colorSet.background());
    }
}
