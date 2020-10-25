package de.gamingcraft.client.colors;

import de.gamingcraft.client.Colors;

import java.awt.*;

public class LightMode implements Colors {
    public String name() {
        return "LightMode";
    }

    public Color primary() {
        return new Color(200, 200, 200);
    }

    public Color text() {
        return new Color(0, 0, 0);
    }

    public Color background() {
        return new Color(230, 230, 230);
    }
}
