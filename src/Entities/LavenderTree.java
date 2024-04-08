package Entities;

import java.awt.Point;
import Entities.AbstractClasses.Entity;
import WorldModel.World;

public class LavenderTree extends Entity implements Tree {

    public LavenderTree(Point p, World world) {
        super(p, world);
        setBounds(0, 0, 96, 96);
    }
    
    public String getFrame() {
        return "/GameResources/Images/Trees/Lavender";
    }
}
