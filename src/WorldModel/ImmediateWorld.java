package WorldModel;

import Tools.ChunkLoader;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;

import Entities.Zombie;

public class ImmediateWorld {
    private ChunkLoader loader = new ChunkLoader();
    private List<Chunk> immediateWorld;
    private Chunk chunkWithPlayer;
    private Zombie character;
    private List<Zombie> entities = new ArrayList<>();
    private double playerMoveCountX;
    private double playerMoveCountY;

    public ImmediateWorld() {
        this.immediateWorld = loader.getChunks();
        setChunkWithPlayer();
    }

    public void handlePlayerMigration() {
        // save state of old world
        // create a new chunkLoader which will change which chunks are chosen
        // 
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
    }

    public void addWorldToPane(JLayeredPane pane) {
        for (Chunk curChunk : this.immediateWorld) {
            pane.add(curChunk, JLayeredPane.DEFAULT_LAYER);
        }
    }

    public void moveWorld(int x, int y) {
        for (Chunk curChunk : this.immediateWorld) {
            curChunk.moveChunk(x, y);
        }
    }

    public void updateChunkWithPlayer(Chunk newChunk) {
        this.chunkWithPlayer.removeContainsPlayer();
        this.chunkWithPlayer = newChunk;
    }

    public List<Zombie> getEntities() {
        return this.entities;
    }

    public void setCharacter(Zombie character) {
        this.character = character;
    }

    public void addEntity(Zombie newZombie) {
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

    public void setChunkWithPlayer() {
        this.chunkWithPlayer = findChunkWithPlayer();
    }
}
