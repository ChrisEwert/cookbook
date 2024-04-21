package cookbook;

import db.RecipeDataHandler;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class RecipeRepository {
    private final RecipeDataHandler recipeDataHandler = new RecipeDataHandler();
    private final static AtomicLong ID_COUNTER = new AtomicLong();

    public static long getNextId() {
        return ID_COUNTER.incrementAndGet();
    }

    public void saveRecipe(Recipe recipe) {
        recipeDataHandler.saveRecipeToDB(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeDataHandler.readRecipesFromDB();
    }

    public Recipe getRecipeById(long id) {
        return recipeDataHandler.getRecipeById(id);
    }
}
