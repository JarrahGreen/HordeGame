package org.example.entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Enemy extends Entity{

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        setDefaultValues();
        getEnemyImage();

    }


    public void setDefaultValues() {
        speed = 1;
    }


    public void updateValues(int playerX, int playerY) {

        int diffX = playerX - x;
        int diffY = playerY - y;
        float angle = (float) Math.atan2(diffY, diffX);

        if (playerX > x && playerY > y) {

            x += (int) (speed * Math.cos(angle));
            y += (int) (speed * Math.sin(angle));
        }

        if (playerX < x && playerY < y) {
            x -= (int) (speed * Math.cos(angle));
            y -= (int) (speed * Math.sin(angle));
        }

        else if (playerX > x) {
            x += speed;
        }
        else if (playerX < x) {
            x -= speed;
        }
        else if (playerY > y) {
            y += speed;
        }
        else if (playerY < y) {
            y -= speed;
        }
    }

    public void getEnemyImage() {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Enemy.png")));

        }catch(IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void draw(Graphics2D g2, int tileSize){
        BufferedImage image = this.image;
        g2.drawImage(image, x, y, tileSize, tileSize, null);
    }

}



