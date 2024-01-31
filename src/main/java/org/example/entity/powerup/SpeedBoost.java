package org.example.entity.powerup;

import org.example.entity.PlayerOne;
import org.example.entity.PlayerTwo;

import java.awt.*;

public class SpeedBoost extends Powerup {
    public SpeedBoost(int x, int y) {
        super(x, y);
        color = Color.BLUE;
    }

    @Override
    public void collect(PlayerOne playerOne) {
        playerOne.setSpeed(playerOne.speed + 2);
    }

    @Override
    public void collect(PlayerTwo playerTwo) {
        playerTwo.setSpeed(playerTwo.speed + 2);
    }




}
