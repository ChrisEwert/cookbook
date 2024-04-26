package db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDataHandler {
    String directoryName = "db";
    void createFile(Path filePath) {
        if (Files.exists(filePath)) {
            return;
        }

        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            System.err.println("Could not create file");
        }
    }
}