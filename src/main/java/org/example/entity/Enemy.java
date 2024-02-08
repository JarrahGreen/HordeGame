package org.example.entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Enemy extends Entity{

    public Enemy(int x, int y) {
        super(x, y);
        this.speed = 2;
        getEnemyImage();

    }

    public void updateValues(double playerX, double playerY) {

        if (false) {
            double diffX = playerX - x;
            double diffY = playerY - y;

            double angle = Math.atan2(diffY, diffX);

            double diffX2 = Math.cos(angle) * speed;
            double diffY2 = Math.sin(angle) * speed;
            x += (int) diffX2;
            y += (int) diffY2;
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
        g2.drawImage(image, (int) x, (int) y, tileSize, tileSize, null);
        }
    }





