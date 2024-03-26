package Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
                    if (line.startsWith("p")) {
                        String[] pieces = line.split(" ");
                        // check if we should include the entity if based on their location relative to centerChunk in world
                        this.character = new Player(Integer.parseInt(pieces[1]), Integer.parseInt(pieces[2]), world);
                        world.addEntity(character);
                        world.setCharacter(character);
                    }
                }
            }
        }
    }
}
