package Entities;

import java.awt.Point;
import Entities.AbstractClasses.Entity;
import WorldModel.ImmediateWorld;

public class LavenderTree extends Entity implements Tree {

    public LavenderTree(Point p, ImmediateWorld world) {
        super(p, world);
        setBounds(0, 0, 96, 96);
        setOpaque(false);
        this.setLayout(null);
    }

    public String getFrame() {
        return "/GameResources/Images/Trees/LavenderTree";
    }



}
