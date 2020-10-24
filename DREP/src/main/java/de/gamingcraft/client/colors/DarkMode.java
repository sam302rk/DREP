package de.gamingcraft.client.colors;

import de.gamingcraft.client.Colors;

import java.awt.*;

public class DarkMode implements Colors {
    public String name() {
        return "DarkMode";
    }

    public Color primary() {
        return new Color(43, 43, 44);
    }

    public Color text() {
        return new Color(255, 255, 255);
    }

    public Color background() {
        return new Color(63, 63, 64);
    }

    public Color inactiveText() {
        return new Color(49, 51, 53);
    }

    public Color inactivePrimary() {
        return new Color(60, 59, 59);
    }
}
