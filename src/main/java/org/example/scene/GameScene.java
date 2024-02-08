package org.example.scene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import org.example.GamePanel;
import org.example.KeyHandler;
import org.example.entity.Direction;
import org.example.entity.Enemy;
import org.example.entity.PlayerOne;
import org.example.entity.PlayerTwo;
import org.example.entity.powerup.Powerup;
import org.example.entity.powerup.SpeedBoost;
import org.example.entity.Projectiles.Projectiles;
import org.example.entity.Projectiles.Bullet;


public class GameScene extends Scene {
    private final BufferedImage background;

    ArrayList<Enemy> enemyList = new ArrayList<>();
    ArrayList<Projectiles> projectileList = new ArrayList<>();
    ArrayList<Projectiles> projectilesToRemove = new ArrayList<>();
    ArrayList<Powerup> powerUpList = new ArrayList<>();

    KeyHandler keyH;
    PlayerOne playerOne;
    PlayerTwo playerTwo;
    GamePanel gamePanel;

    public GameScene(KeyHandler keyH, PlayerOne playerOne, PlayerTwo playerTwo, GamePanel gamePanel) {
        this.keyH = keyH;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.gamePanel = gamePanel;

        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Background.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {

        Enemy e1 = new Enemy(300, 300);
        Enemy e2 = new Enemy(700, 600);

        enemyList.add(e1);
        enemyList.add(e2);

        int speedBoostX = (int) (Math.random() * 500);
        int speedBoostY = (int) (Math.random() * 500);
        powerUpList.add(new SpeedBoost(speedBoostX, speedBoostY));

        /*
        boolean didHit = false;
        for(Enemy e: enemyList) {
            didHit = playerOne.hit(e);
            if(didHit) {
                SceneManager.getSceneManager().setActiveScene();
            }
        }
        */

        for (Projectiles p: projectileList) {
            if (p.x > 768 || p.x < -20 || p.y > 576 || p.y < -20) {
                projectilesToRemove.add(p);
            }
            p.update();
        }
        projectileList.removeAll(projectilesToRemove);
        projectilesToRemove.clear();

        playerOne.update();
        if (keyH.twoPlayer) {
            System.out.println("run");
            playerTwo.update();
        }

        // Change the Enemy's x and y location
        for (Enemy e : enemyList) {
            e.updateValues(playerOne.x, playerOne.y);
        }

        ArrayList<Powerup> toRemove = new ArrayList<>();

        for (Powerup p : powerUpList) {
            // Is the player on top of the PowerUp
            if (playerOne.x < p.x + p.radius && playerOne.x + gamePanel.tileSize > p.x - p.radius
                    && playerOne.y < p.y + p.radius && playerOne.y + gamePanel.tileSize > p.y - p.radius) {
                p.collect(playerOne);
                toRemove.add(p);

            }
            if (playerTwo.x < p.x + p.radius && playerTwo.x + gamePanel.tileSize > p.x - p.radius
                    && playerTwo.y < p.y + p.radius && playerTwo.y + gamePanel.tileSize > p.y - p.radius) {
                p.collect(playerTwo);
                toRemove.add(p);
            }
        // Remove powerUps from the screen that the player collects
        powerUpList.removeAll(toRemove);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(background, 0, 0, null);
        g2.drawImage(background, 0, 0, null);

        playerOne.draw(g2, gamePanel.tileSize);

        if (keyH.twoPlayer) {
            playerTwo.draw(g2, gamePanel.tileSize);
        }

        for (Powerup powerup : powerUpList) {
            powerup.draw(g2);
        }

        // Draw the enemy's
        for (Enemy e : enemyList) {
            e.draw(g2, gamePanel.tileSize);
        }

        for (Projectiles p : projectileList) {
            p.draw(g2);
        }

        g2.dispose();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (keyH.spawnBullet) {
            Bullet bullet = new Bullet(playerOne.x, playerOne.y, Direction.fromBooleans(
                    keyH.wPressed,
                    keyH.dPressed,
                    keyH.sPressed,
                    keyH.aPressed
            ));
            projectileList.add(bullet);
            keyH.spawnBullet = false;
        }

    }
}
