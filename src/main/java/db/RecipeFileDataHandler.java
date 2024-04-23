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
import java.util.Objects;

public class RecipeFileDataHandler extends FileDataHandler {
    private final String fileName = "recipes.json";
    private final Path filePath;

    public RecipeFileDataHandler() {
        this.filePath = Path.of(directoryName, fileName);

        createFile(filePath);
    }

    public List<Recipe> readRecipesFromDB() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());

        try {
            byte[] usersJsonData = Files.readAllBytes(filePath);

            if (usersJsonData.length == 0) {
                return new ArrayList<>();
            }

            TypeReference<List<Recipe>> typeReference = new TypeReference<>() {};

            return objectMapper.readValue(usersJsonData, typeReference);
        } catch (IOException e) {
            System.err.println("Error while reading recipes from file: " + filePath);
        }
        return new ArrayList<>();
    }

    public Recipe getRecipeById(String id) {
        List<Recipe> listOfRecipes = readRecipesFromDB();

        for (Recipe r : listOfRecipes) {
            if (Objects.equals(r.id(), id)) {
                return r;
            }
        }
        return null;
    }

    public void saveRecipeToDB(Recipe recipe) {
        List<Recipe> listOfRecipes = readRecipesFromDB();
        listOfRecipes.add(recipe);

        saveRecipesToDB(listOfRecipes);
    }

    public void updateRecipe(String id, Recipe newRecipe) {
        List<Recipe> listOfRecipes = readRecipesFromDB();

        for (int i = 0; i < listOfRecipes.size(); i++) {
            Recipe recipe = listOfRecipes.get(i);
            if (Objects.equals(recipe.id(), id)) {
                listOfRecipes.set(i, newRecipe);
                saveRecipesToDB(listOfRecipes);
                return;
            }
        }

        System.err.println("Error: Recipe with ID " + id + " not found.");
    }

    private void saveRecipesToDB(List<Recipe> recipes) {
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