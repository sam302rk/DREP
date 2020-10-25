package de.gamingcraft;

import de.gamingcraft.client.*;
import de.gamingcraft.client.colors.*;
import joptsimple.*;
import java.util.ArrayList;

public class DREP {

    private static boolean minimal;
    private static String selectedDesign;

    private static ArrayList<Colors> existingColors = new ArrayList<Colors>();

    public static String workingDirectory = "C:\\DREP\\";

    public static void main(String[] args) throws Exception {
        OptionParser o = new OptionParser();

        o.accepts("minimal");
        OptionSpec<String> design = o.accepts("design").withRequiredArg().defaultsTo("LightMode");

        OptionSet os = o.parse(args);

        minimal = os.has("minimal");

        selectedDesign = design.value(os);

        existingColors.add(new LightMode());
        existingColors.add(new DarkMode());

        Colors selectedColors = new LightMode();

        for (Colors c : existingColors) {
            if(selectedDesign.equalsIgnoreCase(c.name())) {
                selectedColors = c;
            }
        }

        System.out.println("Arguments:");
        System.out.println("Theme selected=" + selectedColors.name());
        System.out.println("Minimal Mode=" + minimal);

        DREPClient.run(minimal, selectedColors);
    }
}
