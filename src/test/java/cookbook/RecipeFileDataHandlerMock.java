package cookbook;

import db.RecipeFileDataHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeFileDataHandlerMock extends RecipeFileDataHandler {
    private Map<String, Recipe> recipes;

    public RecipeFileDataHandlerMock() {
        recipes = new HashMap<>();
        Recipe recipe1 = new Recipe("Recipe ID 1", "Recipe 1", "John", LocalDate.now(), List.of(), List.of(), List.of(), 1, 1f, 1);
        Recipe recipe2 = new Recipe("Recipe ID 2", "Recipe 2", "Jane", LocalDate.now(), List.of(), List.of(), List.of(), 2, 2f, 2);
        Recipe recipe3 = new Recipe("Recipe ID 3", "Recipe 3", "Jane", LocalDate.now(), List.of(), List.of(), List.of(), 3, 3f, 3);
        recipes.put(recipe1.id(), recipe1);
        recipes.put(recipe2.id(), recipe2);
        recipes.put(recipe3.id(), recipe3);
    }

    @Override
    public Map<String, Recipe> getAllRecipesFromDB() {
        return recipes;
    }

    @Override
    public Recipe getRecipeById(String id) {
        return recipes.get(id);
    }

    @Override
    public void saveAllRecipesToDB(Map<String, Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public void addRecipeToDB(String id, Recipe recipe) {
        recipes.put(id, recipe);
    }

    @Override
    public void updateRecipeInDB(String id, Recipe newRecipe) {
        recipes.put(id, newRecipe);
    }

    @Override
    public void deleteRecipeFromDB(String id) {
        recipes.remove(id);
    }
}
