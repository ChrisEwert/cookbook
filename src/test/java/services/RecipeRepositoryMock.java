package services;

import cookbook.Recipe;
import cookbook.RecipeBuilder;
import cookbook.RecipeRating;
import cookbook.RecipeRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeRepositoryMock implements RecipeRepository {
    Map<String, Recipe> recipes;
    Map<String, RecipeRating> ratings;

    public RecipeRepositoryMock() {
        recipes = new HashMap<>();
        Recipe recipe1 = new RecipeBuilder()
            .withId("Recipe ID 1")
            .withName("Recipe 1")
            .withAuthor("John")
            .build();
        Recipe recipe2 = new RecipeBuilder()
            .withId("Recipe ID 2")
            .withName("Recipe 2")
            .withAuthor("Jane")
            .build();
        recipes.put(recipe1.id(), recipe1);
        recipes.put(recipe2.id(), recipe2);

        ratings = new HashMap<>();
        RecipeRating rating1 = new RecipeRating(
            "Rating ID 1",
            "Recipe ID 1",
            "Carl",
            5,
            "Title 1",
            "Comment 1"
        );
        RecipeRating rating2 = new RecipeRating(
            "Rating ID 2",
            "Recipe ID 1",
            "August",
            1,
            "Title 2",
            "Comment 2"
        );
        RecipeRating rating3 = new RecipeRating(
            "Rating ID 3",
            "Recipe ID 2",
            "August",
            3,
            "Title 3",
            "Comment 3"
        );
        ratings.put(rating1.id(), rating1);
        ratings.put(rating2.id(), rating2);
        ratings.put(rating3.id(), rating3);
    }

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
