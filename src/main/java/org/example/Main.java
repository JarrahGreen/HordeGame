package org.example;
import javax.swing.JFrame;

public class  Main {
    public static void main(String[] args) {
        // todo Player two needs to shoot
        // todo Add death screen
        // todo Add enemies increasing in number
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Horde Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}