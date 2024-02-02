package org.example;

import org.example.entity.Direction;
import org.example.entity.Enemy;
import org.example.entity.PlayerOne;
import org.example.entity.PlayerTwo;
import org.example.entity.Projectiles.Bullet;
import org.example.entity.Projectiles.Projectiles;
import org.example.entity.powerup.Powerup;
import org.example.entity.powerup.SpeedBoost;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16 * 16
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;  // 48 * 48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 px
    final int screenHeight = tileSize * maxScreenRow; // 576 px

    // Key handler and Game thread
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // Enemy Array
    ArrayList<Enemy> enemyList = new ArrayList<>();

    ArrayList<Projectiles> projectileList = new ArrayList<>();
    ArrayList<Projectiles> projectilesToRemove = new ArrayList<>();


    // FPS
    int FPS = 60;

    // Player
    boolean onePlayerSelected = false;
    boolean twoPlayerSelected = false;

    PlayerOne playerOne = new PlayerOne(keyH);
    PlayerTwo playerTwo = new PlayerTwo(keyH);

    // Images
    public BufferedImage background;
    public BufferedImage title1p;
    public BufferedImage title2p;

    // Power Up
    ArrayList<Powerup> powerUpList = new ArrayList<>();

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Background.png")));
            title1p = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Title_1p.png")));
            title2p = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Title_2p.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }









    @Override
    public void run() {

            Enemy e1 = new Enemy(300, 300);
            Enemy e2 = new Enemy(700, 600);

            enemyList.add(e1);
            enemyList.add(e2);



        int speedBoostX = (int) (Math.random() * 500);
        int speedBoostY = (int) (Math.random() * 500);
        powerUpList.add(new SpeedBoost(speedBoostX, speedBoostY));

        double drawInterval = 1000000000D / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
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

        for (Projectiles p: projectileList) {
            if (p.x > 768 || p.x < 0 || p.y > 576 || p.y < 0) {
                projectilesToRemove.add(p);
            }
            p.update();
        }
        projectileList.removeAll(projectilesToRemove);
        projectilesToRemove.clear();

        if (onePlayerSelected || twoPlayerSelected) {
            playerOne.update();
            if (twoPlayerSelected) {
                playerTwo.update();
            }



            // Change the Enemy's x and y location
            for (Enemy e : enemyList) {
                e.updateValues(playerOne.x, playerOne.y);

                //enemyXY.add(playerOne.x, playerOne.y);
            }

            ArrayList<Powerup> toRemove = new ArrayList<>();
            for (Powerup p : powerUpList) {
                // Is the player on top of the PowerUp
                if (playerOne.x < p.x + p.radius && playerOne.x + tileSize > p.x - p.radius
                        && playerOne.y < p.y + p.radius && playerOne.y + tileSize > p.y - p.radius) {
                    p.collect(playerOne);
                    toRemove.add(p);

                }
                if (playerTwo.x < p.x + p.radius && playerTwo.x + tileSize > p.x - p.radius
                        && playerTwo.y < p.y + p.radius && playerTwo.y + tileSize > p.y - p.radius) {
                    p.collect(playerTwo);
                    toRemove.add(p);

                }
            }
            // Remove powerUps from the screen that the player collects
            powerUpList.removeAll(toRemove);
        }
    }





    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (keyH.hoveringOnePlayer) {
            g.drawImage(title1p, 0, 0, null);
        }
        if (keyH.hoveringTwoPlayer) {
            g.drawImage(title2p, 0, 0, null);
        }


        if (!onePlayerSelected)
            if (keyH.onePlayer) {
                onePlayerSelected = true;
            }
        if (!twoPlayerSelected)
            if (keyH.twoPlayer) {
                twoPlayerSelected = true;
            }



        if (onePlayerSelected || twoPlayerSelected) {
            g.drawImage(background, 0, 0, null);
            playerOne.draw(g2, tileSize);
            if (twoPlayerSelected) {
                playerTwo.draw(g2, tileSize);
            }

            for (Powerup powerup : powerUpList) {
                powerup.draw(g2);
            }

            // Draw the enemy's
            for (Enemy e : enemyList) {
                e.draw(g2, tileSize);
            }

            for (Projectiles p : projectileList) {
                p.draw(g2);
            }


        }
        g2.dispose();
    }
}
