package org.example;

import javax.swing.*;
import java.awt.*;



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
    Thread gameThread;

    // FPS
    int FPS = 60;

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
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        sceneManager.update();

    }

    public void paint(Graphics2D g2) {
        System.out.println("draw");
        super.paintComponent(g2);
        sceneManager.draw(g2);
    }
}
