package cookbook;

import db.RecipeDataHandler;

import java.util.List;
import java.util.UUID;

public class RecipeRepository {
    private final RecipeDataHandler recipeDataHandler = new RecipeDataHandler();

    public static String getRandomId() {
        return UUID.randomUUID().toString();
    }

    public void saveRecipe(Recipe recipe) {
        recipeDataHandler.saveRecipeToDB(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeDataHandler.readRecipesFromDB();
    }

    public Recipe getRecipeById(String id) {
        return recipeDataHandler.getRecipeById(id);
    }

    public void setRating(Recipe recipe, float rating, int ratingCount) {
        recipeDataHandler.setRating(recipe.id(), rating, ratingCount);
    }
}
