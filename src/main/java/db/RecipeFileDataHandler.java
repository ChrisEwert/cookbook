package db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cookbook.Recipe;
import cookbook.RecipeFileRepository;
import cookbook.RecipeRating;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class RecipeFileDataHandler extends FileDataHandler {
    private final String fileName = "recipes.json";
    private final Path filePath;

    public RecipeFileDataHandler() {
        this.filePath = Path.of(directoryName, fileName);

        createFile(filePath);
    }

    public Map<String, Recipe> getAllRecipesFromDB() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());

        try {
            byte[] usersJsonData = Files.readAllBytes(filePath);

            if (usersJsonData.length == 0) {
                return new HashMap<>();
            }

            TypeReference<Map<String, Recipe>> typeReference = new TypeReference<>() {};

            return objectMapper.readValue(usersJsonData, typeReference);
        } catch (IOException e) {
            System.err.println("Error while reading recipes from file: " + filePath);
        }
        return new HashMap<>();
    }

    public Recipe getRecipeById(String id) {
        Map<String, Recipe> recipes = getAllRecipesFromDB();

        return recipes.get(id);
    }

    public void saveAllRecipesToDB(Map<String, Recipe> recipes) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());

        try {
            File file = new File(String.valueOf(filePath));

            objectMapper.writeValue(file, recipes);
        } catch (IOException e) {
            System.err.println("Error while saving recipes to file: " + filePath);
        }
    }

    public void addRecipeToDB(String id, Recipe recipe) {
        Map<String, Recipe> recipes = getAllRecipesFromDB();

        recipes.put(id, recipe);

        saveAllRecipesToDB(recipes);
    }

    public void updateRecipeInDB(String id, Recipe newRecipe) {
        Map<String, Recipe> recipes = getAllRecipesFromDB();

        if (!recipes.containsKey(id)) {
            System.err.println("Error: Recipe with ID " + id + " not found.");
            return;
        }

        recipes.put(id, newRecipe);

        saveAllRecipesToDB(recipes);
    }

    public void deleteRecipeFromDB(String id) {
        Map<String, Recipe> recipes = getAllRecipesFromDB();

        if (!recipes.containsKey(id)) {
            System.err.println("Error: Recipe with ID " + id + " not found.");
        }

        recipes.remove(id);
        saveAllRecipesToDB(recipes);
    }
}