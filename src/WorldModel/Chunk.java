package WorldModel;

import javax.swing.*;
import Entities.Tree;
import Entities.AbstractClasses.Entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chunk extends JLayeredPane {

    protected int blockWidth;
    protected int blockHeight;
    private boolean containsPlayer = false;

    private List<List<Point>> chunkPoints = new ArrayList<>();
    private Point chunkIndex;
    private Point chunkPixelLocation;

    public Chunk(int x, int y) {
        this.chunkIndex = new Point(x, y);
        this.blockWidth = 128;
        this.blockHeight = 128;
        this.makeBackground();
        this.createPoints();
        this.setPreferredSize(new Dimension(Game.width * 2, Game.height * 2));
    }

    public void placeChunk(int pxLoc, int pyLoc) {
        this.chunkPixelLocation = new Point(pxLoc, pyLoc);
        this.setBounds((int) chunkPixelLocation.getX(), (int) chunkPixelLocation.getY(), Game.width * 2, Game.height * 2);
    }

    public void placeEntity(Entity e) {
        int wh = 64;
        int diff = 0;
        if (e instanceof Tree) {
            wh = 128;
            diff = 32;
        }
        Point pxRelativeToChunk = determinePXLocation(e.getWorldPosition());
        e.setBounds((int) pxRelativeToChunk.getX(), (int) pxRelativeToChunk.getY() + diff, wh, wh);
        e.setPXLocation(pxRelativeToChunk);
        this.add(e, (int) (100 - e.getWorldPosition().getY()));
    }

    public Point determinePXLocation(Point p) {
        Point idx = getChunkIndex();
        Point relativePosition = new Point((int) (p.getX() - idx.getX()), (int) (p.getY() - idx.getY()));
        return new Point((int) relativePosition.getX() * 128, (int) relativePosition.getY() * 128); 
    }
    public void makeBackground() {
        int numBlocksX = 30; // Game.width / this.blockWidth;
        int numBlocksY = 16; // Game.height / this.blockHeight;
        Random rand = new Random();
        for (int y = 0; y < numBlocksY; y++) {
            for (int x = 0; x < numBlocksX; x++) {
                JLabel block = new JLabel(new ImageIcon(getClass().getResource(
                        "/GameResources/Images/GrassBlocks/GrassBlock" + rand.nextInt(4) + ".png")));
                block.setBounds(x * blockWidth, y * blockHeight, blockWidth, blockHeight);
                this.add(block, JLayeredPane.DEFAULT_LAYER);
            }
        }
    }

    public boolean pointWithinBounds(Point p) {
        int left = (int) getChunkIndex().getX();
        int top = (int) getChunkIndex().getY();
        int right = left + 29;
        int bottom = top + 15;

        double eX = p.getX();
        double eY = p.getY();

        if (eX > left && eX < right && eY > top && eY < bottom) {
            return true;
        }
        return false;
    }

    public Chunk containsPlayer(Point playerPos) {
        double chunkX = getChunkIndex().getX();
        double chunkY = getChunkIndex().getY();
        double playerX = playerPos.getX();
        double playerY = playerPos.getY();
        if (chunkX > playerX || chunkY > playerY || playerX > (chunkX + 29) || playerY > (chunkY + 15)) { // checks out
                                                                                                          // of bounds
            this.containsPlayer = false;
            return null;
        }
        return this;
    }

    @Override
    public String toString() {
        return "Chunk @ GamePoint: " + "[" + getChunkIndex().getX() + ", " + getChunkIndex().getY() + "]"
                + " Contains Player: " + containsPlayer;
    }

    public List<List<Point>> getChunkPoints() {
        return this.chunkPoints;
    }

    public Point getChunkIndex() {
        return this.chunkIndex;
    }

    public void createPoints() {
        int startX = (int) getChunkIndex().getX();
        int startY = (int) getChunkIndex().getY();

        for (int i = 0; i < 16; i++) {
            List<Point> row = new ArrayList<>();
            for (int j = 0; j < 30; j++) {
                row.add(new Point(j + startX, i + startY));
            }
            this.chunkPoints.add(row);
        }
    }

    public void setContainsPlayer() {
        this.containsPlayer = true;
    }

    public void removeContainsPlayer() {
        this.containsPlayer = false;
    }

    public boolean getContainsPlayer() {
        return this.containsPlayer;
    }

    public Point getChunkPixelLocation() {
        return this.chunkPixelLocation;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Chunk)) {
            return false;
        }
        Chunk other = (Chunk) obj;
        return getChunkIndex().equals(other.getChunkIndex());
    }
}
