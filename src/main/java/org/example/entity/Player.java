package org.example.entity;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;


public class Player extends Entity{
    public final PlayerController controls;
    private boolean didShoot;
    private double cooldown;
    public int maxCooldown = 0;

    public Player(int x, int y, PlayerController controls, String pathToImage) {
        super(x, y, GamePanel.tileSize, GamePanel.tileSize);
        this.speed = 3;
        this.controls = controls;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(pathToImage)));
        }catch(IOException e) {
            throw new RuntimeException(e);
        }
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
        if (cooldown > 0) {
            cooldown -= dt;
        }
    }

    public void move(Set<Integer> heldKeys, double dt) {
        if (heldKeys.contains(controls.up())) {
            y -= speed * dt;
        }
        if (heldKeys.contains(controls.right())) {
            x += speed * dt;
        }
        if (heldKeys.contains(controls.down())) {
            y += speed * dt;
        }
        if (heldKeys.contains(controls.left())) {
            x -= speed * dt;
        }
        if (heldKeys.contains(controls.shoot())) {
            if (cooldown <= 0) {
                didShoot = true;
                cooldown = maxCooldown;
            }
        }
    }
}
