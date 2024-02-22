package org.example.scene;

import org.example.SceneManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SettingsScene extends Scene{
    BufferedImage settings_isHell,settings_Not_isHell,settings_Resolution_IsHell,settings_Resolution_notIsHell;
    private BufferedImage currentBackground;
    BufferedImage[] backgrounds;

    int i = 0;

    public SettingsScene() {
        try {
            settings_Not_isHell = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Title_settings_Not_isHell.png")));
            settings_isHell = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Title_settings_isHell.png")));
            settings_Resolution_notIsHell = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Title_settings_Resolution_notIsHell.png")));
            settings_Resolution_IsHell = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Title_settings_Resolution_IsHell.png")));
            backgrounds = new BufferedImage[]{settings_Not_isHell,settings_isHell,settings_Resolution_notIsHell, settings_Resolution_IsHell};

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        currentBackground = backgrounds[i];
    }




    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN: {
                if (i < 2) {
                    i +=2;
                }
                currentBackground = backgrounds[i];
                break;
            }

            case KeyEvent.VK_UP: {
                if (i > 1) {
                    i-=2;

                }
                currentBackground = backgrounds[i];
                break;
            }




            case KeyEvent.VK_ENTER: {
                if (currentBackground == backgrounds[0]) {
                    i+=1;
                    System.out.println(i);
                    currentBackground = backgrounds[i];

                    GameScene.isHell = true;
                    System.out.println("Hell mode activated");
                    break;

                }
                else if (currentBackground == backgrounds[1]) {
                    i-=1;
                    System.out.println(i);
                    currentBackground = backgrounds[0];

                    GameScene.isHell = false;
                    System.out.println("Hell mode de-activated");
                    break;
                }
                else {
                    break;
                }
            }

            case KeyEvent.VK_ESCAPE: {
                SceneManager.getSceneManager().setActiveScene(new StartScene());
            }
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(currentBackground, 0, 0, null);
    }
}
