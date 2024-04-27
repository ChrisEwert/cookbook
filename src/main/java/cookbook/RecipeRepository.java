package cookbook;

import java.util.List;
import java.util.Map;

public interface RecipeRepository {

    void saveRecipe(Recipe recipe);

    Map<String, Recipe> getAllRecipes();

    Recipe getRecipeById(String id);

    List<RecipeRating> getAllRatings();

    void addRating(RecipeRating rating);

    void updateRecipeRating(Recipe recipe, float stars, int ratingsCount);
}
