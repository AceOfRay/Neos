package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.awt.Component;
import java.awt.Point;
import java.util.List;

import javax.swing.JPanel;

import org.junit.jupiter.api.Test;

import Entities.LavenderTree;
import WorldModel.Chunk;
import WorldModel.ImmediateWorld;

public class TestImmediateWorld {
    @Test
    public void testChunkPixelLocations1() {
        ImmediateWorld world = new ImmediateWorld();
        List<Chunk> chunks = world.getImmediateWorld();
        assertEquals(new Point(1920, 1024), chunks.get(6).getChunkPixelLocation());
    }

    @Test
    public void testImmediateWorldSize() {
        ImmediateWorld world = new ImmediateWorld();
        List<Chunk> chunks = world.getImmediateWorld();
        assertEquals(25, chunks.size());
    }

    @Test
    public void testWorldChunksPxPlacement_Sim() {
        List<Chunk> simulatedChunks = List.of(
                new Chunk(0, 0), new Chunk(30, 30), new Chunk(60, 60));
        int hcnt = 0;
        int vcnt = 0;

        for (Chunk c : simulatedChunks) { // placing chunks where they should be
            c.placeChunk(hcnt, vcnt);
            hcnt += 1920;
            vcnt += 1024;
        }

        Chunk cwp = simulatedChunks.get(1);
        cwp.setContainsPlayer();
        ImmediateWorld world = new ImmediateWorld(simulatedChunks, cwp);

        hcnt = 0;
        vcnt = 0;
        List<Chunk> chunks = world.getImmediateWorld();
        for (Chunk c : chunks) {
            assertEquals(new Point(hcnt, vcnt), c.getLocation());
            hcnt += 1920;
            vcnt += 1024;
        }
    }

    @Test
    public void testWorldChunksPxPlacement_Actual() {
        int hcnt = 0;
        int vcnt = 0;
        ImmediateWorld world = new ImmediateWorld();
        List<Chunk> chunks = world.getImmediateWorld();
        for (Chunk c : chunks) {
            if (vcnt > 4096) {
                break;
            }
            if (hcnt > 7680) {
                hcnt = 0;
                vcnt += 1024;
            }
            assertEquals(new Point(hcnt, vcnt), c.getLocation());
            hcnt += 1920;
        }
    }

    @Test
    public void testWorldChunkIndexes() {
        ImmediateWorld world = new ImmediateWorld();
        List<Chunk> chunks = world.getImmediateWorld();
        int hcnt = 0;
        int vcnt = 0;
        for (Chunk c : chunks) {
            if (vcnt > 64) {
                break;
            }
            if (hcnt > 120) {
                hcnt = 0;
                vcnt += 16;
            }
            assertEquals(new Point(hcnt, vcnt), c.getChunkIndex());
            hcnt += 30;
        }
    }

    @Test
    public void testImmediateWorldChunkWithPlayer() {
        List<Chunk> simulatedChunks = List.of(
                new Chunk(0, 0), new Chunk(30, 0), new Chunk(60, 0));
        Chunk cwp = simulatedChunks.get(1);
        cwp.setContainsPlayer();
        ImmediateWorld world = new ImmediateWorld(simulatedChunks, cwp);
        assertEquals(cwp, world.getChunkWithPlayer());
    }

    /**
     * These 2 tests ensures that the location of the immediate world
     * upon spawn changes depending on spawn location relative to chunk and not
     * point
     */
    @Test
    public void testWorldPlayerSpawn() {
        List<Chunk> simulatedChunks = List.of(
                new Chunk(0, 0), new Chunk(30, 0), new Chunk(60, 0));
        Chunk cwp = simulatedChunks.get(1);
        cwp.setContainsPlayer();
        cwp.placeChunk(1920, 0);
        ImmediateWorld world = new ImmediateWorld(simulatedChunks, cwp);
        assertEquals(new Point(-1920, 0), world.getLocation());
        assertNotEquals(new Point(0, 0), world.getLocation());
    }

    @Test
    public void testWorldPlayerSpawn2() {
        List<Chunk> simulatedChunks = List.of(
                new Chunk(0, 0), new Chunk(30, 0), new Chunk(60, 0));
        Chunk cwp = simulatedChunks.get(2);
        cwp.setContainsPlayer();
        cwp.placeChunk(3840, 0);
        ImmediateWorld world = new ImmediateWorld(simulatedChunks, cwp);
        assertEquals(new Point(-3840, 0), world.getLocation());
        assertNotEquals(new Point(0, 0), world.getLocation());
    }

    @Test
    public void testEntityPlacement1() {
        List<Chunk> simulatedChunks = List.of(
                new Chunk(0, 0), new Chunk(30, 0), new Chunk(60, 0));
        Chunk cwp = simulatedChunks.get(0);
        cwp.setContainsPlayer();
        cwp.placeChunk(0, 0);

        ImmediateWorld world = new ImmediateWorld(simulatedChunks, cwp);
        LavenderTree t = new LavenderTree(8, 8, world);
        world.placeEntity(t);
        for (Component c : cwp.getComponents()) {
            if (c instanceof LavenderTree) {
                System.out.println(c);
            }
        }
        assertEquals(new Point(8 * 64, 8 * 64), t.getLocation());
        assertEquals(481, cwp.getComponentCount());
    }

}
