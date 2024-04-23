package db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cookbook.RecipeRating;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RatingFileDataHandler extends FileDataHandler {
    private final String fileName = "ratings.json";
    private final Path filePath;

    public RatingFileDataHandler() {
        this.filePath = Path.of(directoryName, fileName);

        if (!fileExists(filePath)) {
            createFile(filePath);
        }
    }

    public List<RecipeRating> getAllRatingsFromDB() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());

        try {
            byte[] usersJsonData = Files.readAllBytes(filePath);

            if (usersJsonData.length == 0) {
                return new ArrayList<>();
            }

            TypeReference<List<RecipeRating>> typeReference = new TypeReference<>() {};

            return objectMapper.readValue(usersJsonData, typeReference);
        } catch (IOException e) {
            System.err.println("Error while reading ratings from file: " + filePath);
        }
        return new ArrayList<>();
    }

    private void saveRatingsToDB(List<RecipeRating> ratings) {
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
        List<RecipeRating> ratings = getAllRatingsFromDB();
        ratings.add(rating);

        saveRatingsToDB(ratings);
    }
}