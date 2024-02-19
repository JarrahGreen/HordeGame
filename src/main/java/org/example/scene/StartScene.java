package org.example.scene;
import org.example.SceneManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class StartScene extends Scene {
    private final BufferedImage title2p;
    private final BufferedImage settings;
    BufferedImage[] backgrounds;

    int i = 0;
    private BufferedImage currentBackground;

    public StartScene() {
        try {
            BufferedImage title1p = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Title_1p.png")));
            title2p = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Title_2p.png")));
            settings = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Title_settings.png")));
            backgrounds = new BufferedImage[]{title1p, title2p, settings};
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        currentBackground = backgrounds[0];


    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(currentBackground, 0, 0, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN: {
                if (i < 2) {
                    i += 1;
                }
                currentBackground = backgrounds[i];
                break;
            }
            case KeyEvent.VK_UP: {
                if (i > 0) {
                    i-=1; 
                }
                currentBackground = backgrounds[i];
                break;
            }
            case KeyEvent.VK_ENTER: {
                if (currentBackground != settings) {
                    int numPlayers = 1;
                    if (currentBackground == title2p) {
                        numPlayers = 2;
                    }
                    SceneManager.getSceneManager().setActiveScene(new GameScene(numPlayers));

                }
                if (currentBackground == settings) {
                    if (!GameScene.isHell) {
                        GameScene.isHell = true;
                        System.out.println("Hell mode activated");
                    }
                    else {
                        GameScene.isHell = false;
                        System.out.println("Hell mode de-activated");
                    }
                }
            }
        }
    }
}
