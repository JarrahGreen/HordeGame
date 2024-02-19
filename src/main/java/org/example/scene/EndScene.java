package org.example.scene;

import org.example.SceneManager;
import org.example.entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class EndScene extends Scene {
    final public BufferedImage won, lost;
    private final BufferedImage currentBackground;
    public EndScene(boolean didWin) {

        try {
            won = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Won.png")));
            lost = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Lost.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        if (didWin) {
            currentBackground = won;
        } else {
            currentBackground = lost;
        }

    }

    public void keyPressed(KeyEvent e) {
        if (currentBackground == won || currentBackground == lost) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                Entity.maxCoolDown = 30;
                GameScene.isHell = false;
                SceneManager.getSceneManager().setActiveScene(new StartScene());


            }
        }
    }



    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(currentBackground, 0, 0, null);
    }

}
