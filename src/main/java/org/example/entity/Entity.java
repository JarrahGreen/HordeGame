package org.example.entity;

import java.awt.image.BufferedImage;


public abstract class Entity {
    public double x, y;
    public int speed;
    public BufferedImage image;
    public static boolean hit = false;

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

