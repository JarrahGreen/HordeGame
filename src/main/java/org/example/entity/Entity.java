package org.example.entity;

import java.awt.image.BufferedImage;

public abstract class Entity {
    public int x, y;
    public int speed;
    public BufferedImage image;
    boolean hit = false;

    public Entity() {
    }
}

