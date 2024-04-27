package cookbook;

import java.util.List;
import java.util.Map;

public interface RecipeRepository {

    void saveRecipe(Recipe recipe);

    Map<String, Recipe> getAllRecipes();

    Recipe getRecipeById(String id);

    void addRating(RecipeRating rating);

    void setStars(Recipe recipe, float stars, int ratingsCount);

    List<RecipeRating> getAllRatings();
}
