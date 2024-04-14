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