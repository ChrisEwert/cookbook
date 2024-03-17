package recipe;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class CookBook {
    private String name;
    private LocalDate dateOfCreation;
    private final static AtomicLong ID_COUNTER = new AtomicLong();
    private final Map<Long, Recipe> recipes = new HashMap<>();
    private static String user;

    public static long getNextId() {
        return ID_COUNTER.incrementAndGet();
    }

    public void createNewRecipe(Recipe recipe) {
        recipes.put(recipe.id(), recipe);
    }

    public void updateRecipe(Recipe recipe) {
        if (recipes.containsKey(recipe.id())) {
            recipes.put(recipe.id(), Recipe.updatedRecipe(recipe));
        } else {
            createNewRecipe(recipe);
        }
    }

    public static String getUser() {
        return user;
    }
}
