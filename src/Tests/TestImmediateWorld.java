package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;
import java.util.List;

import org.junit.jupiter.api.Test;

import Entities.Zombie;
import WorldModel.Chunk;
import WorldModel.ImmediateWorld;

public class TestImmediateWorld {
    @Test
    public void testChunkPixelLocations1() {
        ImmediateWorld world = new ImmediateWorld();
        List<Chunk> chunks = world.getImmediateWorld();
        assertEquals(new Point(-1920, 1024), chunks.get(6).getChunkPixelLocation());
    }

    @Test
    public void testImmediateWorldSize() {
        ImmediateWorld world = new ImmediateWorld();
        List<Chunk> chunks = world.getImmediateWorld();
        for (Chunk curChunk : chunks) {
            System.out.println(curChunk);
        }
        assertEquals(9, chunks.size());
    }
}
