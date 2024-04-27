package db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cookbook.Recipe;

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

    public Map<String, Recipe> readAllRecipesFromDB() {
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
        Map<String, Recipe> recipes = readAllRecipesFromDB();

        return recipes.get(id);
    }

    public void saveRecipeToDB(Recipe recipe) {
        Map<String, Recipe> recipes = readAllRecipesFromDB();
        recipes.put(recipe.id(), recipe);

        saveRecipesToDB(recipes);
    }

    public void updateRecipe(String id, Recipe newRecipe) {
        Map<String, Recipe> recipes = readAllRecipesFromDB();

        if (!recipes.containsKey(id)) {
            System.err.println("Error: Recipe with ID " + id + " not found.");
            return;
        }

        recipes.put(id, newRecipe);
        saveRecipesToDB(recipes);
    }


    private void saveRecipesToDB(Map<String, Recipe> recipes) {
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


    public void setRating(String id, float rating, int ratingCount) {
        Recipe recipe = getRecipeById(id);

        recipe = recipe.changeRating(rating, ratingCount);

        updateRecipe(id, recipe);
    }
}