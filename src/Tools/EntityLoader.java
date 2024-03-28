package Tools;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import Entities.Cow;
import Entities.LavenderTree;
import Entities.Player;
import WorldModel.ImmediateWorld;

public class EntityLoader {
    private ImmediateWorld world;
    private Player character;

    public EntityLoader(ImmediateWorld world) {
        this.world = world;
        try {
            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream("GameResources/Files/entities.txt");
            if (inputStream != null) {
                loadEntitiesFromInputStream(inputStream);
            } else {
                System.out.println("Could not find the desired file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading chunks from file: " + e.getMessage());
        }
    }

    public EntityLoader(ImmediateWorld world, String filepath) {
        this.world = world;
        try {
            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream(filepath);
            if (inputStream != null) {
                loadEntitiesFromInputStream(inputStream);
            } else {
                System.out.println("Could not find the desired file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading chunks from file: " + e.getMessage());
        }
    }

    private void loadEntitiesFromInputStream(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("#")) {
                    String[] pieces = line.split(" ");
                    switch (pieces[0]) {
                        case "p": {
                            this.character = new Player(
                                    new Point(Integer.parseInt(pieces[1]), Integer.parseInt(pieces[2])), 0, 0, world);
                            world.addEntity(character);
                            world.setCharacter(character);
                            break;
                        }
                        case "lt": {
                            LavenderTree tree = new LavenderTree(
                                    new Point(Integer.parseInt(pieces[1]), Integer.parseInt(pieces[2])), world);
                            world.placeEntity(tree);
                            world.addEntity(tree);
                            break;
                        }
                        case "cow": {
                            Cow cow = new Cow(new Point(Integer.parseInt(pieces[1]), Integer.parseInt(pieces[2])),
                                    world);
                            world.placeEntity(cow);
                            world.addEntity(cow);
                            break;
                        }
                        default:
                            break;
                    }
                }
            }
        }
    }
}
