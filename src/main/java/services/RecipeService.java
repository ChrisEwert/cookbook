package services;

import cookbook.Recipe;
import cookbook.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void saveRecipe(String name, String content, List<String> categories, int minutes) {
        Recipe recipe = new Recipe(name, content, categories, minutes);
        recipeRepository.saveRecipe(recipe);
    }

    public List<String[]> getRecipeData() {
        List<String[]> data = new ArrayList<>();
        recipeRepository.getAllRecipes().forEach(recipe -> {
            data.add(new String[]{recipe.name(), recipe.author()});
        });
        return data;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.getAllRecipes();
    }
}
