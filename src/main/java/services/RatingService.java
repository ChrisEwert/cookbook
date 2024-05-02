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

    public Map<String, RecipeRating> getAllRatings() {
        return recipeRepository.getAllRatings();
    }

    public List<RecipeRating> getRatingsByRecipeId(String recipeId) {
        return recipeRepository.getRatingsOfRecipe(recipeId);
    }

    public boolean hasRated(String username, String id) {
        List<RecipeRating> ratings = getRatingsByRecipeId(id);

        for (RecipeRating rating : ratings) {
            if (Objects.equals(rating.author(), username)) {
                return true;
            }
        }

        return false;
    }

    public RecipeRating getRatingByName(String recipeId, String name) {
        return recipeRepository.getRatingByName(recipeId, name);
    }

    public void addRating(String id, String author, int rating, String title, String comment) {
        RecipeRating newRating = new RecipeRating(id, author, rating, title, comment);

        recipeRepository.addRating(newRating);

        Recipe updatedRecipe = recipeRepository.getRecipeById(id);

        updateStarsOfRecipe(updatedRecipe);
    }

    public void updateStarsOfRecipe(Recipe recipe) {
        List<RecipeRating> ratings = getRatingsByRecipeId(recipe.id());

        float stars = 0;
        for (RecipeRating rating : ratings) {
            stars += rating.stars();
        }
        stars /= ratings.size();

        recipeRepository.updateRatingOfRecipe(recipe, stars, ratings.size());
    }

    public void deleteRatingsOfRecipe(String recipeId) {
        recipeRepository.deleteRatingsOfRecipe(recipeId);
    }
}
