package WorldModel;

import Tools.ChunkLoader;
import java.awt.Component;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import Entities.Player;
import Entities.AbstractClasses.Entity;

public class World extends JPanel {
    private ChunkLoader loader = new ChunkLoader();
    private List<Chunk> immediateWorld;
    private Chunk chunkWithPlayer;
    private Player character;
    private List<Entity> entities = new LinkedList<>();
    private Point worldLocation;

    public World() {
        this.immediateWorld = loader.getChunks();
        this.setLayout(null);
        this.setBounds(0, 0, Game.width * 10, Game.height * 10);
        putChunksInWorld();
        setChunkWithPlayer();
        setWorldLocation();
    }

    public boolean checkPlayerMigration() {
        if (character != null) {
            for (Chunk curChunk : getImmediateWorld()) {
                Chunk newChunkWithPlayer = curChunk.containsPlayer(character.getWorldPosition());
                if (newChunkWithPlayer != null && !chunkWithPlayer.equals(newChunkWithPlayer)) {
                    updateChunkWithPlayer(newChunkWithPlayer);
                    return true;
                }
            }
        }
        return false;
    }

    public void placeEntity(Entity e) {
        Chunk c = findChunk(e);
        if (c != null) {
            c.placeEntity(e);
        }

    }

    public Chunk findChunk(Entity e) {
        for (Chunk c : this.immediateWorld) {
            if (c.pointWithinBounds(e.getWorldPosition())) {
                return c;
            }
        }
        return null;
    }

    public void updateChunkWithPlayer(Chunk newChunk) {
        this.chunkWithPlayer.removeContainsPlayer();
        this.chunkWithPlayer = newChunk;
        newChunk.setContainsPlayer();
    }

    public void moveGame(int dx, int dy) {
        Point newPos = new Point((int) this.worldLocation.getX() + dx, (int) this.worldLocation.getY() + dy);
        Point newPlayerCoord = createNewPlayerCoord(newPos);
        if (pxWithinBounds(newPos) && !pointOccupied(newPlayerCoord) ) {
            moveWorld(newPos);
            moveCharacter(newPlayerCoord);
        }
    }

    public Point createNewPlayerCoord(Point pxPos) {
        return new Point((-pxPos.x / 128 + 7), (-pxPos.y / 128 + 4)); // check the + 15 and + 8 in the next test phase
    }

    public void moveWorld(Point np) {
        this.setLocation(np);
        this.worldLocation = np;

    }

    public void moveCharacter(Point np) {
        character.updatePosition(np);
        checkPlayerMigration();
    }

    public boolean pointOccupied(Point p) {
        for (Entity e : entities) {
            if (e.getWorldPosition().equals(p) && !(e instanceof Player)) {
                return true;
            }
        }
        return false;
    }

    public boolean pxWithinBounds(Point pxLoc) {  // comeback and fix
        double x = pxLoc.getX();
        double y = pxLoc.getY();
        if (x > 960 || x < -18176 || y > 512 || y < -9664) {
            return false;
        }
        return true;
    }

    public Chunk findChunkWithPlayer() {
        for (Chunk curChunk : getImmediateWorld()) {
            if (curChunk.getContainsPlayer()) {
                return curChunk;
            }
        }
        return null;
    }

    public boolean contains(Chunk chunk) {
        for (Chunk curChunk : immediateWorld) {
            if (curChunk.equals(chunk)) {
                return true;
            }
        }
        return false;
    }

    public void setCamera() {
        Point cP = character.getWorldPosition();
        this.worldLocation = new Point(-((cP.x - 7) * 128), -((cP.y - 4) * 128));
        setLocation(this.worldLocation.x, this.worldLocation.y);
    }

    public void putChunksInWorld() {
        for (Component chunk : this.immediateWorld) {
            this.add(chunk);
        }
    }

    public void setWorldLocation() {
        Point cwp = this.chunkWithPlayer.getChunkPixelLocation();
        this.worldLocation = new Point(-cwp.x, -cwp.y);
        this.setLocation(worldLocation);
    }

    public World(List<Chunk> chunks, Chunk cwp) { // test constructor
        this.immediateWorld = chunks;
        this.setLayout(null);
        this.setBounds(0, 0, Game.width * 5, Game.height * 5);
        putChunksInWorld();
        setChunkWithPlayer();
        setWorldLocation();
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    public Chunk getChunk(int index) {
        return this.immediateWorld.get(index);
    }

    public void setChunkWithPlayer() {
        this.chunkWithPlayer = findChunkWithPlayer();
    }

    public void setCharacter(Player character) {
        this.character = character;
    }

    public Player getCharacter() {
        return this.character;
    }

    public void addEntity(Entity e) {
        this.entities.add(e);
    }

    public Chunk getChunkWithPlayer() {
        return this.chunkWithPlayer;
    }

    public List<Chunk> getImmediateWorld() {
        return this.immediateWorld;
    }

    public Point getWorldLocation() {
        return this.worldLocation;
    }
}
