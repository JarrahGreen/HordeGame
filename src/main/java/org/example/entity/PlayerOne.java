package org.example.entity;
import org.example.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class PlayerOne extends Entity{
    KeyHandler keyH;

    public PlayerOne(KeyHandler keyH) {
        super(100, 100);
        this.keyH = keyH;
        this.speed = 2;
        getPlayerImage();
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
        g2.drawImage(image, (int) x, (int) y, tileSize, tileSize, null);
    }
}
