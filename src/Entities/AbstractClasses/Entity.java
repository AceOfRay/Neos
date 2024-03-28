package Entities.AbstractClasses;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Entities.Player;
import Entities.Tree;
import Tools.Direction;
import WorldModel.Game;
import WorldModel.ImmediateWorld;

public abstract class Entity extends JPanel {
    private Point pxLocation;
    protected ImmediateWorld world;
    public Direction facingDirection = Direction.Left;
    public boolean isWalking;
    protected Point worldPos;
    private int frameIndex;
    private static final int frameMax = 2;

    public Entity(Point pos, ImmediateWorld world) {
        this.frameIndex = 0;
        this.worldPos = pos;
        this.world = world;
        this.setOpaque(false);
    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.frameIndex >= frameMax) {
            this.frameIndex = 0;
        }
        URL frame = getClass().getResource(getFrame() + this.frameIndex + ".png");
        try {
            BufferedImage eImage = ImageIO.read(frame);

            Point placement = getImagePlacement();
            g.drawImage((Image) eImage, placement.x, placement.y, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public abstract String getFrame();

    public Point getImagePlacement() {
        if (this instanceof Player) {
            return new Point(Game.width / 2, Game.height / 2);
        } else if (this instanceof Tree) {
            return new Point(0, 0);
        } else {
            return new Point(0, 0);
        }
    }

    public Point getWorldPosition() {
        return this.worldPos;
    }

    public void setImmediateWorld(ImmediateWorld world) {
        this.world = world;
    }

    public void updateFrame() {
        this.frameIndex++;
    }

    public void setPXLocation(Point p) {
        this.pxLocation = p;
    }
}
