package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import Entities.Player;
import WorldModel.Game;
import WorldModel.World;

public class TestGame {

    @Test // right
    public void testPlayerCoordinateChange1() {
        Game g = new Game(3);
        World world = g.getWorld();
        int c = 4;
        while (c > 0) {

            world.moveGame(-16, 0);
            c--;

        }
        assertEquals(new Point(16, 8), world.getCharacter().getWorldPosition());
        assertEquals(new Point(-64, 0), world.getLocation());
    }

    @Test // up
    public void testPlayerCoordinateChange2() {
        Game g = new Game(3);
        World world = g.getWorld();
        int c = 4;
        while (c > 0) {
            world.moveGame(0, 16);
            c--;
        }
        assertEquals(new Point(15, 7), world.getCharacter().getWorldPosition());
        assertEquals(new Point(0, 64), world.getLocation());
    }

    @Test // down
    public void testPlayerCoordinateChange3() {
        Game g = new Game(3);
        World world = g.getWorld();
        int c = 4;
        while (c > 0) {
            world.moveGame(0, -16);
            c--;
        }
        assertEquals(new Point(15, 9), world.getCharacter().getWorldPosition());
        assertEquals(new Point(0, -64), world.getLocation());
    }

    @Test // left
    public void testPlayerCoordinateChange4() {
        Game g = new Game(3);
        World world = g.getWorld();
        int c = 4;
        while (c > 0) {
            world.moveGame(16, 0);
            c--;
        }
        assertEquals(new Point(14, 8), world.getCharacter().getWorldPosition());
        assertEquals(new Point(64, 0), world.getLocation());
    }

    @Test // up and left
    public void testPlayerCoordinateChange5() {
        Game g = new Game(5);
        World world = g.getWorld();
        Player player = world.getCharacter();
        int c = 32;
        while (c > 0) {
            world.moveGame(16, 16);
            c--;
        }
        int c2 = 32;
        while (c2 > 0) {
            world.moveGame(16, 0);
            c2--;
        }
        assertEquals(new Point(0, 0), world.getCharacter().getWorldPosition());
    }

    @Test // up and left
    public void testPlayerCollision() {
        Game g = new Game(5);
        World world = g.getWorld();
        Player player = world.getCharacter();
        int c0 = 12;
        while (c0 > 0) {
            world.moveGame(-16, 0);
            c0--;
        }
        int c1 = 32;
        while (c1 > 0) {
            world.moveGame(16, 16);
            c1--;
        }
        int c2 = 64;
        while (c2 > 0) {
            world.moveGame(16, 0);
            c2--;
        }
        assertEquals(new Point(0, 0), world.getCharacter().getWorldPosition());
    }

}
