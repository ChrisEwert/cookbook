package db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cookbook.Recipe;
import cookbook.RecipeRating;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class RatingFileDataHandler extends FileDataHandler {
    private final String fileName = "ratings.json";
    private final Path filePath;

    public RatingFileDataHandler() {
        this.filePath = Path.of(directoryName, fileName);

        createFile(filePath);
    }

    public Map<String, RecipeRating> getAllRatingsFromDB() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());

        try {
            byte[] usersJsonData = Files.readAllBytes(filePath);

            if (usersJsonData.length == 0) {
                return new HashMap<>();
            }

            TypeReference<Map<String, RecipeRating>> typeReference = new TypeReference<>() {};

            return objectMapper.readValue(usersJsonData, typeReference);
        } catch (IOException e) {
            System.err.println("Error while reading ratings from file: " + filePath);
        }
        return new HashMap<>();
    }

    public RecipeRating getRatingById(String id) {
        Map<String, RecipeRating> ratings = getAllRatingsFromDB();

        return ratings.get(id);
    }

    private void saveAllRatingsToDB(Map<String, RecipeRating> ratings) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            File file = new File(String.valueOf(filePath));

            objectMapper.writeValue(file, ratings);
        } catch (IOException e) {
            System.err.println("Error while saving recipes to file: " + filePath);
        }
    }

    public void addRatingToDB(RecipeRating rating) {
        Map<String, RecipeRating> ratings = getAllRatingsFromDB();

        ratings.put(rating.id(), rating);

        saveAllRatingsToDB(ratings);
    }

    public void updateRatingInDB(String id, RecipeRating newRating) {
        Map<String, RecipeRating> ratings = getAllRatingsFromDB();

        if (!ratings.containsKey(id)) {
            System.err.println("Error: Rating with ID " + id + " not found.");
            return;
        }

        ratings.put(id, newRating);

        saveAllRatingsToDB(ratings);
    }
}