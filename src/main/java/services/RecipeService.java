package services;

import cookbook.CookbookRepository;
import cookbook.Recipe;
import cookbook.RecipeRepository;

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
}
