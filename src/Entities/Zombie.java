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

public class Zombie extends JPanel {
    private Point gamePosition;
    private ImmediateWorld world;
    private int frameIndex = 0;
    private int frameMax = 2;
    public Direction facingDirection = Direction.Down;

    public Zombie(double x, double y, ImmediateWorld world) {
        setBounds(0, 0, 1920, 1080);
        setOpaque(false);
        this.gamePosition = new Point((int) x, (int) y);
        this.world = world;

    }

    @Override
    protected void paintComponent(Graphics g) {
        if (frameIndex >= frameMax) {
            frameIndex = 0;
        }
        URL frameLocation;
        if (facingDirection.equals(Direction.Up)) {
            frameLocation = getClass().getResource("/GameResources/Images/ZombieImages/UpStill" + frameIndex + ".png");
        } else if (facingDirection.equals(Direction.Down)) {
            frameLocation = getClass()
                    .getResource("/GameResources/Images/ZombieImages/DownStill" + frameIndex + ".png");
        } else if (facingDirection.equals(Direction.Right)) {
            frameLocation = getClass()
                    .getResource("/GameResources/Images/ZombieImages/RightStill" + frameIndex + ".png");
        } else if (facingDirection.equals(Direction.Left)) {
            frameLocation = getClass()
                    .getResource("/GameResources/Images/ZombieImages/LeftStill" + frameIndex + ".png");
        } else {
            frameLocation = getClass()
                    .getResource("/GameResources/Images/ZombieImages/DownStill" + frameIndex + ".png");
        }

        try {
            BufferedImage dude = ImageIO.read(frameLocation);
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
        this.gamePosition = new Point((int) newX, (int) getGamePosition().getY());
    }

    public void moveY(double y) {
        double newY = getGamePosition().getY() + (y > 0 ? 1 : -1);
        this.gamePosition = new Point((int) getGamePosition().getX(), (int) newY);
    }

    public void setImmediateWorld(ImmediateWorld world) {
        this.world = world;
    }

    public void updateFrame() {
        this.frameIndex++;
    }
}
