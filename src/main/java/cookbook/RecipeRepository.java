package cookbook;

import java.util.List;
import java.util.Map;

public interface RecipeRepository {

    Map<String, Recipe> getAllRecipes();

    Recipe getRecipeById(String recipeId);

    void addRecipe(String recipeId, Recipe recipe);

    void updateRecipe(String recipeId, Recipe updatedRecipe);

    void deleteRecipe(String recipeId);

    Map<String, RecipeRating> getAllRatings();

    List<RecipeRating> getRatingsOfRecipe(String recipeId);

    RecipeRating getRatingById(String ratingId);

    RecipeRating getRatingOfRecipeByAuthor(String recipeId, String author);

    void addRating(String ratingId, RecipeRating rating);

    void updateExistingRatingOfRecipe(String ratingId, RecipeRating updatedRating);

    void deleteRating(String ratingId);

    void deleteAllRatingsOfRecipe(String recipeId);
}
