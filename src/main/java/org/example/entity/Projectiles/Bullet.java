package org.example.entity.Projectiles;
import org.example.entity.Direction;
import org.example.entity.Entity;

public class Bullet extends Projectiles{
    private final Direction dir;
    public Bullet(double x, double y, Direction dir, Entity source) {
        super(x, y, source);
        this.dir = dir;
    }

    public void update() {
        x += dir.getDeltaX() * 10;
        y += dir.getDeltaY() * 10;
    }
}
