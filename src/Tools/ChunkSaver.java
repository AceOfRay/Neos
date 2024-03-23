package Tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import WorldModel.Chunk;

public class ChunkSaver {
    private final String filePath = "GameResources/Files/world.txt";

    public ChunkSaver() {
        try {
            File tempFile = File.createTempFile("tempfile", ".txt");

            FileReader fileReader = new FileReader(filePath);
            FileWriter fileWriter = new FileWriter(tempFile);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // here is where the checks will be added

                // Write the modified line to the temporary file
                //bufferedWriter.write(modifiedLine);
                //bufferedWriter.newLine(); // Add newline after each line
            }

            // Close the resources
            bufferedReader.close();
            bufferedWriter.close();

            // Replace the original file with the temporary file
            tempFile.renameTo(new File(filePath));

            System.out.println("File modified successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
