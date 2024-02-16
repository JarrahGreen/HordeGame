package org.example.entity.Projectiles;

import org.example.entity.Entity;

import java.awt.*;

public class Projectiles extends Entity {
    public static final int diameter = 10;
    Color color = Color.cyan;
    public final Entity source;

    public Projectiles(double x, double y, Entity source) {
        super(x, y, diameter, diameter);
        this.source = source;
    }

    public void update() {}

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int)x, (int)y, diameter, diameter);
    }


}
