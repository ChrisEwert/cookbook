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
import java.util.ArrayList;
import java.util.List;

public class RecipeDataHandler implements DataHandler {
    private final String fileName = "recipes.json";
    private final Path filePath;

    public RecipeDataHandler() {
        this.filePath = Path.of(directoryName, fileName);
        if (!fileExists(filePath)) {
            createFile(filePath);
        }
    }

    public List<Recipe> readRecipesFromDB() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            byte[] usersJsonData = Files.readAllBytes(filePath);
            if (usersJsonData.length == 0) {
                return new ArrayList<>();
            }

            objectMapper.registerModule(new JavaTimeModule());
            TypeReference<List<Recipe>> typeReference = new TypeReference<>() {
            };
            return objectMapper.readValue(usersJsonData, typeReference);
        } catch (IOException e) {
            System.err.println("Error while reading recipes from file: " + filePath);
        }
        return new ArrayList<>();
    }

    public void saveRecipeToDB(Recipe recipe) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            List<Recipe> listOfRecipes = readRecipesFromDB();
            listOfRecipes.add(recipe);
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.writeValue(new File(String.valueOf(filePath)), listOfRecipes);
        } catch (IOException e) {
            System.err.println("Error while saving recipe: " + recipe.name());
        }
    }

    public Recipe getRecipeById(long id) {
        List<Recipe> listOfRecipes = readRecipesFromDB();
        Recipe recipe = new Recipe();
        for (Recipe r : listOfRecipes) {
            if (r.id() == id) {
                recipe = r;
            }
        }
        return recipe;
    }
}