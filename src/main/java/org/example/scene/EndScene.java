package org.example.scene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class EndScene extends Scene {
    final BufferedImage won, lost;
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


    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(currentBackground, 0, 0, null);
    }

}
