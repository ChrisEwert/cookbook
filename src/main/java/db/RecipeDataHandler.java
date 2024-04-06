package db;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import recipe.Recipe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RecipeDataHandler implements DataHandler {
    private final String fileName = "recipes.json";
    private final Path filePath;
    private final List<Recipe> recipes;

    public RecipeDataHandler() {
        this.filePath = Path.of(directoryName, fileName);
        if (!fileExists(filePath)) {
            createFile(filePath);
        }
        recipes = loadRecipesFromFile();
    }

    public void addRecipeToDB(Recipe recipe) {
        recipes.add(recipe);
        saveRecipesToFile();
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    private List<Recipe> loadRecipesFromFile() {
        List<Recipe> recipes = new ArrayList<>();
        try {
            String fileContent = Files.readString(filePath);
            if (!fileContent.isEmpty()) {
                JSONArray jsonArray = new JSONArray(fileContent);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonRecipe = jsonArray.getJSONObject(i);
                    long id = jsonRecipe.getLong("id");
                    String name = jsonRecipe.getString("name");
                    String author = jsonRecipe.getString("author");

                    Recipe recipe = new Recipe(id, name, author);
                    recipes.add(recipe);
                }
            }
        } catch (JSONException e) {
            System.err.println("Invalid JSON format in the file");
        } catch (IOException e) {
            System.err.println("Error reading the file");
        }
        return recipes;
    }

    private void saveRecipesToFile() {
        JSONArray jsonRecipes = new JSONArray();
        for (Recipe recipe : recipes) {
            JSONObject jsonRecipe = new JSONObject();
            jsonRecipe.put("id", recipe.id());
            jsonRecipe.put("name", recipe.name());
            jsonRecipe.put("author", recipe.author());
            jsonRecipes.put(jsonRecipe);
        }

        try {
            Files.writeString(filePath, jsonRecipes.toString());
        } catch (IOException e) {
            System.err.println("Error writing to the file");
        }
    }
}