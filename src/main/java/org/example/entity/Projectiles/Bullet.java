package org.example.entity.Projectiles;
import org.example.KeyHandler;
import org.example.entity.PlayerOne;

import java.awt.*;


public class Bullet extends Projectiles{

    KeyHandler keyH;

    public Bullet(KeyHandler keyH, int x, int y) {
        this.keyH = keyH;
        shoot();
        this.x = x;
        this.y = y;
    }

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
