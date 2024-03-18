package Tests;

import Tools.ChunkLoader;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestChunkLoader {
    @Test
    public void testChunks() {
        ChunkLoader example = new ChunkLoader();
        assertEquals(9, example.getChunks().size());
    }
}