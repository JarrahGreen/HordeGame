package org.example.entity.powerup;

import org.example.entity.Entity;

import java.awt.*;

public abstract class Powerup extends Entity {
     public static final int diameter = 35;

    Color color;

    Powerup(int x, int y) {
        super(x, y, diameter, diameter);
    }


    public abstract void collect(Entity e);

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int) x, (int) y, diameter, diameter);
    }
}
