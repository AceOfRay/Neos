package Entities.AbstractClasses;

import java.awt.Point;

import WorldModel.World;

public abstract class Obstacle extends Entity {

    public Obstacle(Point pos, World world) {
        super(pos, world);
    }

}
