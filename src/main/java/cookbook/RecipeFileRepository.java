package cookbook;

import db.RatingFileDataHandler;
import db.RecipeFileDataHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    public void updateRatingStarsOfRecipe(Recipe recipe, float stars, int ratingCount) {
        Recipe newRecipe = recipe.changeRating(stars, ratingCount);

        recipeDataHandler.updateRecipeInDB(recipe.id(), newRecipe);
    }

    public void updateRecipe(String id, Recipe newRecipe) {
        recipeDataHandler.updateRecipeInDB(id, newRecipe);
    }

    public void deleteRecipe(String recipeId) {
        recipeDataHandler.deleteRecipeFromDB(recipeId);
    }


    public Map<String, RecipeRating> getAllRatings() {
        return ratingDataHandler.getAllRatingsFromDB();
    }

    public RecipeRating getRatingById(String id) {
        return ratingDataHandler.getRatingById(id);
    }

    public RecipeRating getRatingByName(String recipeId, String name) {
        List<RecipeRating> ratings = getRatingsOfRecipe(recipeId);

        for (RecipeRating rating : ratings) {
            if (Objects.equals(rating.author(), name)) {
                return rating;
            }
        }

        return null;
    }

    public List<RecipeRating> getRatingsOfRecipe(String recipeId) {
        Map<String, RecipeRating> allRatings = getAllRatings();
        List<RecipeRating> ratings = new ArrayList<>();

        for (RecipeRating rating : allRatings.values()) {
            if (Objects.equals(rating.recipeId(), recipeId)) {
                ratings.add(rating);
            }
        }

        return ratings;
    }

    public void addRating(RecipeRating rating) {
        ratingDataHandler.addRatingToDB(rating);
    }

    public void updateExistingRatingOfRecipe(RecipeRating newRating) {
        ratingDataHandler.updateRatingInDB(newRating);
    }

    public void deleteRatingsOfRecipe(String recipeId) {
        List<RecipeRating> ratings = getRatingsOfRecipe(recipeId);

        for (RecipeRating rating : ratings) {
            ratingDataHandler.deleteRatingFromDB(rating.id());
        }
    }
}
