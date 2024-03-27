package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import WorldModel.ImmediateWorld;

public class LavenderTree extends JPanel implements Tree, Entity {
    private Point gamePosition;
    private Point pxLocation;
    private ImmediateWorld world;
    private int frameIndex = 0;
    private int frameMax = 2;

    public LavenderTree(double x, double y, ImmediateWorld world) {
        setBounds(0, 0, 96, 96);
        setOpaque(false);
        this.gamePosition = new Point((int) x, (int) y);
        this.world = world;
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (frameIndex >= frameMax) {
            frameIndex = 0;
        }
        URL frame = getFrame();
        try {
            BufferedImage entity = ImageIO.read(frame);
            Image e = (Image) entity;
            g.drawImage(e, 0, 0, this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Point getGamePosition() {
        return this.gamePosition;
    }

    public void setImmediateWorld(ImmediateWorld world) {
        this.world = world;
    }

    public void updateFrame() {
        this.frameIndex++;
    }

    public URL getFrame() {
        return getClass().getResource("/GameResources/Images/Trees/LavenderTree0.png");
    }

    public void setPXLocation(Point p) {
        this.pxLocation = p;
    }
}
