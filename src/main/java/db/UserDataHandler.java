package db;

import cookbook.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

public class UserDataHandler implements DataHandler {
//    private final String fileName = "users";
//    private final Path filePath;
//    private final List<String> userList;
//
//    public UserDataHandler() {
//        this.filePath = Path.of(directoryName, fileName);
//        if (!fileExists(filePath)) {
//            createFile(filePath);
//        }
//        userList = readUserListFromDB(filePath);
//    }
//
//    private List<String> readUserListFromDB(Path filePath) {
//        List<String> list = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toString()))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                list.add(line);
//            }
//        } catch (IOException e) {
//            System.err.println("Could not read file");
//        }
//
//        return list;
//    }
//
//    public List<String> getUserList() {
//        return userList;
//    }
//
//    public void saveUserListToDB(List<String> userList) {
//        saveListToFile(userList, filePath);
//    }
//
//    private void saveListToFile(List<String> list, Path filePath) {
//        try (BufferedWriter writer = Files.newBufferedWriter(filePath,
//                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
//            for (String item : list) {
//                writer.write(item);
//                writer.newLine();
//            }
//        } catch (IOException e) {
//            System.err.println("Could not save file");
//        }
//    }

    private final String fileName = "users.json";
    private final Path filePath;

    public UserDataHandler() {
        this.filePath = Path.of(directoryName, fileName);
        if (!fileExists(filePath)) {
            createFile(filePath);
        }
    }

    public void saveUserToDB(User user) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            List<User> listOfUsers = readUsersFromFile();
            listOfUsers.add(user);
            objectMapper.writeValue(new File(String.valueOf(filePath)), listOfUsers);
        } catch (IOException e) {
            System.err.println("Error while saving user: " + user.username());
        }
    }

    public List<User> readUsersFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            byte[] usersJsonData = Files.readAllBytes(filePath);
            if (usersJsonData.length == 0) {
                return new ArrayList<>();
            }

            TypeReference<List<User>> typeReference = new TypeReference<>() {};
            return objectMapper.readValue(usersJsonData, typeReference);
        } catch (IOException e) {
            System.err.println("Error while reading users from file: " + filePath);
        }
        return new ArrayList<>();
    }
}