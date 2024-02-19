package org.example.scene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import org.example.GamePanel;
import org.example.SceneManager;
import org.example.entity.*;
import org.example.entity.Projectiles.Bullet;
import org.example.entity.powerup.FireSpeedUp;
import org.example.entity.powerup.MachineGun;
import org.example.entity.powerup.Powerup;
import org.example.entity.powerup.SpeedBoost;
import org.example.entity.Projectiles.Projectiles;


public class GameScene extends Scene {
    private final BufferedImage background;
    private final Set<Integer> heldKeys;
    private final Random rng;


    public static boolean pickedUpMachineGun = false;
    public static boolean holdingMachineGun = false;
    public static long startPickedUpMachineGun;
    public static long endPickedUpMachineGun;

    int machineGunSpawnRate = 2000;
    int fireSpeedUpSpawnRate = 1000;
    int speedBoostSpawnRate = 1000;

    ArrayList<Enemy> enemyList = new ArrayList<>();
    ArrayList<Enemy> enemiesToRemove = new ArrayList<>();
    ArrayList<Projectiles> projectileList = new ArrayList<>();
    ArrayList<Projectiles> projectilesToRemove = new ArrayList<>();
    ArrayList<Powerup> powerUpList = new ArrayList<>();
    ArrayList<Powerup> powerUpsToRemove = new ArrayList<>();
    ArrayList<Player> players = new ArrayList<>();

    public static boolean isHell = false;

    public int enemySpawnRate;
    public int score;

    public GameScene(int numPlayers) {
        heldKeys = new HashSet<>();
        final PlayerController[] pControllers = {
                new PlayerController(
                    KeyEvent.VK_W,
                    KeyEvent.VK_D,
                    KeyEvent.VK_S,
                    KeyEvent.VK_A,
                    KeyEvent.VK_SPACE
                ),
                new PlayerController(
                    KeyEvent.VK_UP,
                    KeyEvent.VK_RIGHT,
                    KeyEvent.VK_DOWN,
                    KeyEvent.VK_LEFT,
                    KeyEvent.VK_ENTER
                )
        };
        final String[] pImages = {
                "/PlayerOne.png",
                "/PlayerTwo.png",
        };
        enemySpawnRate = 200;
        int px = 100;
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(px, 100, pControllers[i], pImages[i]));
            px += 100;
        }

        rng = new Random();

        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Background.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(double dt) {
        for (Player p: players) {
            p.move(heldKeys, dt);
            p.update(dt);
            checkCollect(p);
            if (p.didShoot()) {
                projectileList.add(new Bullet(
                        p.x + (double) p.width / 2, p.y + (double) p.height / 2,
                        p.lastDirection, p
                ));
            }
        }


        if (rng.nextInt(speedBoostSpawnRate) == 0) {
            powerUpList.add(new SpeedBoost(
                    rng.nextInt(GamePanel.screenWidth - Powerup.diameter),
                    rng.nextInt(GamePanel.screenHeight - Powerup.diameter)
            ));
        }

        if (rng.nextInt(fireSpeedUpSpawnRate) == 0) {
            powerUpList.add(new FireSpeedUp(
                    rng.nextInt(GamePanel.screenWidth - Powerup.diameter),
                    rng.nextInt(GamePanel.screenHeight - Powerup.diameter)
            ));
        }

        if (rng.nextInt(machineGunSpawnRate) == 0) {
            powerUpList.add(new MachineGun(
                    rng.nextInt(GamePanel.screenWidth - Powerup.diameter),
                    rng.nextInt(GamePanel.screenHeight - Powerup.diameter)
            ));
        }


        if (enemySpawnRate <= 1) {
            SceneManager.getSceneManager().setActiveScene(new EndScene(true));
        }
        if (rng.nextInt(enemySpawnRate) == 0) {
            if (enemySpawnRate > 20) { enemySpawnRate--; }
            int primaryAxis;
            int secondaryAxis;
            boolean flip = rng.nextBoolean();
            if (flip) {
                primaryAxis = GamePanel.screenHeight;
                secondaryAxis = GamePanel.screenWidth;
            } else {
                primaryAxis = GamePanel.screenWidth;
                secondaryAxis = GamePanel.screenHeight;
            }
            primaryAxis = rng.nextInt(primaryAxis + GamePanel.tileSize) - GamePanel.tileSize;
            secondaryAxis = rng.nextBoolean() ? secondaryAxis : -GamePanel.tileSize;
            Enemy e;
            if (flip) {
                e = new Enemy(secondaryAxis, primaryAxis);
            } else {
                e = new Enemy(primaryAxis, secondaryAxis);
            }
            enemyList.add(e);
        }

        for (Projectiles p: projectileList) {
            if (p.x > GamePanel.screenWidth || p.x < -20 || p.y > GamePanel.screenHeight || p.y < -20) {
                projectilesToRemove.add(p);
            }
            for (Enemy e: enemyList) {
                if (e.collidesWith(p)) {
                    score++;
                    enemiesToRemove.add(e);
                    projectilesToRemove.add(p);
                    if (isHell) {
                        Player pl = ((Player) p.source);
                        pl.controls = pl.controls.randomise();
                    }
                }
            }
            enemyList.removeAll(enemiesToRemove);
            p.update();
        }
        projectileList.removeAll(projectilesToRemove);
        projectilesToRemove.clear();

        for (Enemy e : enemyList) {
            for (Player p: players) {
                if (e.collidesWith(p)) {
                    SceneManager.getSceneManager().setActiveScene(new EndScene(false));
                }
            }

            e.updateValues(players);
            checkCollect(e);
        }
    }

    void checkCollect(Entity e) {
        for (Powerup p : powerUpList) {
            if (p.collidesWith(e)) {
                p.collect(e);
                if (p instanceof MachineGun) {
                    pickedUpMachineGun = true;
                }
                powerUpsToRemove.add(p);
            }
        }
        powerUpList.removeAll(powerUpsToRemove);
        powerUpsToRemove.clear();


        if (pickedUpMachineGun) {
            startPickedUpMachineGun = System.currentTimeMillis();
            pickedUpMachineGun = false;
            holdingMachineGun = true;
        }

        // Check if the Machine gun has been help for 5 Seconds
        if (holdingMachineGun) {
            endPickedUpMachineGun = System.currentTimeMillis();

            if (endPickedUpMachineGun - startPickedUpMachineGun > 5000) {
                Entity.maxCoolDown = 30;
            }
        }
    }



    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);


        for (Player p: players) {
            p.draw(g);
        }

        for (Powerup powerup : powerUpList) {
            powerup.draw(g);
        }

        for (Enemy e : enemyList) {
            e.draw(g);
        }

        for (Projectiles p : projectileList) {
            p.draw(g);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
        g.drawString(String.valueOf(score), 5,75);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        heldKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        heldKeys.remove(e.getKeyCode());
    }
}
