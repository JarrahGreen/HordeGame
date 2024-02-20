package org.example.scene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SettingsScene extends Scene{
    BufferedImage settingsStart, notIsHell, isHell;
    private BufferedImage currentBackground;

    public SettingsScene() {
        try {
            settingsStart = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Title_settings.png")));
            isHell = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Settings_isHell.png")));
            notIsHell = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Settings_notIsHell.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        currentBackground = settingsStart;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN: {

            }
            case KeyEvent.VK_UP: {

            }
            case KeyEvent.VK_ENTER: {
                if (currentBackground == notIsHell) {
                    GameScene.isHell = true;
                    currentBackground = isHell;
                    System.out.println("Hell mode activated");
                }
                if (currentBackground == isHell) {
                    GameScene.isHell = false;
                    currentBackground = notIsHell;
                    System.out.println("Hell mode de-activated");
                }
            }
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(currentBackground, 0, 0, null);
    }




}

