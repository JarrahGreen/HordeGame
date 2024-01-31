package org.example.entity;
import org.example.KeyHandler;
import org.example.entity.Projectiles.Bullet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class PlayerOne extends Entity{
    KeyHandler keyH;

    public PlayerOne(KeyHandler keyH) {
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 2;
    }

    public int getX(int x) {
        return x;
    }

    public int getY(int y) {
        return y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void getPlayerImage() {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PlayerOne.png")));

        }catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {

        if(keyH.wPressed && y > 0) {
            y -= speed;
        }
        if(keyH.sPressed && y < 528) {
            y += speed;
        }
        if(keyH.aPressed && x > 0) {
            x -= speed;

        }
        if(keyH.dPressed && x < 720) {
            x += speed;
        }
    }


    public void draw(Graphics2D g2, int tileSize){
        BufferedImage image = this.image;
        g2.drawImage(image, x, y, tileSize, tileSize, null);
        getY(y);
        getX(x);
    }
}
