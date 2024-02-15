package org.example.entity.powerup;

import org.example.entity.Entity;
import org.example.entity.Player;

import java.awt.*;

public class FireSpeedUp extends Powerup {
    public FireSpeedUp(int x, int y) {
        super(x, y);
        color = Color.PINK;
    }

    @Override
    public void collect(Entity e) {
        if (e instanceof Player) {
            ((Player) e).maxCooldown -= 2;
        }
    }
}
