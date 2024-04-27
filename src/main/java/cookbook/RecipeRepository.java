package cookbook;

import java.util.Map;

public interface RecipeRepository {

    Map<String, Recipe> getAllRecipes();

    Recipe getRecipeById(String id);

    void addRecipe(Recipe recipe);

    Map<String, RecipeRating> getAllRatings();

    void addRating(RecipeRating rating);

    void updateRatingOfRecipe(Recipe recipe, float stars, int ratingsCount);
}
