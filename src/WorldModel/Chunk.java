package WorldModel;

import javax.swing.*;
import java.awt.Point;
import java.util.List;
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
        placeChunk();
        this.makeBackground();
        this.createPoints();

    }

    private void placeChunk() {
        int x = (this.chunkIndex.getX() > 0) ? Game.width : (this.chunkIndex.getX() < 0) ? -Game.width : 0;
        int y = (this.chunkIndex.getY() > 0) ? Game.height : (this.chunkIndex.getY() < 0) ? -Game.height : 0;
        this.chunkPixelLocation = new Point(x, y);

        this.setBounds(x, y, Game.width, Game.height);
    }

    public void moveChunk(int x, int y) {
        Point newPos = new Point((int)this.chunkPixelLocation.getX() + x, (int) this.chunkPixelLocation.getY() + y);
        this.setLocation(newPos);
        this.chunkPixelLocation = newPos;
    }

    public Point getChunkIndex() {
        return this.chunkIndex;
    }

    public Point getChunkPixelLocation() {
        return this.chunkPixelLocation;
    }

    public void makeBackground() {
        int numBlocksX = 30; // Game.width / this.blockWidth;
        int numBlocksY = 16; // Game.height / this.blockHeight;

        for (int y = 0; y < numBlocksY; y++) {
            for (int x = 0; x < numBlocksX; x++) {
                JLabel block = new JLabel(
                        new ImageIcon(getClass().getResource("/GameResources/Images/GrassBlock1.png")));
                block.setBounds(x * blockWidth, y * blockHeight, blockWidth, blockHeight);
                this.add(block);
            }
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

    public Chunk containsPlayer(Point playerPos) { // can optimize using maxes and mins
        for (List<Point> row : chunkPoints) {
            for (Point col : row) {
                if (col.equals(playerPos)) {
                    this.containsPlayer = true;
                    return this;
                }
            }
        }
        this.containsPlayer = false;
        return null;
    
    }

    public void createPoints() {
        int startY = (int) getChunkIndex().getY();
        int startX = (int) getChunkIndex().getX();

        for (int i = 0; i < 16; i++) {
            List<Point> row = new ArrayList<>();
            for (int j = 0; j < 30; j++) {
                row.add(new Point(i + startX, j + startY)); // if this doesn't work we swap start x and start y
            }
            this.chunkPoints.add(row);
        }
    }

    @Override
    public String toString() {
        return "Chunk @ Point: " + getLocation().toString() + " Contains Player: " + this.containsPlayer;
    }

    public List<List<Point>> getChunkPoints() {
        return this.chunkPoints;
    }

}
