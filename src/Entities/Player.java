package Entities;

import java.awt.*;
import Entities.AbstractClasses.Living;
import Tools.Direction;
import WorldModel.World;

public class Player extends Living {

    public Player(Point p, int health, int hunger, World world) {
        super(p, health, hunger, world);
        setBounds(0, 0, 1920, 1080);
        setOpaque(false);
        this.worldPos = p;

    }


    public void updatePosition(Point np) {
        this.worldPos = np;
        // System.out.println(np);
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
