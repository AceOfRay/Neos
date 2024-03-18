package WorldModel;

import Tools.ChunkLoader;
import java.util.List;

public class ImmediateWorld {
    private ChunkLoader loader = new ChunkLoader();
    private List<Chunk> immediateWorld;
    private Chunk chunkWithPlayer;


    public ImmediateWorld() {
        this.immediateWorld = loader.getChunks();
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

    public void move(int x, int y) {

    }

    public boolean checkPlayerMigration() {
        return false;
    }

    public Chunk getChunkWithPlayer() {
        return this.chunkWithPlayer;
    }
}

