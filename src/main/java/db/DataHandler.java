package db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public interface DataHandler {
    String directoryName = "db";

    default boolean fileExists(Path filePath) {
        return Files.exists(filePath);
    }

    default void createFile(Path filePath) {
        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            System.err.println("Could not create file");
        }
    }
}