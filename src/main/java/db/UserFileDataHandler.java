package db;

import cookbook.CookbookUser;

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

public class UserFileDataHandler extends FileDataHandler {
    private final String fileName = "users.json";
    private final Path filePath;

    public UserFileDataHandler() {
        this.filePath = Path.of(directoryName, fileName);

        createFile(filePath);
    }

    public void saveUserToDB(CookbookUser user) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            List<CookbookUser> listOfUsers = readUsersFromDB();
            listOfUsers.add(user);
            objectMapper.writeValue(new File(String.valueOf(filePath)), listOfUsers);
        } catch (IOException e) {
            System.err.println("Error while saving username: " + user.username());
        }
    }

    public void writeUsersToDB(List<CookbookUser> userList) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new File(String.valueOf(filePath)), userList);
        } catch (IOException e) {
            System.err.println("Error while bookmarking recipe for username list");
        }
    }

    public List<CookbookUser> readUsersFromDB() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            byte[] usersJsonData = Files.readAllBytes(filePath);
            if (usersJsonData.length == 0) {
                return new ArrayList<>();
            }

            TypeReference<List<CookbookUser>> typeReference = new TypeReference<>() {};
            return objectMapper.readValue(usersJsonData, typeReference);
        } catch (IOException e) {
            System.err.println("Error while reading users from file: " + filePath);
        }
        return new ArrayList<>();
    }

    public Set<String> getBookmarkIds(String username) {
        Set<String> bookmarkedIds = new HashSet<>();
        List<CookbookUser> userList = readUsersFromDB();
        for (CookbookUser user : userList) {
            if (user.username().equals(username)) {
                bookmarkedIds.addAll(user.bookmarkedRecipeIds());
                break;
            }
        }
        return bookmarkedIds;
    }

    public void bookmarkRecipeById(String username, String recipeId) {
           List<CookbookUser> userList = readUsersFromDB();
        for (CookbookUser UserObj : userList) {
            if (UserObj.username().equals(username)) {
                UserObj.bookmarkedRecipeIds().add(recipeId);
                break;
            }
        }
        writeUsersToDB(userList);
    }
}