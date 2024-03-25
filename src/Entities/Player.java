package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import Tools.Direction;
import WorldModel.Game;
import WorldModel.ImmediateWorld;

public class Player extends JPanel {
    private Point gamePosition;
    private ImmediateWorld world;
    private int frameIndex = 0;
    private int frameMax = 2;
    public Direction facingDirection = Direction.Down;
    public boolean isWalking;

    public Player(double x, double y, ImmediateWorld world) {
        setBounds(-64, -64, 1920, 1080);
        setOpaque(false);
        this.gamePosition = new Point((int) x, (int) y);
        this.world = world;

    }

    @Override
    protected void paintComponent(Graphics g) {
        if (frameIndex >= frameMax) {
            frameIndex = 0;
        }
        URL frame = getFrame();
        try {
            BufferedImage dude = ImageIO.read(frame);
            g.drawImage((Image) dude, Game.width / 2, Game.height / 2, this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Point getGamePosition() {
        return this.gamePosition;
    }

    public void moveX(double x) {
        double newX = getGamePosition().getX() + (x > 0 ? 1 : -1);
        Point nextPos = new Point((int) newX, (int) getGamePosition().getY());
        this.gamePosition = nextPos;
    }

    public void moveY(double y) {
        double newY = getGamePosition().getY() + (y > 0 ? 1 : -1);
        Point nextPos = new Point((int) getGamePosition().getX(), (int) newY);
        this.gamePosition = nextPos;

    }

    public void setImmediateWorld(ImmediateWorld world) {
        this.world = world;
    }

    public void updateFrame() {
        this.frameIndex++;
    }

    public URL getFrame() {
        boolean facingUp = facingDirection.equals(Direction.Up);
        boolean facingDown = facingDirection.equals(Direction.Down);
        boolean facingRight = facingDirection.equals(Direction.Right);
        boolean facingLeft = facingDirection.equals(Direction.Left);

        if (facingUp && isWalking) { // or is sprinting
            return getClass().getResource("/GameResources/Images/CharacterImages/RayUpMoving" + frameIndex + ".png");
        } else if (facingDown && isWalking) { // or is sprinting
            return getClass().getResource("/GameResources/Images/CharacterImages/RayDownMoving" + frameIndex + ".png");
        } else if (facingRight && isWalking) { // or is sprinting
            return getClass().getResource("/GameResources/Images/CharacterImages/RayRightMoving" + frameIndex + ".png");
        } else if (facingLeft && isWalking) { // or isSprinting
            return getClass().getResource("/GameResources/Images/CharacterImages/RayLeftMoving" + frameIndex + ".png");
        } else if (facingUp) {
            return getClass().getResource("/GameResources/Images/CharacterImages/RayUpStill" + frameIndex + ".png");
        } else if (facingDown) {
            return getClass()
                    .getResource("/GameResources/Images/CharacterImages/RayDownStill" + frameIndex + ".png");
        } else if (facingRight) {
            return getClass()
                    .getResource("/GameResources/Images/CharacterImages/RayRightStill" + frameIndex + ".png");
        } else if (facingLeft) {
            return getClass()
                    .getResource("/GameResources/Images/CharacterImages/RayLeftStill" + frameIndex + ".png");
        } else {
            return getClass()
                    .getResource("/GameResources/Images/CharacterImages/RayDownStill" + frameIndex + ".png");
        }
    }
}
