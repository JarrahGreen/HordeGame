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
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 2;
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

        if(keyH.wPressed) {
            y -= speed;
        }
        if(keyH.sPressed) {
            y += speed;
        }
        if(keyH.aPressed) {
            x -= speed;
        }
        if(keyH.dPressed) {
            x += speed;
        }
    }


    public void draw(Graphics2D g2, int tileSize){
        BufferedImage image = this.image;
        g2.drawImage(image, x, y, tileSize, tileSize, null);
    }
}
