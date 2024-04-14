package cookbook;

import db.RecipeDataHandler;

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
}
