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

        if (!hit) {
            int diffX = x - playerX;
            int diffY = y - playerY;
            int total = diffX + diffY;
            double totalX = (double) diffX /total;
            double totalY = (double) diffY /total;

            double test1 = (x-= totalX);
            double test2 = (y-= totalY);

            if (test1 == 0 && test2 == 0) {
                System.out.println("hit");
                hit = true;
            }
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



