package org.example;

import org.example.entity.Enemy;
import org.example.entity.PlayerOne;
import org.example.entity.PlayerTwo;
import org.example.entity.Projectiles.Bullet;
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
    int i = 0;
    int enemyX = 700;
    int enemyY = 500;
    int FPS = 60;
    int numEnemy = 5;
    boolean onePlayerSelected = false;
    boolean twoPlayerSelected = false;
    public BufferedImage background;
    public BufferedImage title1p;
    public BufferedImage title2p;

    ArrayList<Powerup> powerUpList = new ArrayList<>();
    ArrayList<Enemy> enemyList = new ArrayList<>();

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // Create a player object
    PlayerOne playerOne = new PlayerOne(keyH);
    PlayerTwo playerTwo = new PlayerTwo(keyH);

    Bullet bullet = new Bullet(keyH, playerOne.getX(), playerOne.getY());

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        while (i < numEnemy) {
            enemyList.add(new Enemy(enemyX, enemyY));
            enemyY -= 100;
            i++;
        }

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
        if (onePlayerSelected || twoPlayerSelected) {
            playerOne.update();
            playerTwo.update();

            // Change the Enemy's x and y location
            for (Enemy e : enemyList) {
                e.updateValues(playerOne.x, playerOne.y);
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

        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Background.png")));
            title1p = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Title_1p.png")));
            title2p = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Title_2p.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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



        if (onePlayerSelected) {
            g.drawImage(background, 0, 0, null);
            playerOne.draw(g2, tileSize);


            if (keyH.shootBullet) {
                bullet.draw(g2);
            }

            for (Powerup powerup : powerUpList) {
                powerup.draw(g2);
            }

            // Draw the enemy's
            for (Enemy e : enemyList) {
                e.draw(g2, tileSize);
            }

        }
        if (twoPlayerSelected) {
            g.drawImage(background, 0, 0, null);
            playerOne.draw(g2, tileSize);
            playerTwo.draw(g2, tileSize);


            if (keyH.shootBullet) {
                bullet.draw(g2);
            }

            for (Powerup powerup : powerUpList) {
                powerup.draw(g2);
            }

            for (Enemy e : enemyList) {
                e.draw(g2, tileSize);
            }
        }
        g2.dispose();
    }
}