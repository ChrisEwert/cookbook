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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDataHandler implements DataHandler {
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
            List<User> listOfUsers = readUsersFromDB();
            listOfUsers.add(user);
            objectMapper.writeValue(new File(String.valueOf(filePath)), listOfUsers);
        } catch (IOException e) {
            System.err.println("Error while saving username: " + user.username());
        }
    }

    public List<User> readUsersFromDB() {
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

    public Set<String> getBookmarkIds(String username) {
        Set<String> bookmarkedIds = new HashSet<>();
        List<User> userList = readUsersFromDB();
        for (User user : userList) {
            if (user.username().equals(username)) {
                bookmarkedIds.addAll(user.bookmarkedRecipeIds());
                break;
            }
        }
        return bookmarkedIds;
    }

    public void bookmarkRecipeById(String username, String recipeId) {
        try {
            List<User> userList = readUsersFromDB();
            for (User userObj : userList) {
                if (userObj.username().equals(username)) {
                    userObj.bookmarkedRecipeIds().add(recipeId);
                    break;
                }
            }
            writeUsersToDB(userList);
        } catch (IOException e) {
            System.err.println("Error while bookmarking recipe for username: " + username);
        }
    }

    private void writeUsersToDB(List<User> userList) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File(String.valueOf(filePath)), userList);
    }
}