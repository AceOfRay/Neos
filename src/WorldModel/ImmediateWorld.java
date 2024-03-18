package WorldModel;

import Tools.ChunkLoader;
import java.util.List;

import javax.swing.JLayeredPane;

public class ImmediateWorld {
    private ChunkLoader loader = new ChunkLoader();
    private List<Chunk> immediateWorld;
    private Chunk chunkWithPlayer;

    public ImmediateWorld() {
        this.immediateWorld = loader.getChunks();
        setChunkWithPlayer();
    }

    public void addWorldToPane(JLayeredPane pane) {
        for (Chunk curChunk : this.immediateWorld) {
            pane.add(curChunk, JLayeredPane.DEFAULT_LAYER);
        }
    }

    public void move(int x, int y) {
        for (Chunk curChunk : this.immediateWorld) {
            curChunk.moveChunk(x, y);
        }
    }

    public boolean checkPlayerMigration() {
        return false;
    }

    public Chunk getChunkWithPlayer() {
        return this.chunkWithPlayer;
    }

    public List<Chunk> getImmediateWorld() {
        return this.immediateWorld;
    }

    public Chunk findChunkWithPlayer() {
        for (Chunk curChunk : this.immediateWorld) {
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
