package services;

import cookbook.Recipe;
import cookbook.RecipeBuilder;
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

    public Recipe getRecipeById(String recipeId) {
        return recipeRepository.getRecipeById(recipeId);
    }

    public List<Recipe> getRecipesByAuthor(String author) {
        Map<String, Recipe> recipes = getAllRecipes();
        List<Recipe> recipesList = new ArrayList<>();

        for (Recipe recipe : recipes.values()) {
            if (recipe.author().equals(author)) {
                recipesList.add(recipe);
            }
        }

        return recipesList;
    }

    public String formatRecipeToSelectData(Recipe recipe) {
        return recipe.name().toUpperCase() + " by " + recipe.author() + " [" + Math.round(recipe.rating()) + "⭑]";
    }

    public void saveRecipe(
        String name,
        String author,
        List<String> ingredients,
        List<String> content,
        List<String> categories,
        int minutes
    ) {
        Recipe recipe = new RecipeBuilder()
            .withName(name)
            .withAuthor(author)
            .withIngredients(ingredients)
            .withContent(content)
            .withCategories(categories)
            .withCookingTimeInMinutes(minutes)
            .build();

        recipeRepository.addRecipe(recipe.id(), recipe);
    }

    public boolean isAuthor(String username, Recipe recipe) {
        return Objects.equals(recipe.author(), username);
    }

    public void updateRecipe(
        String recipeId,
        String name,
        String author,
        List<String> ingredients,
        List<String> content,
        List<String> categories,
        int minutes
    ) {
        Recipe newRecipe = getRecipeById(recipeId);
        newRecipe = newRecipe.updateRecipe(name, author, ingredients, content, categories, minutes);

        recipeRepository.updateRecipe(recipeId, newRecipe);
    }

    public void deleteRecipe(String recipeId) {
        recipeRepository.deleteRecipe(recipeId);
    }
}