package Entities.Interfaces;

import java.awt.Point;

public interface Moving {
    public abstract void findTarget();

    public abstract void moveTowardsTarget();

    public abstract boolean atTarget();

    public abstract void findDirection();
}
