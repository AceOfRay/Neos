package Entities;

import java.awt.*;
import Entities.AbstractClasses.Living;
import Tools.Direction;
import WorldModel.ImmediateWorld;

public class Player extends Living {

    public Player(Point p, int health, int hunger, ImmediateWorld world) {
        super(p, health, hunger, world);
        setBounds(0, 0, 1920, 1080);
        setOpaque(false);
        this.worldPos = p;

    }

    public void moveX(double x) {
        double newX = getWorldPosition().getX() + (x > 0 ? 1 : -1);
        Point nextPos = new Point((int) newX, (int) getWorldPosition().getY());
        if (!world.pointOccupied(nextPos)) {
            this.worldPos = nextPos;
        }
        
    }

    public void moveY(double y) {
        double newY = getWorldPosition().getY() + (y > 0 ? -1 : 1);
        Point nextPos = new Point((int) getWorldPosition().getX(), (int) newY);
        if (!world.pointOccupied(nextPos)) {
            this.worldPos = nextPos;
        }
    }

    public String getFrame() {
        boolean facingUp = facingDirection.equals(Direction.Up);
        boolean facingDown = facingDirection.equals(Direction.Down);
        boolean facingRight = facingDirection.equals(Direction.Right);
        boolean facingLeft = facingDirection.equals(Direction.Left);

        if (facingUp && isWalking) { // or is sprinting
            return "/GameResources/Images/CharacterImages/RayUpMoving";
        } else if (facingDown && isWalking) { // or is sprinting
            return "/GameResources/Images/CharacterImages/RayDownMoving";
        } else if (facingRight && isWalking) { // or is sprinting
            return "/GameResources/Images/CharacterImages/RayRightMoving";
        } else if (facingLeft && isWalking) { // or isSprinting
            return "/GameResources/Images/CharacterImages/RayLeftMoving";
        } else if (facingUp) {
            return "/GameResources/Images/CharacterImages/RayUpStill";
        } else if (facingDown) {
            return "/GameResources/Images/CharacterImages/RayDownStill";
        } else if (facingRight) {
            return "/GameResources/Images/CharacterImages/RayRightStill";
        } else if (facingLeft) {
            return "/GameResources/Images/CharacterImages/RayLeftStill";
        } else {
            return "/GameResources/Images/CharacterImages/RayDownStill";
        }
    }
}
