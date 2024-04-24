package org.example.entity.powerup;

import org.example.entity.Entity;
import org.example.entity.Projectiles.Bullet;

import java.awt.*;

public class Grenade extends Powerup{

    public Grenade(int x, int y) {
        super(x, y);
        color = Color.GREEN;
    }
    @Override
    public void collect(Entity e) {
        Bullet.diameter = 1000;

    }
}
