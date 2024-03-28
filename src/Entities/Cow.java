package Entities;

import java.awt.Point;

import Entities.AbstractClasses.Entity;
import Entities.Interfaces.Animal;
import Tools.Direction;
import WorldModel.ImmediateWorld;

public class Cow extends Entity implements Animal {
    
    public Cow(Point pos, ImmediateWorld world) {
        super(pos, world);
    }

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
       boolean facingUp = facingDirection.equals(Direction.Up);
        boolean facingDown = facingDirection.equals(Direction.Down);
        boolean facingRight = facingDirection.equals(Direction.Right);
        boolean facingLeft = facingDirection.equals(Direction.Left);

        if (facingUp && isWalking) { // or is sprinting
            return "/GameResources/Images/Cow/RayUpMoving";
        } else if (facingDown && isWalking) { // or is sprinting
            return "/GameResources/Images/Cow/RayDownMoving";
        } else if (facingRight && isWalking) { // or is sprinting
            return "/GameResources/Images/Cow/RayRightMoving";
        } else if (facingLeft && isWalking) { // or isSprinting
            return "/GameResources/Images/Cow/RayLeftMoving";
        } else if (facingUp) {
            return "/GameResources/Images/Cow/CowUpStill";
        } else if (facingDown) {
            return "/GameResources/Images/Cow/CowDownStill";
        } else if (facingRight) {
            return "/GameResources/Images/Cow/CowRightStill";
        } else if (facingLeft) {
            return "/GameResources/Images/Cow/CowLeftStill";
        } else {
            return "/GameResources/Images/Cow/CowDownStill";
        }
    }
    
}
