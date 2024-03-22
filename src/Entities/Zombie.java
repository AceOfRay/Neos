package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import WorldModel.Game;
import WorldModel.ImmediateWorld;

public class Zombie extends JPanel {
    private Point gamePosition;
    private ImmediateWorld world;

    public Zombie(double x, double y, ImmediateWorld world) {
        setBounds(0, 0, 1920, 1080);
        setOpaque(false);
        this.gamePosition = new Point((int) x, (int) y);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        URL dudeLocation = getClass().getResource("/GameResources/Images/ZombieCharacter.png");
        try {
            BufferedImage dude = ImageIO.read(dudeLocation);
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
}
