package cookbook;

import db.RatingFileDataHandler;
import db.RecipeFileDataHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RecipeFileRepository implements RecipeRepository {
    private final RecipeFileDataHandler recipeFileDataHandler;
    private final RatingFileDataHandler ratingFileDataHandler;

    public RecipeFileRepository() {
        this.ratingFileDataHandler = new RatingFileDataHandler();
        this.recipeFileDataHandler = new RecipeFileDataHandler();
    }

    public RecipeFileRepository(RecipeFileDataHandler recipeFileDataHandler) {
        this.recipeFileDataHandler = recipeFileDataHandler;
        this.ratingFileDataHandler = new RatingFileDataHandler();
    }

    public RecipeFileRepository(RatingFileDataHandler ratingFileDataHandler) {
        this.recipeFileDataHandler = new RecipeFileDataHandler();
        this.ratingFileDataHandler = ratingFileDataHandler;
    }

    public RecipeFileRepository(RecipeFileDataHandler recipeFileDataHandler, RatingFileDataHandler ratingFileDataHandler) {
        this.recipeFileDataHandler = recipeFileDataHandler;
        this.ratingFileDataHandler = ratingFileDataHandler;
    }

    public Map<String, Recipe> getAllRecipes() {
        return recipeFileDataHandler.getAllRecipesFromDB();
    }

    public Recipe getRecipeById(String recipeId) {
        return recipeFileDataHandler.getRecipeById(recipeId);
    }

    public void addRecipe(String recipeId, Recipe recipe) {
        recipeFileDataHandler.addRecipeToDB(recipeId, recipe);
    }

    public void updateRecipe(String recipeId, Recipe updatedRecipe) {
        recipeFileDataHandler.updateRecipeInDB(recipeId, updatedRecipe);
    }

    public void deleteRecipe(String recipeId) {
        recipeFileDataHandler.deleteRecipeFromDB(recipeId);
    }


    public Map<String, RecipeRating> getAllRatings() {
        return ratingFileDataHandler.getAllRatingsFromDB();
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

    public RecipeRating getRatingById(String ratingId) {
        return ratingFileDataHandler.getRatingById(ratingId);
    }

    public RecipeRating getRatingOfRecipeByAuthor(String recipeId, String author) {
        List<RecipeRating> ratings = getRatingsOfRecipe(recipeId);

        for (RecipeRating rating : ratings) {
            if (Objects.equals(rating.author(), author)) {
                return rating;
            }
        }

        return null;
    }

    public void addRating(String ratingId, RecipeRating rating) {
        ratingFileDataHandler.addRatingToDB(ratingId, rating);
    }

    public void updateExistingRatingOfRecipe(String ratingId, RecipeRating newRating) {
        ratingFileDataHandler.updateRatingInDB(ratingId, newRating);
    }

    public void deleteRating(String ratingId) {
        ratingFileDataHandler.deleteRatingFromDB(ratingId);
    }

    public void deleteAllRatingsOfRecipe(String recipeId) {
        List<RecipeRating> ratings = getRatingsOfRecipe(recipeId);

        for (RecipeRating rating : ratings) {
            ratingFileDataHandler.deleteRatingFromDB(rating.id());
        }
    }
}
