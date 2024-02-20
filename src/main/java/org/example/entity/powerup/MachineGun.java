package org.example.entity.powerup;

import org.example.entity.Entity;
import org.example.entity.Player;

import java.awt.*;


public class MachineGun extends Powerup{

    public MachineGun(int x, int y) {
        super(x, y);
        color = Color.yellow;

    }

    @Override
    public void collect(Entity e) {
        Player.maxCoolDown -= 30;


    }
}
