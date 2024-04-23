package services;

import cookbook.Recipe;
import cookbook.RecipeRating;
import cookbook.RecipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public boolean noRecipesExist() {
        return recipeRepository.getAllRecipes().isEmpty();
    }

    public int recipeCount() {
        return recipeRepository.getAllRecipes().size();
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.getAllRecipes();
    }

    public Recipe getRecipeByIndex(int index) {
        return getAllRecipes().get(index);
    }

    public String getRecipeSelectDataByIndex(int index) {
        Recipe recipe = getRecipeByIndex(index);
        return formatRecipeToSelectData(recipe);
    }

    public Recipe getRecipeById(String id) {
        return recipeRepository.getRecipeById(id);
    }

    public String getRecipeSelectDataById(String id) {
        Recipe recipe = getRecipeById(id);
        return (recipe != null) ? formatRecipeToSelectData(recipe) : "Recipe not found";
    }

    public Recipe getLastRecipe() {
        return getRecipeByIndex(recipeCount()-1);
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

    public void addRating(String id, String author, int rating, String title, String comment) {
        RecipeRating newRating = new RecipeRating(id, author, rating, title, comment);

        recipeRepository.addRating(newRating);
    }

    public void updateStarsOfRecipe(Recipe recipe) {
        List<RecipeRating> ratings = getRecipeRatingsByRecipeId(recipe.id());

        float stars = 0;
        for (RecipeRating rating : ratings) {
            stars += rating.stars();
        }
        stars /= ratings.size();

        recipeRepository.setStars(recipe, stars, ratings.size());
    }

    private List<RecipeRating> getRecipeRatingsByRecipeId(String recipeId) {
        List<RecipeRating> allRatings = recipeRepository.getAllRatings();
        List<RecipeRating> ratingsOfRecipe = new ArrayList<>();

        for (RecipeRating rating : allRatings) {
            if (Objects.equals(rating.recipeId(), recipeId)) {
                ratingsOfRecipe.add(rating);
            }
        }

        return ratingsOfRecipe;
    }

    private String formatRecipeToSelectData(Recipe recipe) {
        return recipe.name().toUpperCase() + " by " + recipe.author() + " [" + Math.round(recipe.rating()) + "⭑]";
    }
}