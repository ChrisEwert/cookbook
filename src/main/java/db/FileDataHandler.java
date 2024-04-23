package db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDataHandler {
    String directoryName = "db";

    boolean fileExists(Path filePath) {
        return Files.exists(filePath);
    }

    void createFile(Path filePath) {
        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            System.err.println("Could not create file");
        }
    }
}