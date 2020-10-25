package de.gamingcraft.client.colors;

import de.gamingcraft.client.Colors;

import java.awt.*;

public class DarkMode implements Colors {
    public String name() {
        return "DarkMode";
    }

    public Color primary() {
        return new Color(52, 53, 55);
    }

    public Color text() {
        return new Color(255, 255, 255);
    }

    public Color background() {
        return new Color(60, 63, 65);
    }
}
