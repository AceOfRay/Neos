package Tests;

import Tools.ChunkLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestChunkLoader {
    @Test
    public void testChunks() {
        ChunkLoader example = new ChunkLoader();
        assertEquals(9, example.getChunks().size());
    }
}