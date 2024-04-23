package cookbook;

import java.util.List;

public interface RecipeRepository {

    void saveRecipe(Recipe recipe);

    List<Recipe> getAllRecipes();

    Recipe getRecipeById(String id);

    void addRating(RecipeRating rating);

    void setStars(Recipe recipe, float stars, int ratingsCount);

    List<RecipeRating> getAllRatings();
}
