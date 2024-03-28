package Entities;

import java.awt.Point;

import Entities.AbstractClasses.Entity;
import Entities.Interfaces.Animal;
import WorldModel.ImmediateWorld;

public class Zombie extends Entity implements Animal {

    public Zombie(Point pos, ImmediateWorld world) {
        super(pos, world);
        //TODO Auto-generated constructor stub
    }

    public String zombieFilePath = "/GameResources/Images/Trees/Zombie";
    @Override
    public void findTarget() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findTarget'");
    }

    @Override
    public void moveTowardsTarget() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveTowardsTarget'");
    }

    @Override
    public boolean atTarget() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atTarget'");
    }

    @Override
    public void executeAction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeAction'");
    }

    @Override
    public void generateNextAction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateNextAction'");
    }

    @Override
    public String getFrame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFrame'");
    }
    
}
