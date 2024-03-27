package WorldModel;

import javax.swing.*;

import Entities.Entity;
import Entities.LavenderTree;

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
        this.blockWidth = 64;
        this.blockHeight = 64;
        this.makeBackground();
        this.createPoints();
        this.setPreferredSize(new Dimension(Game.width, Game.height));
    }

    public void placeChunk(int pxLoc, int pyLoc) {
        this.chunkPixelLocation = new Point(pxLoc, pyLoc);
        this.setBounds((int) chunkPixelLocation.getX(), (int) chunkPixelLocation.getY(), Game.width, Game.height);
    }

    public void placeEntity(Entity e) {
        LavenderTree lt = (LavenderTree) e;
        Point pxRelativeToChunk = determinePXLocation(lt.getGamePosition());
        lt.setBounds((int)pxRelativeToChunk.getX(), (int) pxRelativeToChunk.getY(), 96, 96);
        lt.setPXLocation(pxRelativeToChunk);
        JPanel ltPanel = (JPanel) lt;

        this.add(ltPanel, JLayeredPane.PALETTE_LAYER);
        System.out.println("added" + lt);
    }

    public Point determinePXLocation(Point p) {
        Point idx = getChunkIndex();
        Point relativePosition = new Point((int) (p.getX() - idx.getX()), (int) (p.getY() - idx.getY()));
        return new Point((int) relativePosition.getX() * 64, (int) relativePosition.getY() * 64);
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
}
