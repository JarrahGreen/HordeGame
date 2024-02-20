package org.example.scene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ShopScene extends Scene{
    BufferedImage shopStart;
    private final BufferedImage currentBackground;

    public ShopScene() {
        try {
            shopStart = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Title_shop.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        currentBackground = shopStart;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(currentBackground, 0, 0, null);
    }


}
