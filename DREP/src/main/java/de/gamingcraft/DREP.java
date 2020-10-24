package de.gamingcraft;

import de.gamingcraft.cli.DREPCLI;
import de.gamingcraft.client.Colors;
import de.gamingcraft.client.DREPClient;
import de.gamingcraft.client.colors.DarkMode;
import de.gamingcraft.client.colors.LightMode;
import de.gamingcraft.installer.DREPInstaller;
import joptsimple.*;

import java.util.ArrayList;

public class DREP {

    private static boolean cli, installer, minimal;
    private static String selectedDesign;

    private static ArrayList<Colors> existingColors = new ArrayList<Colors>();

    public static void main(String[] args) {
        OptionParser o = new OptionParser();

        o.accepts("cli");
        o.accepts("install");
        o.accepts("minimal");
        OptionSpec<String> design = o.accepts("design").withRequiredArg().defaultsTo("LightMode");

        OptionSet os = o.parse(args);

        cli = os.has("cli");
        installer = os.has("install");

        minimal = os.has("minimal");

        selectedDesign = design.value(os);

        existingColors.add(new LightMode());
        existingColors.add(new DarkMode());

        if(cli) {
            DREPCLI.run();
        }else if(installer) {
            DREPInstaller.run();
        }else {
            Colors selectedColors = new LightMode();

            for (Colors c : existingColors) {
                if(selectedDesign.equalsIgnoreCase(c.name())) {
                    selectedColors = c;
                }
            }

            DREPClient.run(minimal, selectedColors);
        }
    }
}
