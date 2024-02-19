package org.example.entity;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;


public class Player extends Entity{
    public PlayerController controls;
    private boolean didShoot;
    private double coolDown;
    // public int maxCoolDown = 30;
    public Direction lastDirection;

    public Player(int x, int y, PlayerController controls, String pathToImage) {
        super(x, y, GamePanel.tileSize, GamePanel.tileSize);
        this.speed = 3;
        this.controls = controls;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(pathToImage)));
        }catch(IOException e) {
            throw new RuntimeException(e);
        }
        lastDirection = Direction.UP;
    }

    public boolean didShoot() {
        boolean didShoot = this.didShoot;
        this.didShoot = false;
        return didShoot;
    }

    public void draw(Graphics g){
        g.drawImage(image, (int) x, (int) y, width, height, null);
    }

    @Override
    public void update(double dt) {
        super.update(dt);
        if (coolDown > 0) {
            coolDown -= dt;
        }
    }

    public void move(Set<Integer> heldKeys, double dt) {
        boolean isUp = heldKeys.contains(controls.up());
        boolean isRight = heldKeys.contains(controls.right());
        boolean isDown = heldKeys.contains(controls.down());
        boolean isLeft = heldKeys.contains(controls.left());
        if (isUp) {
            y -= speed * dt;
        }
        if (isRight) {
            x += speed * dt;
        }
        if (isDown) {
            y += speed * dt;
        }
        if (isLeft) {
            x -= speed * dt;
        }
        if (isUp || isRight || isDown || isLeft) {
            lastDirection = Direction.fromBooleans(isUp, isRight, isDown, isLeft);
        }
        if (heldKeys.contains(controls.shoot())) {
            if (coolDown <= 0) {
                didShoot = true;
                coolDown = maxCoolDown;
            }
        }
    }
}
