package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean
            wPressed = false, sPressed = false, aPressed = false, dPressed = false,
            onePlayer = false, twoPlayer = false,
            hoveringOnePlayer = true, hoveringTwoPlayer = false,
            rightPressed = false, leftPressed = false, downPressed = false, upPressed = false,
            bulletUp = false, bulletDown = false, bulletRight = false, bulletLeft = false, shootBullet = false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Look at One/ Two player option
        if (!onePlayer || !twoPlayer)
            if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_UP ) {
                if (hoveringOnePlayer) {
                    hoveringOnePlayer = false;
                    hoveringTwoPlayer = true;
                }
                else if (hoveringTwoPlayer) {
                    hoveringTwoPlayer = false;
                    hoveringOnePlayer = true;
                }
            }

        // Select One/ Two player
        if (code == KeyEvent.VK_ENTER) {
            if (hoveringOnePlayer) {
                onePlayer = true;
            }
            if (hoveringTwoPlayer) {
                twoPlayer = true;
            }

        }

        // Player One movement
        if (code == KeyEvent.VK_W) {
            wPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            sPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            aPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            dPressed = true;
        }

        // Player Two movement
        if (code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

        // Bullet shooting
        if (code == KeyEvent.VK_SPACE) {
            shootBullet = true;
            if (upPressed) {
                bulletUp = true;
            }
            if (downPressed) {
                bulletDown = true;
            }
            if (leftPressed) {
                bulletLeft = true;
            }
            if (rightPressed) {
                bulletRight = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            wPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            sPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            aPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            dPressed = false;
        }


        if (code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

        if (code == KeyEvent.VK_SPACE) {
            shootBullet = false;
        }

    }
}
