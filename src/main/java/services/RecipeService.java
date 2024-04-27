package services;

import cookbook.Recipe;
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

    public List<Recipe> getRecipesByAuthor(String username) {
        Map<String, Recipe> recipes = getAllRecipes();
        List<Recipe> recipesList = new ArrayList<>();

        for (Recipe recipe : recipes.values()) {
            if (recipe.author().equals(username)) {
                recipesList.add(recipe);
            }
        }

        return recipesList;
    }

    public String formatRecipeToSelectData(Recipe recipe) {
        return recipe.name().toUpperCase() + " by " + recipe.author() + " [" + Math.round(recipe.rating()) + "⭑]";
    }

    public void saveRecipe(String name, String author, List<String> ingredients, List<String> content, List<String> categories, int minutes) {
        Recipe recipe = new Recipe(name, author, ingredients, content, categories, minutes);

        recipeRepository.addRecipe(recipe);
    }

    public boolean isAuthor(String username, Recipe recipe) {
        return Objects.equals(recipe.author(), username);
    }
}