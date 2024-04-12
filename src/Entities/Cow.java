package Entities;

import java.awt.Point;
import java.util.Random;

import Entities.AbstractClasses.Entity;
import Entities.Enums.Action;
import Entities.Interfaces.Animal;
import Tools.Direction;
import WorldModel.World;

public class Cow extends Entity implements Animal {
    private Action action = Action.MOVE;
    private Point target;
    private static final int moveSpeed = 2;

    public Cow(Point pos, World world) {
        super(pos, world);
        generateNextAction();
    }

    @Override
    public void findTarget() {
        Random rand = new Random();
        this.target = new Point(this.worldPos.x + rand.nextInt(3) - 1, this.worldPos.y + rand.nextInt(3) - 2);
        this.isWalking = true;
        findDirection();
    }

    @Override
    public void findDirection() {
        if (this.worldPos.x - this.target.x > 0) {
            this.directionSet.add(Direction.Left);
        } else {
            this.directionSet.add(Direction.Right);
        }
        if (this.target.y - this.worldPos.y > 0) {
            this.directionSet.add(Direction.Down);
        } else {
            this.directionSet.add(Direction.Up);
        }
    }

    @Override
    public void moveTowardsTarget() {
        if (isWalking) {

            boolean up = directionSet.contains(Direction.Up);
            boolean down = directionSet.contains(Direction.Down);
            boolean right = directionSet.contains(Direction.Right);
            boolean left = directionSet.contains(Direction.Left);
            int dx = 0;
            int dy = 0;

            if (up) {
                dy = -moveSpeed;
            }
            if (down) {
                dy = moveSpeed;
            }
            if (right) {
                dx = moveSpeed;
            }
            if (left) {
                dx = -moveSpeed;
            }
            Point oldLoc = getLocation();
            setLocation(oldLoc.x + dx, oldLoc.y + dy);
            this.isWalking = true;
            this.repaint();
        }
        // update the worldPos using the current chunk index + new location
    }

    @Override
    public boolean atTarget() {
        if (this.worldPos.equals(this.target)) {
            directionSet.clear();
            this.isWalking = false;
            return true;
        }
        return false;
    }

    @Override
    public void executeAction() {
        if (!atTarget()) {
            moveTowardsTarget();
        }
    }

    @Override
    public void generateNextAction() {
        switch (this.action) {
            case MOVE: {
                this.action = Action.EAT;
                break;
            }
            case EAT: {
                this.action = Action.MOVE;
                break;
            }
            default: {
                this.action = Action.MOVE;
                break;
            }
        }
        findTarget();
    }

    @Override
    public String getFrame() {
        boolean facingUp = facingDirection.equals(Direction.Up);
        boolean facingDown = facingDirection.equals(Direction.Down);
        boolean facingRight = facingDirection.equals(Direction.Right);
        boolean facingLeft = facingDirection.equals(Direction.Left);

        if (facingUp && isWalking) { // or is sprinting
            return "/GameResources/Images/Cow/CowUpStill";
        } else if (facingDown && isWalking) { // or is sprinting
            return "/GameResources/Images/Cow/CowDownStill";
        } else if (facingRight && isWalking) { // or is sprinting
            return "/GameResources/Images/Cow/CowRightStill";
        } else if (facingLeft && isWalking) { // or isSprinting
            return "/GameResources/Images/Cow/CowLeftStill";
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
