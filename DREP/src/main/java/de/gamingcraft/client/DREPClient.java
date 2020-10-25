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
                    if(selectRP.getSelectedItem().equals(c.getDeclaredMethod("getName").invoke(c.newInstance())
                            + " by " + c.getDeclaredMethod("getAuthor").invoke(c.newInstance()))) {
                        DiscordRP.run(c);
                        runRP.setEnabled(false);
                        return;
                    }
                }
            }catch (Exception e) { }
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
            lbl.setBounds(25, 100, 400, 30);

            frame.add(lbl);

            JTextField[] fields = {new JTextField("Top Text..."), new JTextField("Bottom Text..."), new JTextField("Big Image Text..."), new JTextField("Small Image Text...")};

            int x = 0;

            for (JTextField jtf : fields) {
                jtf.setForeground(colorSet.text());
                jtf.setBackground(colorSet.primary());
                jtf.setBounds(25, 125+(35*x), 575, 30);
                x++;
                frame.add(jtf);
            }

            JButton updateButton = new JButton("Update Values");

            updateButton.setBackground(colorSet.primary());
            updateButton.setForeground(colorSet.text());
            updateButton.setBounds(25, 265, 575, 30);

            updateButton.addActionListener((action) -> {
                try {
                    DiscordRP.updatePresence(fields[0].getText(), fields[1].getText(), fields[2].getText(), fields[3].getText());
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
