package services;

import cookbook.Recipe;
import cookbook.RecipeRating;
import cookbook.RecipeRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeRepositoryMock implements RecipeRepository {
    Map<String, Recipe> recipes = new HashMap<>();
    Map<String, RecipeRating> ratings = new HashMap<>();

    @Override
    public Map<String, Recipe> getAllRecipes() {
        return recipes;
    }

    @Override
    public Recipe getRecipeById(String id) {
        return recipes.get(id);
    }

    @Override
    public void addRecipe(String recipeId, Recipe recipe) {
        recipes.put(recipeId, recipe);
    }

    @Override
    public void updateRecipe(String recipeId, Recipe newRecipe) {
        recipes.put(recipeId, newRecipe);
    }

    @Override
    public void deleteRecipe(String recipeId) {
        recipes.remove(recipeId);
    }


    @Override
    public Map<String, RecipeRating> getAllRatings() {
        return ratings;
    }

    @Override
    public RecipeRating getRatingById(String ratingId) {
        return ratings.get(ratingId);
    }

    @Override
    public List<RecipeRating> getRatingsOfRecipe(String recipeId) {
        List<RecipeRating> ratingList = new ArrayList<>();

        for (RecipeRating rating : ratings.values()) {
            if (rating.id().equals(recipeId)) {
                ratingList.add(rating);
            }
        }

        return ratingList;
    }

    @Override
    public RecipeRating getRatingOfRecipeByAuthor(String recipeId, String author) {
        List<RecipeRating> ratings = getRatingsOfRecipe(recipeId);

        for (RecipeRating rating : ratings) {
            if (rating.author().equals(author)) {
                return rating;
            }
        }

        return null;
    }

    @Override
    public void addRating(String ratingId, RecipeRating rating) {
        ratings.put(ratingId, rating);
    }

    @Override
    public void updateExistingRatingOfRecipe(String ratingId, RecipeRating updatedRating) {
        ratings.put(ratingId, updatedRating);
    }

    @Override
    public void deleteRating(String ratingId) {
        ratings.remove(ratingId);
    }

    @Override
    public void deleteAllRatingsOfRecipe(String recipeId) {
        List<RecipeRating> allRatings = getRatingsOfRecipe(recipeId);

        for (RecipeRating rating : allRatings) {
            ratings.remove(rating.id());
        }
    }
}
