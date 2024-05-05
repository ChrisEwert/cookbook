package services;

import cookbook.Recipe;
import cookbook.RecipeBuilder;
import cookbook.RecipeRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RecipeServiceTest {

    @Test
    void getAllRecipes() {
        // ARRANGE
        RecipeRepository recipeRepository = new RecipeRepositoryMock();
        RecipeService recipeService = new RecipeService(recipeRepository);

        // ACT
        Map<String, Recipe> recipes = recipeService.getAllRecipes();

        // ASSERT
        assertThat(recipes)
            .isEmpty();
    }

    @Test
    void getRecipeById() {
        // ARRANGE
        RecipeRepository recipeRepository = new RecipeRepositoryMock();
        RecipeService recipeService = new RecipeService(recipeRepository);
        Recipe expectedRecipe = new Recipe();
        String recipeId = expectedRecipe.id();
        recipeRepository.addRecipe(recipeId, expectedRecipe);

        // ACT
        Recipe actualRecipe = recipeService.getRecipeById(recipeId);

        // ASSERT
        assertThat(actualRecipe)
            .isEqualTo(expectedRecipe);
    }

//    @Test
//    void getRecipesByAuthor() {
//        // ARRANGE
//        RecipeRepository recipeRepository = new RecipeRepositoryMock();
//        RecipeService recipeService = new RecipeService(recipeRepository);
//        List<Recipe> expectedRecipes = new ArrayList<>();
//        expectedRecipes.add(new Recipe("Name 1", "John", List.of(), List.of(), List.of(), 1));
//        expectedRecipes.add(new Recipe("Name 2", "John", List.of(), List.of(), List.of(), 2));
//        for (Recipe r : expectedRecipes) {
//            recipeRepository.addRecipe(r.id(), r);
//        }
//
//        // ACT
//        List<Recipe> actualRecipes = recipeService.getRecipesByAuthor("John");
//
//        // ASSERT
//        assertThat(actualRecipes)
//            .isEqualTo(expectedRecipes);
//    }

    @Test
    void formatRecipeToSelectData() {
        // ASSERT
        RecipeRepository recipeRepository = new RecipeRepositoryMock();
        RecipeService recipeService = new RecipeService(recipeRepository);
        String name = "Name";
        String author = "Author";
        float rating = 2.5f;
        Recipe recipe = new RecipeBuilder()
            .withName(name)
            .withAuthor(author)
            .withRating(rating)
            .build();
        String expectedResult = name.toUpperCase() + " by " + author + " [" + Math.round(rating) + "⭑]";

        // ACT
        String actualResult = recipeService.formatRecipeToSelectData(recipe);

        // ASSERT
        assertThat(actualResult)
            .isEqualTo(expectedResult);
    }

    @Test
    void saveRecipe() {
    }

    @Test
    void isAuthor() {
    }

    @Test
    void updateRecipe() {
    }

    @Test
    void deleteRecipe() {
    }
}