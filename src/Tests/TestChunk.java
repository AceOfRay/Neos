package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import WorldModel.Chunk;

public class TestChunk {

    @Test
    public void testChunkPointLocations1() {
        Chunk c = new Chunk(0, 0);
        Point start = c.getChunkPoints().get(0).get(0);
        Point end = c.getChunkPoints().get(15).get(29);
        Point bottomLeft = c.getChunkPoints().get(15).get(0);
        Point topRight = c.getChunkPoints().get(0).get(15);
        assertEquals(new Point(0, 0), start);
        assertEquals(new Point(29, 15), end);
        assertEquals(new Point(0, 15), bottomLeft);
        assertEquals(new Point(15, 0), topRight);
    }

    @Test
    public void testChunkPointLocations2() {
        Chunk c = new Chunk(0, 0);
        assertNotEquals(new Point (-1, -1), c.getChunkIndex());
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
        assertNotEquals(c1, c2);
    }

}
