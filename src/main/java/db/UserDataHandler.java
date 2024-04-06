package db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class UserDataHandler {
    private final String directoryName = "db";
    private final String fileName = "users";
    private final Path filePath;
    private final List<String> userList;

    public UserDataHandler() {
        this.filePath = Path.of(directoryName, fileName);
        if (!fileExists(filePath)) {
            createFile(filePath);
        }
        userList = readUserListFromDB(filePath);
    }

    private boolean fileExists(Path filePath) {
        return Files.exists(filePath);
    }

    private void createFile(Path filePath) {
        try {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        } catch (IOException e) {
            System.err.println("Could not create file");
        }
    }

    private List<String> readUserListFromDB(Path filePath) {
        List<String> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toString()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.err.println("Could not read file");
        }

        return list;
    }

    public List<String> getUserList() {
        return userList;
    }

    public void saveUserListToDB(List<String> userList) {
        saveListToFile(userList, filePath);
    }

    private void saveListToFile(List<String> list, Path filePath) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath,
                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (String item : list) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Could not save file");
        }
    }
}