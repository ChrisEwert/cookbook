package services;

import cookbook.Recipe;
import cookbook.RecipeRating;
import cookbook.RecipeRepository;

import java.util.List;

public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void saveRecipe(String name, List<String> ingredients, List<String> content, List<String> categories, int minutes) {
        Recipe recipe = new Recipe(name, ingredients, content, categories, minutes);
        recipeRepository.saveRecipe(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.getAllRecipes();
    }

    public boolean noRecipesExist() {
        return recipeRepository.getAllRecipes().isEmpty();
    }

    public int recipeCount() {
        return recipeRepository.getAllRecipes().size();
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

    private String formatRecipeToSelectData(Recipe recipe) {
        return recipe.name().toUpperCase() + " by " + recipe.author() + " [" + Math.round(recipe.rating()) + "⭑]";
    }

    public void addRating(String id, int rating, String title, String comment) {
        RecipeRating newRating = new RecipeRating(id, rating, title, comment);

        recipeRepository.addRating(newRating);
    }
}