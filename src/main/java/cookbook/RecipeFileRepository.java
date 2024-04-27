package cookbook;

import db.RatingFileDataHandler;
import db.RecipeFileDataHandler;

import java.util.Map;

public class RecipeFileRepository implements RecipeRepository {
    private final RecipeFileDataHandler recipeDataHandler = new RecipeFileDataHandler();
    private final RatingFileDataHandler ratingDataHandler = new RatingFileDataHandler();

    public Map<String, Recipe> getAllRecipes() {
        return recipeDataHandler.getAllRecipesFromDB();
    }

    public Recipe getRecipeById(String id) {
        return recipeDataHandler.getRecipeById(id);
    }

    public void addRecipe(Recipe recipe) {
        recipeDataHandler.addRecipeToDB(recipe);
    }

    public void updateRecipe(String id, Recipe newRecipe) {
        recipeDataHandler.updateRecipeInDB(id, newRecipe);
    }

    public Map<String, RecipeRating> getAllRatings() {
        return ratingDataHandler.getAllRatingsFromDB();
    }

    public RecipeRating getRatingById(String id) {
        return ratingDataHandler.getRatingById(id);
    }

    public void addRating(RecipeRating rating) {
        ratingDataHandler.addRatingToDB(rating);
    }

    public void updateRatingOfRecipe(Recipe recipe, float stars, int ratingCount) {
        Recipe newRecipe = recipe.changeRating(stars, ratingCount);

        recipeDataHandler.updateRecipeInDB(recipe.id(), newRecipe);
    }
}
