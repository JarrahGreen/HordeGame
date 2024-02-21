package org.example.entity;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;


public class Enemy extends Entity{
    int enemySpeed = 2;


    public Enemy(int x, int y) {
        super(x, y, GamePanel.tileSize, GamePanel.tileSize);
        this.speed = enemySpeed;
        getEnemyImage();
    }

    public void updateValues(ArrayList<Player> playerList) {
        double minDistanceSoFar = Double.POSITIVE_INFINITY;
        Optional<Double> diffX = Optional.empty();
        Optional<Double> diffY = Optional.empty();
        for (Player p : playerList) {
            double dx = p.x - x;
            double dy = p.y - y;
            double dist = Math.abs(dx) + Math.abs(dy);
            if (dist < minDistanceSoFar) {
                minDistanceSoFar = dist;
                diffX = Optional.of(dx);
                diffY = Optional.of(dy);
            }
        }

        if (minDistanceSoFar < speed) { return; }

        double angle = Math.atan2(diffY.orElseThrow(), diffX.orElseThrow());

        double diffX2 = Math.cos(angle) * speed;
        double diffY2 = Math.sin(angle) * speed;
        x += (int) diffX2;
        y += (int) diffY2;
    }

    public void getEnemyImage() {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Enemy.png")));

        }catch(IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void draw(Graphics g) {
        g.drawImage(image, (int) x, (int) y, width, height, null);
    }
}





