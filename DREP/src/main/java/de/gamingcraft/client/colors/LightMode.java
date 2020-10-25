package de.gamingcraft.client.colors;

import de.gamingcraft.client.Colors;

import java.awt.*;

public class LightMode implements Colors {
    public String name() {
        return "LightMode";
    }

    public Color primary() {
        return new Color(239, 239, 239);
    }

    public Color text() {
        return new Color(0, 0, 0);
    }

    public Color background() {
        return new Color(255, 255, 255);
    }

    public Color inactiveText() {
        return new Color(55, 55, 55);
    }

    public Color inactivePrimary() {
        return new Color(150, 150, 150);
    }
}
