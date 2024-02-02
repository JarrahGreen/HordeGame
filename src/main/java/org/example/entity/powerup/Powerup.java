package org.example.entity.powerup;

import org.example.entity.Entity;
import org.example.entity.PlayerOne;
import org.example.entity.PlayerTwo;

import java.awt.*;

public abstract class Powerup extends Entity {
    public final int radius = 35;

    Color color;

    Powerup(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void collect(PlayerOne player) {}
    public void collect(PlayerTwo player) {}

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillOval((int) x, (int) y, radius, radius);
    }
}
