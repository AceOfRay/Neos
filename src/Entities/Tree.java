package Entities;

import java.awt.Point;

import Entities.AbstractClasses.Entity;
import WorldModel.ImmediateWorld;

public interface Tree {
    public default Entity generateDrops() {
        return new LavenderTree(new Point(0, 0), new ImmediateWorld());
    }
}
