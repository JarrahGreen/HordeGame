package org.example;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final static int originalTileSize = 16; // 16 * 16
    final static int scale = 3;
    public static final int tileSize = originalTileSize * scale;  // 48 * 48 tile
    final static int maxScreenCol = 40;
    final static int maxScreenRow = 22;
    public final static int screenWidth = tileSize * maxScreenCol; // 768 px
    public final static int screenHeight = tileSize * maxScreenRow; // 576 px

    // Key handler and Game thread
    Thread gameThread;

    // FPS
    final static int FPS = 60;

    private final SceneManager sceneManager;

    public GamePanel() {
        this.sceneManager = SceneManager.getSceneManager();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(sceneManager);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000D / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update(delta);
                repaint();
                delta--;
            }
        }
    }

    public void update(double dt) {
        sceneManager.update(dt);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        sceneManager.draw(g);
    }
}
