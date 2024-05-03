package cookbook;

import db.RatingFileDataHandler;
import db.RecipeFileDataHandler;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RecipeFileRepositoryTest {

    @Test
    void getAllRecipes() {
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
    void getRecipeById() {
        // ARRANGE
        RecipeFileDataHandler recipeFileDataHandler = new RecipeFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(recipeFileDataHandler);
        Recipe expectedRecipe = new Recipe();
        repository.addRecipe(expectedRecipe);

        // ACT
        Recipe actualRecipe = repository.getRecipeById(expectedRecipe.id());

        // ASSERT
        assertThat(actualRecipe)
            .isEqualTo(expectedRecipe);
    }

    @Test
    void addRecipe() {
        // ARRANGE
        RecipeFileDataHandler recipeFileDataHandler = new RecipeFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(recipeFileDataHandler);
        Recipe recipe = new Recipe();

        // ACT
        repository.addRecipe(recipe);

        // ASSERT
        assertThat(repository.getAllRecipes())
            .isNotEmpty();
    }

    @Test
    void updateRatingStarsOfRecipe() {
        // ARRANGE
        RecipeFileDataHandler recipeFileDataHandler = new RecipeFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(recipeFileDataHandler);
        Recipe recipe = new Recipe();
        repository.addRecipe(recipe);
        float stars = 2.5f;
        int ratingCount = 10;

        // ACT
        repository.updateRatingStarsOfRecipe(recipe, stars, ratingCount);

        // ASSERT
        assertThat(repository.getRecipeById(recipe.id()).rating())
            .isEqualTo(2.5f);
        assertThat(repository.getRecipeById(recipe.id()).ratingCount())
            .isEqualTo(ratingCount);
    }

    @Test
    void updateRecipe() {
        // ARRANGE
        RecipeFileDataHandler recipeFileDataHandler = new RecipeFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(recipeFileDataHandler);
        Recipe recipe = new Recipe();
        repository.addRecipe(recipe);
        Recipe updatedRecipe = new Recipe("Name", "Author", List.of(), List.of(), List.of(), 5);

        // ACT
        repository.updateRecipe(recipe.id(), updatedRecipe);

        // ASSERT
        assertThat(repository.getRecipeById(recipe.id()))
            .isNotEqualTo(recipe)
            .isEqualTo(updatedRecipe);
    }

    @Test
    void deleteRecipe() {
        // ARRANGE
        RecipeFileDataHandler recipeFileDataHandler = new RecipeFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(recipeFileDataHandler);
        Recipe recipe = new Recipe();
        repository.addRecipe(recipe);

        // ACT
        repository.deleteRecipe(recipe.id());

        // ASSERT
        assertThat(repository.getRecipeById(recipe.id()))
            .isNull();
    }

    @Test
    void getAllRatings() {
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
    void getRatingById() {
        // ARRANGE
        RatingFileDataHandler ratingFileDataHandler = new RatingFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(ratingFileDataHandler);
        RecipeRating expectedRating = new RecipeRating();
        repository.addRating(expectedRating);

        // ACT
        RecipeRating actualRating = repository.getRatingById(expectedRating.id());

        // ASSERT
        assertThat(actualRating)
            .isEqualTo(expectedRating);
    }

    @Test
    void getRatingByName() {
        // ARRANGE
        RecipeFileDataHandler recipeFileDataHandler = new RecipeFileDataHandlerMock();
        RatingFileDataHandler ratingFileDataHandler = new RatingFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(recipeFileDataHandler, ratingFileDataHandler);

        Recipe existingRecipe = repository.getRecipeById("Recipe ID 1");
        String existingAuthor = "John";
        String nonExistingAuthor = "Bob";

        // ACT
        RecipeRating existingRating = repository.getRatingByName(existingRecipe.id(), existingAuthor);
        RecipeRating nonExistingRating = repository.getRatingByName(existingRecipe.id(), nonExistingAuthor);

        // ASSERT
        assertThat(existingRating)
            .isNotNull();
        assertThat(nonExistingRating)
            .isNull();
    }

    @Test
    void getRatingsOfRecipe() {
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
    void addRating() {
        // ARRANGE
        RatingFileDataHandler ratingFileDataHandler = new RatingFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(ratingFileDataHandler);
        RecipeRating rating = new RecipeRating();

        // ACT
        repository.addRating(rating);

        // ASSERT
        assertThat(repository.getRatingById(rating.id()))
            .isNotNull();
    }

    @Test
    void updateExistingRatingOfRecipe() {
        // ARRANGE
        RatingFileDataHandler ratingFileDataHandler = new RatingFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(ratingFileDataHandler);
        RecipeRating rating = new RecipeRating();
        repository.addRating(rating);
        RecipeRating updatedRating = new RecipeRating(rating.id(), "Recipe ID 1", "Author", 5, "Title", "Comment");

        // ACT
        repository.updateExistingRatingOfRecipe(updatedRating);

        // ASSERT
        assertThat(repository.getRatingById(updatedRating.id()))
            .isNotEqualTo(rating);
    }

    @Test
    void deleteRating() {
        // ARRANGE
        RatingFileDataHandler ratingFileDataHandler = new RatingFileDataHandlerMock();
        RecipeFileRepository repository = new RecipeFileRepository(ratingFileDataHandler);
        RecipeRating rating = new RecipeRating();
        repository.addRating(rating);

        // ACT
        repository.deleteRating(rating.id());

        // ASSERT
        assertThat(repository.getRatingById(rating.id()))
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