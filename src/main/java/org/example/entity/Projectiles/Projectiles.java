package org.example.entity.Projectiles;

import org.example.entity.Entity;

import java.awt.*;

public class Projectiles extends Entity {
    public final int radius = 10;
    Color color = Color.cyan;

    public Projectiles(double x, double y) {
        super(x, y);
    }

    public void update() {}

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillOval((int)(x + 20), (int)(y + 20), radius, radius);
    }


}
