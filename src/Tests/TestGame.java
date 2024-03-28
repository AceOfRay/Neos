package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import Entities.Player;
import WorldModel.Game;
import WorldModel.ImmediateWorld;

public class TestGame {
    
    @Test //right
    public void testPlayerCoordinateChangeOnMovement1() {
        Game g = new Game(3);
        ImmediateWorld world = g.getImmediateWorld();
        int c = 4;
        while (c > 0) {
            world.move(-16, 0, .25, 0);
            c--;
        }
        assertEquals(new Point(16, 8), world.getCharacter().getWorldPosition());
        assertEquals(new Point(-64, 0), world.getLocation());
    }

    @Test //up
    public void testPlayerCoordinateChangeOnMovement2() {
        Game g = new Game(3);
        ImmediateWorld world = g.getImmediateWorld();
        int c = 4;
        while (c > 0) {
            world.move(0, 16, 0, -.25);
            c--;
        }
        assertEquals(new Point(15, 7), world.getCharacter().getWorldPosition());
        assertEquals(new Point(0, 64), world.getLocation());
    }

    @Test //down
    public void testPlayerCoordinateChangeOnMovement3() {
        Game g = new Game(3);
        ImmediateWorld world = g.getImmediateWorld();
        int c = 4;
        while (c > 0) {
            world.move(0, -16, 0, .25);
            c--;
        }
        assertEquals(new Point(15, 9), world.getCharacter().getWorldPosition());
        assertEquals(new Point(0, -64), world.getLocation());
    }

    @Test // left
    public void testPlayerCoordinateChangeOnMovement4() {
        Game g = new Game(3);
        ImmediateWorld world = g.getImmediateWorld();
        int c = 4;
        while (c > 0) {
            world.move(16, 0, -.25, 0);
            c--;
        }
        assertEquals(new Point(14, 8), world.getCharacter().getWorldPosition());
        assertEquals(new Point(64, 0), world.getLocation());
    }

    @Test // up and left
    public void testPlayerCoordinateChangeOnMovement5() {
        Game g = new Game(5);
        ImmediateWorld world = g.getImmediateWorld();
        Player player = world.getCharacter();
        int c = 32;
        while (c > 0) {
            world.move(16, 16, -.25, -.25);
            c--;
        }
        int c2 = 32;
        while (c2 > 0) {
            world.move(16, 0, -.25, 0);
            c2--;
        }
        assertEquals(new Point(0, 0), world.getCharacter().getWorldPosition());
    }

    @Test // up and left
    public void testPlayerCollision() {
        Game g = new Game(5);
        ImmediateWorld world = g.getImmediateWorld();
        Player player = world.getCharacter();
        int c0 = 12;
        while (c0 > 0) {
            world.move(-16, 0, .25, 0);
            c0--;
        }
        int c1 = 32;
        while (c1 > 0) {
            world.move(16, 16, -.25, -.25);
            c1--;
        }
        int c2 = 64;
        while (c2 > 0) {
            world.move(16, 0, -.25, 0);
            c2--;
        }
        assertEquals(new Point(0, 0), world.getCharacter().getWorldPosition());
    }

}
