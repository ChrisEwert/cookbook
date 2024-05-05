package db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDataHandler {
    final String directoryName = "db";

    public void createFile(Path filePath) {
        Path directoryPath = filePath.getParent();
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
            } catch (IOException e) {
                System.err.println("Could not create directory: " + directoryPath);
                return;
            }
        }

        if (Files.exists(filePath)) {
            return;
        }

        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            System.err.println("Could not create file: " + filePath);
        }
    }
}