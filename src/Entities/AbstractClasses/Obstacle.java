package Entities.AbstractClasses;

import java.awt.Point;

import WorldModel.ImmediateWorld;

public abstract class Obstacle extends Entity {

    public Obstacle(Point pos, ImmediateWorld world) {
        super(pos, world);
    }

}
