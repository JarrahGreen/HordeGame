package org.example.entity.Projectiles;
import org.example.entity.Direction;

public class Bullet extends Projectiles{
    private final Direction dir;
    public Bullet(double x, double y, Direction dir) {
        super(x, y);
        this.dir = dir;
    }

    public void update() {
        x += dir.getDeltaX() * 5;
        y += dir.getDeltaY() * 5;
    }
}
