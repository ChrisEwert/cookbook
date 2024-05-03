package db;

import cookbook.CookbookUser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.Map;

public class UserFileDataHandler extends FileDataHandler {
    private final String fileName = "users.json";
    private final Path filePath;

    public UserFileDataHandler() {
        this.filePath = Path.of(directoryName, fileName);

        createFile(filePath);
    }

    public Map<String, CookbookUser> getAllUsersFromDB() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            byte[] usersJsonData = Files.readAllBytes(filePath);

            if (usersJsonData.length == 0) {
                return new HashMap<>();
            }

            TypeReference<Map<String, CookbookUser>> typeReference = new TypeReference<>() {};

            return objectMapper.readValue(usersJsonData, typeReference);
        } catch (IOException e) {
            System.err.println("Error while reading users from file: " + filePath);
        }

        return new HashMap<>();
    }

    public CookbookUser getUserByUsername(String username) {
        Map<String, CookbookUser> users = getAllUsersFromDB();

        return users.get(username);
    }

    public void saveAllUsersToDB(Map<String, CookbookUser> userList) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            File file = new File(String.valueOf(filePath));

            objectMapper.writeValue(file, userList);
        } catch (IOException e) {
            System.err.println("Error while bookmarking recipe for username list");
        }
    }

    public void saveUserToDB(CookbookUser user) {
        Map<String, CookbookUser> listOfUsers = getAllUsersFromDB();

        listOfUsers.put(user.username(), user);

        saveAllUsersToDB(listOfUsers);
    }

    public void updateUserInDB(String username, CookbookUser user) {
        Map<String, CookbookUser> users = getAllUsersFromDB();

        if (!users.containsKey(username)) {
            System.err.println("Error: User " + username + " not found.");
            return;
        }

        users.put(username, user); // TODO: Change to replace?

        saveAllUsersToDB(users);
    }
}