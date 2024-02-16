package org.example.scene;

import org.example.SceneManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class StartScene extends Scene {
    private final BufferedImage title1p;
    private final BufferedImage title2p;

    private BufferedImage currentBackground;

    public StartScene() {
        try {
            title1p = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Title_1p.png")));
            title2p = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Title_2p.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        currentBackground = title1p;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(currentBackground, 0, 0, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN: {
                currentBackground = title2p;
                break;
            }
            case KeyEvent.VK_UP: {
                currentBackground = title1p;
                break;
            }
            case KeyEvent.VK_ENTER: {
                int numPlayers = 1;
                if (currentBackground == title2p) {
                    numPlayers = 2;
                }
                SceneManager.getSceneManager().setActiveScene(new GameScene(numPlayers));
            }
        }
    }
}
