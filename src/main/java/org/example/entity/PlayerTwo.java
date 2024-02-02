package org.example.entity;
import org.example.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class PlayerTwo extends Entity{
    KeyHandler keyH;

    public PlayerTwo(KeyHandler keyH) {
        super(200, 200);
        this.keyH = keyH;
        this.speed = 2;
        getPlayerImage();
    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void getPlayerImage() {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PlayerTwo.png")));

        }catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {

        if(keyH.upPressed) {
            y -= speed;
        }
        if(keyH.downPressed) {
            y += speed;
        }
        if(keyH.leftPressed) {
            x -= speed;
        }
        if(keyH.rightPressed) {
            x += speed;
        }
    }


    public void draw(Graphics2D g2, int tileSize){
        BufferedImage image = this.image;
        g2.drawImage(image, (int) x, (int) y, tileSize, tileSize, null);
    }
}
