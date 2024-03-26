package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.awt.Point;
import java.util.List;

import org.junit.jupiter.api.Test;

import WorldModel.Chunk;

public class TestChunk {

    @Test
    public void testChunkPointLocations1() {
        Chunk c = new Chunk(0, 0);
        Point start = c.getChunkPoints().get(0).get(0);
        Point end = c.getChunkPoints().get(15).get(29);
        Point bottomLeft = c.getChunkPoints().get(15).get(0);
        Point topRight = c.getChunkPoints().get(0).get(29);
        assertEquals(new Point(0, 0), start);
        assertEquals(new Point(29, 15), end);
        assertEquals(new Point(0, 15), bottomLeft);
        assertEquals(new Point(29, 0), topRight);
    }

    @Test
    public void testChunkPointLocations2() {
        Chunk c = new Chunk(0, 0);
        assertNotEquals(new Point (-1, -1), c.getChunkIndex());
    }

    @Test
    public void testChunkPointLocations3() {
        Chunk c = new Chunk(30, 16);
        Point start = c.getChunkPoints().get(0).get(0);
        Point end = c.getChunkPoints().get(15).get(29);
        Point bottomLeft = c.getChunkPoints().get(15).get(0);
        Point topRight = c.getChunkPoints().get(0).get(29);
        assertEquals(new Point(30, 16), start);
        assertEquals(new Point(59, 31), end);
        assertEquals(new Point(30, 31), bottomLeft);
        assertEquals(new Point(59, 16), topRight);
    }

    @Test
    public void testChunkPointsX() {
        List<Chunk> simulatedChunks = List.of(
                new Chunk(0, 0), new Chunk(30, 0), new Chunk(60, 0));
        int cnt = 0;
        for (Chunk c : simulatedChunks) {
            assertEquals(new Point (cnt, 0), c.getChunkPoints().get(0).get(0));
            cnt += 30;
        }
    }

    @Test
    public void testChunkPointsY() {
        List<Chunk> simulatedChunks = List.of(
                new Chunk(0, 0), new Chunk(0, 16), new Chunk(0, 32));
        int cnt = 0;
        for (Chunk c : simulatedChunks) {
            assertEquals(new Point (0, cnt), c.getChunkPoints().get(0).get(0));
            cnt += 16;
        }
    }


    @Test
    public void testChunkEquals1() {
        Chunk c1 = new Chunk (-1, -1);
        Chunk c2 = new Chunk (1, 1);
        assertNotEquals(c1, c2);
    }

    @Test
    public void testChunkEquals2() {
        Chunk c1 = new Chunk (1, 1);
        Chunk c2 = new Chunk (1, 1);
        assertEquals(c1, c2);
    }

}
