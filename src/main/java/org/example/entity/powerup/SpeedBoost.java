package org.example.entity.powerup;

import org.example.entity.Entity;

import java.awt.*;

public class SpeedBoost extends Powerup {
    public SpeedBoost(int x, int y) {
        super(x, y);
        color = Color.BLUE;
    }

    @Override
    public void collect(Entity e) {
        e.speed += 1;
    }
}
