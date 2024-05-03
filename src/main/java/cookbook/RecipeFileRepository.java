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

    public Recipe getRecipeById(String id) {
        return recipeFileDataHandler.getRecipeById(id);
    }

    public void addRecipe(Recipe recipe) {
        recipeFileDataHandler.addRecipeToDB(recipe);
    }

    public void updateRatingStarsOfRecipe(Recipe recipe, float stars, int ratingCount) {
        Recipe newRecipe = recipe.changeRating(stars, ratingCount);

        recipeFileDataHandler.updateRecipeInDB(recipe.id(), newRecipe);
    }

    public void updateRecipe(String id, Recipe newRecipe) {
        recipeFileDataHandler.updateRecipeInDB(id, newRecipe);
    }

    public void deleteRecipe(String recipeId) {
        recipeFileDataHandler.deleteRecipeFromDB(recipeId);
    }


    public Map<String, RecipeRating> getAllRatings() {
        return ratingFileDataHandler.getAllRatingsFromDB();
    }

    public RecipeRating getRatingById(String id) {
        return ratingFileDataHandler.getRatingById(id);
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

    // TODO: You should not add the recipeId in the service, but here
    public void addRating(RecipeRating rating) {
        ratingFileDataHandler.addRatingToDB(rating);
    }

    public void updateExistingRatingOfRecipe(RecipeRating newRating) {
        ratingFileDataHandler.updateRatingInDB(newRating);
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
