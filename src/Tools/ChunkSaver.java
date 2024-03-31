package Tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import WorldModel.Chunk;
import WorldModel.World;

public class ChunkSaver {
    private final String filePath = "src/GameResources/Files/world.txt";

    public ChunkSaver(List<String> indexes, World world) {
        Chunk hasPlayer = world.getChunkWithPlayer();
        String centerPoint = hasPlayer.getChunkIndex().getX() + ", " + hasPlayer.getChunkIndex().getY();
        try {
            File tempFile = File.createTempFile("tempfile", ".txt");

            FileReader fileReader = new FileReader(filePath);
            FileWriter fileWriter = new FileWriter(tempFile);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith("#")) {
                    // here is where the checks will be added
                    String[] pieces = line.split(" ");
                    String curIndex = pieces[1] + ", " + pieces[2];

                    boolean matches = curIndex.equals(centerPoint);
                    boolean inNewChunkList = indexes.contains(curIndex);

                    switch (pieces[0]) {
                        case "np": {
                            if (matches) {
                                bufferedWriter
                                        .write("hp " + pieces[1] + " " + pieces[2] + " " + pieces[3] + " " + pieces[4]);
                            } else if (!inNewChunkList) {
                                bufferedWriter
                                        .write("c " + pieces[1] + " " + pieces[2] + " " + pieces[3] + " " + pieces[4]);
                            } else if (inNewChunkList) {
                                bufferedWriter.write(line);
                            }
                            bufferedWriter.newLine();
                            break;
                        }
                        case "hp": {
                            if (!matches && !inNewChunkList) {
                                bufferedWriter
                                        .write("c " + pieces[1] + " " + pieces[2] + " " + pieces[3] + " " + pieces[4]);
                            } else if (!matches && inNewChunkList) {
                                bufferedWriter
                                        .write("np " + pieces[1] + " " + pieces[2] + " " + pieces[3] + " " + pieces[4]);
                            }
                            bufferedWriter.newLine();
                            break;
                        }
                        case "c": {
                            if (inNewChunkList) {
                                bufferedWriter
                                        .write("np " + pieces[1] + " " + pieces[2] + " " + pieces[3] + " " + pieces[4]);
                            } else {
                                bufferedWriter.write(line);
                            }
                            bufferedWriter.newLine();
                            break;
                        }
                        default: {
                            bufferedWriter.write(line);
                            bufferedWriter.newLine();
                            break;
                        }
                    }
                }
            }
            bufferedReader.close();
            bufferedWriter.close();
            tempFile.renameTo(new File(filePath));
            System.out.println(tempFile);

            System.out.println("File modified successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
