package cookbook;

import db.RatingDataHandler;
import db.RecipeDataHandler;

import java.util.List;
import java.util.UUID;

public class RecipeRepository {
    private final RecipeDataHandler recipeDataHandler = new RecipeDataHandler();
    private final RatingDataHandler ratingDataHandler = new RatingDataHandler();

    public static String getRandomId() {
        return UUID.randomUUID().toString();
    }

    public void saveRecipe(Recipe recipe) {
        recipeDataHandler.saveRecipeToDB(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeDataHandler.readRecipesFromDB();
    }

    public Recipe getRecipeById(String id) {
        return recipeDataHandler.getRecipeById(id);
    }

    public void addRating(RecipeRating rating) {
        ratingDataHandler.addRatingToDB(rating);
    }

    public void setStars(Recipe recipe, float stars, int ratingsCount) {
        recipeDataHandler.setRating(recipe.id(), stars, ratingsCount);
    }

    public List<RecipeRating> getAllRatings() {
        return ratingDataHandler.getAllRatingsFromDB();
    }
}
