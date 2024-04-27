package services;

import cookbook.Recipe;
import cookbook.RecipeRating;
import cookbook.RecipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Map<String, Recipe> getAllRecipes() {
        return recipeRepository.getAllRecipes();
    }

    public List<String> getAllRecipeSelectData() {
        List<String> data = new ArrayList<>();

        for (Recipe recipe : getAllRecipes().values()) {
            data.add(formatRecipeToSelectData(recipe));
        }

        return data;
    }

    public String getRecipeIdByIndex(int index) {
        int count = 1;

        for (Recipe recipe : getAllRecipes().values()) {
            if (count == index) {
                return recipe.id();
            }
            count++;
        }

        return null;
    }

    public Recipe getRecipeById(String id) {
        return recipeRepository.getRecipeById(id);
    }

    public String getRecipeSelectDataById(String id) {
        Recipe recipe = getRecipeById(id);

        return formatRecipeToSelectData(recipe);
    }

    public int recipeCount() {
        Map<String, Recipe> recipes = getAllRecipes();

        return recipes.size();
    }

    public boolean noRecipesExist() {
        return recipeCount() == 0;
    }

    public List<RecipeRating> getRecipeRatingsByRecipeId(String recipeId) {
        List<RecipeRating> allRatings = recipeRepository.getAllRatings();
        List<RecipeRating> ratingsOfRecipe = new ArrayList<>();

        for (RecipeRating rating : allRatings) {
            if (Objects.equals(rating.recipeId(), recipeId)) {
                ratingsOfRecipe.add(rating);
            }
        }

        return ratingsOfRecipe;
    }

    public void saveRecipe(String name, String author, List<String> ingredients, List<String> content, List<String> categories, int minutes) {
        Recipe recipe = new Recipe(name, author, ingredients, content, categories, minutes);

        recipeRepository.saveRecipe(recipe);
    }

    public boolean hasRated(String username, String id) {
        List<RecipeRating> ratings = getRecipeRatingsByRecipeId(id);

        for (RecipeRating rating : ratings) {
            if (Objects.equals(rating.author(), username)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasRatings(String id) {
        List<RecipeRating> ratings = getRecipeRatingsByRecipeId(id);

        return ratings.isEmpty();
    }

    public void addRating(String id, String author, int rating, String title, String comment) {
        RecipeRating newRating = new RecipeRating(id, author, rating, title, comment);

        recipeRepository.addRating(newRating);

        Recipe updatedRecipe = recipeRepository.getRecipeById(id);

        updateStarsOfRecipe(updatedRecipe);
    }

    public void updateStarsOfRecipe(Recipe recipe) {
        List<RecipeRating> ratings = getRecipeRatingsByRecipeId(recipe.id());

        float stars = 0;
        for (RecipeRating rating : ratings) {
            stars += rating.stars();
        }
        stars /= ratings.size();

        recipeRepository.updateRecipeRating(recipe, stars, ratings.size());
    }

    private String formatRecipeToSelectData(Recipe recipe) {
        return recipe.name().toUpperCase() + " by " + recipe.author() + " [" + Math.round(recipe.rating()) + "⭑]";
    }
}