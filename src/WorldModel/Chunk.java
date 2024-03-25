package WorldModel;

import javax.swing.*;
import java.awt.Point;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Chunk extends JPanel {

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
        this.setLayout(null);
        this.makeBackground();
        this.createPoints();

    }

    public void placeChunk(int pxLoc, int pyLoc) {
        this.chunkPixelLocation = new Point(pxLoc, pyLoc);
        this.setBounds((int) chunkPixelLocation.getX(), (int) chunkPixelLocation.getY(), Game.width, Game.height);
    }

    public void moveChunk(int x, int y) {
        Point newPos = new Point((int) this.chunkPixelLocation.getX() + x, (int) this.chunkPixelLocation.getY() + y);
            this.setLocation(newPos);
            this.chunkPixelLocation = newPos;
    }

    public boolean pixelWithinBounds(Point px) {
        double x = px.getX();
        double y = px.getY();
        if (x <= -3840 || x >= 1920 || y <= -1024 || y >= 2048) {
            return false;
        }
        return true;
    }

    public void makeBackground() {
        int numBlocksX = 30; // Game.width / this.blockWidth;
        int numBlocksY = 16; // Game.height / this.blockHeight;
        Random rand = new Random();
        for (int y = 0; y < numBlocksY; y++) {
            for (int x = 0; x < numBlocksX; x++) {
                JLabel block = new JLabel(
                        new ImageIcon(getClass().getResource(
                                "/GameResources/Images/GrassBlocks/GrassBlock" + rand.nextInt(4) + ".png")));
                block.setBounds(x * blockWidth, y * blockHeight, blockWidth, blockHeight);
                this.add(block);
            }
        }
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
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        Chunk c;
        if (other instanceof Chunk) {
            c = (Chunk) other;
        } else {
            return false;
        }
        
        return getChunkIndex().equals(c.getChunkIndex());
    }

    public Point getChunkIndex() {
        return this.chunkIndex;
    }

    public Point getChunkPixelLocation() {
        return this.chunkPixelLocation;
    }

    public void createPoints() {
        int startY = (int) getChunkIndex().getX();
        int startX = (int) getChunkIndex().getY();
    
        for (int i = 0; i < 16; i++) {  
            List<Point> row = new ArrayList<>();
            for (int j = 0; j < 30; j++) {  
                row.add(new Point(j + startX, i + startY));
            }
            this.chunkPoints.add(row);
        }
    }
    

    @Override
    public String toString() {
        return "Chunk @ GamePoint: " + "[" + getChunkIndex().getX() + ", " + getChunkIndex().getY() + "]"
                + " Contains Player: " + getContainsPlayer();
    }

    public List<List<Point>> getChunkPoints() {
        return this.chunkPoints;
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
}
