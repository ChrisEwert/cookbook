package services;

import cookbook.Recipe;
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

    public String getRecipeTitle(int index) {
        Recipe recipe = getRecipeByIndex(index);
        return recipe.name().toUpperCase() + " by " + recipe.author() + " [" + Math.round(recipe.rating()) + "⭑]";
    }

    public Recipe getRecipeByIndex(int index) {
        return getAllRecipes().get(index);
    }

    public Recipe getRecipeById(long id) {
        return recipeRepository.getRecipeById(id);
    }
}