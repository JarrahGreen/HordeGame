package org.example.entity;

import org.example.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class Entity {
    public double x, y;
    public int speed;
    public BufferedImage image;
    public int width;
    public int height;

    public Entity(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void update(double dt) {
        if (x < 0) { x = 0; }
        if (x + width > GamePanel.screenWidth) { x = GamePanel.screenWidth - width; }
        if (y < 0) { y = 0; }
        if (y + height > GamePanel.screenHeight) { y = GamePanel.screenHeight - height; }
    }

    public boolean collidesWith(Entity target) {
        return x < target.x + target.width && x + width > target.x
                && y < target.y + target.height && y + height > target.y;
    }

    public abstract void draw(Graphics g);

    public boolean hit(Entity enemyRectangle) {
        return false;
    }

}