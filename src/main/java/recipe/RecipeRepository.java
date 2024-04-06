package recipe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class RecipeRepository {
    private final static AtomicLong ID_COUNTER = new AtomicLong();
    private final Map<Long, Recipe> recipes = new HashMap<>();

    public static long getNextId() {
        return ID_COUNTER.incrementAndGet();
    }

    public Recipe getRecipe(long id) {
        return recipes.get(id);
    }

    public void createNewRecipe(Recipe recipe) {
        recipes.put(recipe.id(), recipe);
    }

    public void updateRecipe(Recipe recipe) {
        if (recipes.containsKey(recipe.id())) {
            recipes.put(recipe.id(), recipe);
        } else {
            createNewRecipe(recipe);
        }
    }
}
