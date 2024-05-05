package cookbook;

import db.RatingFileDataHandler;
import db.RecipeFileDataHandler;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RecipeFileRepositoryTest {

    @Test
    void testGetAllRecipes() {
        // ARRANGE
        RecipeFileDataHandler recipeFileDataHandler = new RecipeFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(recipeFileDataHandler);

        // ACT
        Map<String, Recipe> recipes = repository.getAllRecipes();

        // ASSERT
        assertThat(recipes)
            .isNotEmpty();
    }

    @Test
    void testGetRecipeById() {
        // ARRANGE
        RecipeFileDataHandler recipeFileDataHandler = new RecipeFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(recipeFileDataHandler);
        Recipe expectedRecipe = new Recipe();
        String recipeId = expectedRecipe.id();
        repository.addRecipe(recipeId, expectedRecipe);

        // ACT
        Recipe actualRecipe = repository.getRecipeById(recipeId);

        // ASSERT
        assertThat(actualRecipe)
            .isEqualTo(expectedRecipe);
    }

    @Test
    void testAddRecipe() {
        // ARRANGE
        RecipeFileDataHandler recipeFileDataHandler = new RecipeFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(recipeFileDataHandler);
        Recipe recipe = new Recipe();
        String recipeId = recipe.id();

        // ACT
        repository.addRecipe(recipeId, recipe);

        // ASSERT
        assertThat(repository.getRecipeById(recipeId))
            .isNotNull();
    }

    @Test
    void testUpdateRecipe() {
        // ARRANGE
        RecipeFileDataHandler recipeFileDataHandler = new RecipeFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(recipeFileDataHandler);
        Recipe recipe = new Recipe();
        String recipeId = recipe.id();
        repository.addRecipe(recipeId, recipe);
        Recipe updatedRecipe = recipe.updateRecipe("Name", "Author", List.of(), List.of(), List.of(), 5);

        // ACT
        repository.updateRecipe(recipeId, updatedRecipe);

        // ASSERT
        assertThat(repository.getRecipeById(recipeId))
            .isNotEqualTo(recipe)
            .isEqualTo(updatedRecipe);
    }

    @Test
    void testDeleteRecipe() {
        // ARRANGE
        RecipeFileDataHandler recipeFileDataHandler = new RecipeFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(recipeFileDataHandler);
        Recipe recipe = new Recipe();
        String recipeId = recipe.id();
        repository.addRecipe(recipeId, recipe);

        // ACT
        repository.deleteRecipe(recipeId);

        // ASSERT
        assertThat(repository.getRecipeById(recipeId))
            .isNull();
    }

    @Test
    void testGetAllRatings() {
        // ARRANGE
        RatingFileDataHandler ratingFileDataHandler = new RatingFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(ratingFileDataHandler);

        // ACT
        Map<String, RecipeRating> ratings = repository.getAllRatings();

        // ASSERT
        assertThat(ratings)
            .isNotEmpty();
    }

    @Test
    void testGetRatingById() {
        // ARRANGE
        RatingFileDataHandler ratingFileDataHandler = new RatingFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(ratingFileDataHandler);
        RecipeRating expectedRating = new RecipeRating();
        String ratingId = expectedRating.id();
        repository.addRating(ratingId, expectedRating);

        // ACT
        RecipeRating actualRating = repository.getRatingById(ratingId);

        // ASSERT
        assertThat(actualRating)
            .isEqualTo(expectedRating);
    }

    @Test
    void testGetRatingsOfRecipe() {
        // ARRANGE
        RatingFileDataHandler ratingFileDataHandler = new RatingFileDataHandlerMock();
        RecipeFileDataHandler recipeFileDataHandler = new RecipeFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(recipeFileDataHandler, ratingFileDataHandler);
        String existingRecipeId = "Recipe ID 1";

        // ACT
        List<RecipeRating> ratings = repository.getRatingsOfRecipe(existingRecipeId);

        // ASSERT
        assertThat(ratings)
            .isNotEmpty();
    }

    @Test
    void testGetRatingByName() {
        // ARRANGE
        RatingFileDataHandler ratingFileDataHandler = new RatingFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(ratingFileDataHandler);

        String existingRecipeId = "Recipe ID 1";
        String existingAuthor = "John";
        String nonExistingAuthor = "Bob";

        // ACT
        RecipeRating existingRating = repository.getRatingOfRecipeByAuthor(existingRecipeId, existingAuthor);
        RecipeRating nonExistingRating = repository.getRatingOfRecipeByAuthor(existingRecipeId, nonExistingAuthor);

        // ASSERT
        assertThat(existingRating)
            .isNotNull();
        assertThat(nonExistingRating)
            .isNull();
    }

    @Test
    void testAddRating() {
        // ARRANGE
        RatingFileDataHandler ratingFileDataHandler = new RatingFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(ratingFileDataHandler);
        RecipeRating rating = new RecipeRating();
        String ratingId = rating.id();

        // ACT
        repository.addRating(ratingId, rating);

        // ASSERT
        assertThat(repository.getRatingById(ratingId))
            .isEqualTo(rating);
    }

    @Test
    void testUpdateExistingRatingOfRecipe() {
        // ARRANGE
        RatingFileDataHandler ratingFileDataHandler = new RatingFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(ratingFileDataHandler);
        RecipeRating rating = new RecipeRating();
        String ratingId = rating.id();
        repository.addRating(ratingId, rating);
        RecipeRating updatedRating = new RecipeRating(ratingId, "Recipe ID 1", "Author", 5, "Title", "Comment");

        // ACT
        repository.updateExistingRatingOfRecipe(ratingId, updatedRating);

        // ASSERT
        assertThat(repository.getRatingById(ratingId))
            .isNotEqualTo(rating)
            .isEqualTo(updatedRating);
    }

    @Test
    void deleteRating() {
        // ARRANGE
        RatingFileDataHandler ratingFileDataHandler = new RatingFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(ratingFileDataHandler);
        RecipeRating rating = new RecipeRating();
        String ratingId = rating.id();
        repository.addRating(ratingId, rating);

        // ACT
        repository.deleteRating(ratingId);

        // ASSERT
        assertThat(repository.getRatingById(ratingId))
            .isNull();
    }

    @Test
    void deleteAllRatingsOfRecipe() {
        // ARRANGE
        RatingFileDataHandler ratingFileDataHandler = new RatingFileDataHandlerMock();
        RecipeFileDataHandler recipeFileDataHandler = new RecipeFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(recipeFileDataHandler, ratingFileDataHandler);
        Recipe existingRecipe = repository.getRecipeById("Recipe ID 1");

        // ACT
        repository.deleteAllRatingsOfRecipe(existingRecipe.id());

        // ASSERT
        assertThat(repository.getRatingsOfRecipe(existingRecipe.id()))
            .isEmpty();
    }
}