package org.example.entity.Projectiles;
import org.example.KeyHandler;
import org.example.entity.PlayerOne;

import java.awt.*;


public class Bullet extends Projectiles{

    KeyHandler keyH;

    public Bullet(KeyHandler keyH) {
        this.keyH = keyH;
        setDefaultValues();
        updateValues();
        shoot();
    }

    public void setDefaultValues() {
        color = Color.red;
        //x = x.PlayerOne;
        //y = y.PlayerOne;
    }

    public void updateValues() {
        shoot();
    }

    public void hit(PlayerOne playerOne) {}

    public void shoot() {
        if (keyH.bulletUp) {
            y+=10;
        }
        if (keyH.bulletDown) {
            y-=10;
        }
        if (keyH.bulletLeft) {
            x-=10;
        }
        if (keyH.bulletRight) {
            x+=10;
        }
    }

}
