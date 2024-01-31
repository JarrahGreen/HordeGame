package org.example.entity.Projectiles;

import org.example.entity.Entity;
import org.example.entity.PlayerOne;

import java.awt.*;

public class Projectiles extends Entity {
    public final int radius = 10;
    Color color;

    Projectiles() {

    }

    public void hit(PlayerOne playerOne) {}

    public void shoot() {}


    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillOval(x, y, radius, radius);
    }


}
