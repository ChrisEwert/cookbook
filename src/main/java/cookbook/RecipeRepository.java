package cookbook;

import java.util.List;
import java.util.Map;

public interface RecipeRepository {

    Map<String, Recipe> getAllRecipes();

    Recipe getRecipeById(String id);

    void addRecipe(Recipe recipe);

    void updateRecipe(String id, Recipe newRecipe);

    void deleteRecipe(String recipeId);

    Map<String, RecipeRating> getAllRatings();

    List<RecipeRating> getRatingsOfRecipe(String recipeId);

    RecipeRating getRatingByName(String recipeId, String name);

    RecipeRating getRatingById(String id);

    void addRating(RecipeRating rating);

    void updateRatingStarsOfRecipe(Recipe recipe, float stars, int ratingsCount);

    void deleteAllRatingsOfRecipe(String recipeId);

    void updateExistingRatingOfRecipe(RecipeRating updatedRating);

    void deleteRating(String ratingId);
}
