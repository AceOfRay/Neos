package Tools;

import WorldModel.Chunk;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChunkLoader {
    private List<Chunk> chunks = new ArrayList<>();

    public ChunkLoader() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("GameResources/Files/world.txt");
            if (inputStream != null) {
                loadChunksFromInputStream(inputStream);
            } else {
                System.out.println("Could not find the desired file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading chunks from file: " + e.getMessage());
        }
    }

    private void loadChunksFromInputStream(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("#") && !line.contains("Entities:") && !line.contains("Chunks:")) {
                    if (line.startsWith("c")) {
                        String[] pieces = line.split(" ");
                        Chunk curChunk = new Chunk(Integer.parseInt(pieces[1]), Integer.parseInt(pieces[2]));
                        this.chunks.add(curChunk);
                        if (pieces.length > 3 && pieces[3] != null) {
                            curChunk.setContainsPlayer();
                        }
                    }
                }
            }
        }
        sortChunks();
    }

    private void sortChunks() {
        Comparator<Chunk> chunkYComparator = Comparator.comparingDouble(chunk -> chunk.getLocation().getY());
        Comparator<Chunk> chunkXComparator = Comparator.comparingDouble(chunk -> chunk.getLocation().getX());
        Comparator<Chunk> chunkComparatorFinal = chunkYComparator.thenComparing(chunkXComparator);
        Collections.sort(this.chunks, chunkComparatorFinal);
    }

    public List<Chunk> getChunks() {
        return this.chunks;
    }
}
