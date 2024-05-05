package services;

import cookbook.Recipe;
import cookbook.RecipeRating;
import cookbook.RecipeRepository;

import java.util.*;

public class RatingService {
    private final RecipeRepository recipeRepository;

    public RatingService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeRating> getRatingsByRecipeId(String recipeId) {
        return recipeRepository.getRatingsOfRecipe(recipeId);
    }

    public boolean hasRated(String username, String recipeId) {
        List<RecipeRating> ratings = getRatingsByRecipeId(recipeId);

        for (RecipeRating rating : ratings) {
            if (Objects.equals(rating.author(), username)) {
                return true;
            }
        }

        return false;
    }

    public RecipeRating getRatingByAuthor(String recipeId, String author) {
        return recipeRepository.getRatingOfRecipeByAuthor(recipeId, author);
    }

    public void addRating(String recipeId, String author, int stars, String title, String comment) {
        RecipeRating newRating = new RecipeRating(recipeId, author, stars, title, comment);

        recipeRepository.addRating(newRating.id(), newRating);

        Recipe updatedRecipe = recipeRepository.getRecipeById(recipeId);

        updateStarsOfRecipe(updatedRecipe);
    }

    public void updateExistingRating(String ratingId, String author, int stars, String title, String comment) {
        RecipeRating currentRating = recipeRepository.getRatingById(ratingId);

        RecipeRating updatedRating = currentRating.updateRating(author, stars, title, comment);

        recipeRepository.updateExistingRatingOfRecipe(ratingId, updatedRating);
    }

    public void updateStarsOfRecipe(Recipe recipe) {
        List<RecipeRating> ratings = getRatingsByRecipeId(recipe.id());

        float stars = 0;
        for (RecipeRating rating : ratings) {
            stars += rating.stars();
        }
        stars /= ratings.size();

        Recipe newRecipe = recipe.changeRating(stars, ratings.size());

        recipeRepository.updateRecipe(recipe.id(), newRecipe);
    }

    public void deleteRatingsOfRecipeWithId(String recipeId) {
        recipeRepository.deleteAllRatingsOfRecipe(recipeId);
    }

    public void deleteRatingWithId(String ratingId) {
        recipeRepository.deleteRating(ratingId);
    }
}
