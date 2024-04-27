package cookbook;

import db.RatingFileDataHandler;
import db.RecipeFileDataHandler;

import java.util.List;
import java.util.Map;

public class RecipeFileRepository implements RecipeRepository {
    private final RecipeFileDataHandler recipeDataHandler = new RecipeFileDataHandler();
    private final RatingFileDataHandler ratingDataHandler = new RatingFileDataHandler();

    public Map<String, Recipe> getAllRecipes() {
        return recipeDataHandler.readAllRecipesFromDB();
    }

    public Recipe getRecipeById(String id) {
        return recipeDataHandler.getRecipeById(id);
    }

    public void saveRecipe(Recipe recipe) {
        recipeDataHandler.saveRecipeToDB(recipe);
    }

    public List<RecipeRating> getAllRatings() {
        return ratingDataHandler.getAllRatingsFromDB();
    }

    public void addRating(RecipeRating rating) {
        ratingDataHandler.addRatingToDB(rating);
    }

    public void setStars(Recipe recipe, float stars, int ratingsCount) {
        recipeDataHandler.setRating(recipe.id(), stars, ratingsCount);
    }
}
