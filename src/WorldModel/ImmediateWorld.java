package WorldModel;

import Tools.ChunkLoader;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Entities.Player;

public class ImmediateWorld extends JPanel {
    private ChunkLoader loader = new ChunkLoader();
    private List<Chunk> immediateWorld;
    private Chunk chunkWithPlayer;
    private Player character;
    private List<Player> entities = new LinkedList<>();
    private double playerMoveCountX;
    private double playerMoveCountY;
    private Point worldLocation;

    public ImmediateWorld() {
        this.immediateWorld = loader.getChunks();
        this.setLayout(null);
        this.setBounds(-Game.width, -Game.height, Game.width * 3, Game.height * 3);
        this.worldLocation = new Point(-1920, -1024);
        putChunksInWorld();
        setChunkWithPlayer();

    }

    public boolean checkPlayerMigration() {
        if (character != null) {
            for (Chunk curChunk : getImmediateWorld()) {
                Chunk newChunkWithPlayer = curChunk.containsPlayer(character.getGamePosition());
                if (newChunkWithPlayer != null && !chunkWithPlayer.equals(newChunkWithPlayer)) {
                    updateChunkWithPlayer(newChunkWithPlayer);
                    return true;
                }
            }
        }
        return false;
    }

    public void updateChunkWithPlayer(Chunk newChunk) {
        // System.out.println("updating Chunk With Player");
        this.chunkWithPlayer.removeContainsPlayer();
        this.chunkWithPlayer = newChunk;
        newChunk.setContainsPlayer();
    }

    public void move(int dx, int dy, double pdx, double pdy) {
        moveWorld(dx, dy);
        moveCharacter(pdx, pdy);
    }

    public void moveWorld(int x, int y) {
        Point newPos = new Point((int) this.worldLocation.getX() + x, (int) this.worldLocation.getY() + y);
        this.setLocation(newPos);
        this.worldLocation = newPos;

    }

    public void moveCharacter(double dx, double dy) {
        playerMoveCountX += dx;
        playerMoveCountY -= dy;

        if (playerMoveCountX >= 1) {
            character.moveX(1);
            playerMoveCountX = 0;
        } else if (playerMoveCountX <= -1) {
            character.moveX(-1);
            playerMoveCountX = 0;
        } else if (playerMoveCountY >= 1) {
            character.moveY(1);
            playerMoveCountY = 0;
        } else if (playerMoveCountY <= -1) {
            character.moveY(-1);
            playerMoveCountY = 0;
        }
        checkPlayerMigration();
    }

    public boolean pointWithinBounds(Point nextPos) {
        int playerX = (int) nextPos.getX();
        int playerY = (int) nextPos.getY();
        if (playerX > 60 || playerX < -30 || playerY > 32 || playerY < -16) {
            return false;
        }
        return true;
    }

    public List<String> determineNewChunkIndices() { // probably these for loops that dont function properly
        List<String> newChunks = new ArrayList<>();
        List<Chunk> oldWorld = getImmediateWorld();

        int playerChunkIndex = -1;
        for (int i = 0; i < oldWorld.size(); i++) {
            if (oldWorld.get(i).getContainsPlayer()) {
                playerChunkIndex = i;// may break here
                break;
            }
        }

        if (playerChunkIndex != -1) {
            switch (playerChunkIndex) {
                case 1: // Top 6 chunks
                    for (int i = 6; i <= 8; i++) {
                        Chunk chunk = oldWorld.remove(6);
                        newChunks.add(chunk.getChunkIndex().getX() + ", " + (chunk.getChunkIndex().getY() + 48));
                    }
                    break;
                case 3: // Left 6 chunks
                    for (int i = 2; i <= 4; i += 3) {
                        Chunk chunk = oldWorld.remove(2);
                        newChunks.add(chunk.getChunkIndex().getX() - 90 + ", " + chunk.getChunkIndex().getY());
                    }
                    break;
                case 5: // Right 6 chunks
                    for (int i = 0; i <= 2; i += 3) {
                        Chunk chunk = oldWorld.remove(0);
                        newChunks.add(chunk.getChunkIndex().getX() + 90 + ", " + chunk.getChunkIndex().getY());
                    }
                    break;
                case 7: // Bottom 7 chunks
                    for (int i = 0; i <= 2; i++) {
                        Chunk chunk = oldWorld.remove(0);
                        newChunks.add(chunk.getChunkIndex().getX() + ", " + (chunk.getChunkIndex().getY() - 48));
                    }
                    break;
            }
        }
        for (Chunk cur : oldWorld) {
            newChunks.add(cur.getChunkIndex().getX() + ", " + cur.getChunkIndex().getY());
        }

        return newChunks;
    }

    public void addWorldToPane(JLayeredPane pane) {
        for (Chunk curChunk : this.immediateWorld) {
            pane.add(curChunk, JLayeredPane.DEFAULT_LAYER);
        }
    }

    public List<Player> getEntities() {
        return this.entities;
    }

    public void setCharacter(Player character) {
        this.character = character;
    }

    public void addEntity(Player newZombie) {
        this.entities.add(newZombie);
    }

    public Chunk getChunkWithPlayer() {
        return this.chunkWithPlayer;
    }

    public List<Chunk> getImmediateWorld() {
        return this.immediateWorld;
    }

    public Chunk findChunkWithPlayer() {
        for (Chunk curChunk : getImmediateWorld()) {
            if (curChunk.getContainsPlayer()) {
                return curChunk;
            }
        }
        return null;
    }

    public Chunk getChunk(int index) {
        return this.immediateWorld.get(index);
    }

    public void setChunkWithPlayer() {
        this.chunkWithPlayer = findChunkWithPlayer();
    }

    public boolean contains(Chunk chunk) {
        for (Chunk curChunk : immediateWorld) {
            if (curChunk.equals(chunk)) {
                return true;
            }
        }
        return false;
    }

    public void putChunksInWorld() {
        for (Component chunk : this.immediateWorld) {
            this.add(chunk);
        }
    }
}
