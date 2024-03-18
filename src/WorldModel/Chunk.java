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
    private Point chunkLocation;

    public Chunk(int x, int y) {
        this.chunkLocation = new Point(x, y);
        this.blockWidth = 64;
        this.blockHeight = 64;
        this.setLayout(null);
        this.setBounds(x, y, Game.width, Game.height);
        this.makeBackground();
        this.createPoints();

    }

    public Point getChunkLocation() {
        return this.chunkLocation;
    }

    public void makeBackground() {
        int numBlocksX = 30; //Game.width / this.blockWidth;
        int numBlocksY = 16; // Game.height / this.blockHeight;


        for (int y = 0; y < numBlocksY; y++) {
            for (int x = 0; x < numBlocksX; x++) {
                JLabel block = new JLabel(new ImageIcon(getClass().getResource("/images/GrassBlockLargeRatio.png")));
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

    public void createPoints() {
        int startY = (int) getChunkLocation().getY();
        int startX = (int) getChunkLocation().getX();

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

}
