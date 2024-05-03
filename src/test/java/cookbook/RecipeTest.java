package cookbook;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

class RecipeTest {

    @Test
    void testUpdateRecipe() {
        // ARRANGE
        Recipe recipe = new Recipe();
        String name = "New name";
        String author = "New author";
        List<String> ingredients = List.of("Ingredient 1", "Ingredient 2");
        List<String> content = List.of("Step 1", "Step 2");
        List<String> categories = List.of("Category 1", "Category 2");
        int minutes = 5;

        // ACT
        Recipe updatedRecipe = recipe.updateRecipe(
            name,
            author,
            ingredients,
            content,
            categories,
            minutes
        );

        // ASSERT
        assertThat(updatedRecipe.id())
            .isEqualTo(recipe.id());
        assertThat(updatedRecipe.name())
            .isNotEqualTo(recipe.name())
            .isEqualTo(name);
        assertThat(updatedRecipe.author())
            .isNotEqualTo(recipe.author())
            .isEqualTo(author);
        assertThat(updatedRecipe.dateOfCreation())
            .isEqualTo(recipe.dateOfCreation());
        assertThat(updatedRecipe.ingredients())
            .isNotEqualTo(recipe.ingredients())
            .isEqualTo(ingredients);
        assertThat(updatedRecipe.content())
            .isNotEqualTo(recipe.content())
            .isEqualTo(content);
        assertThat(updatedRecipe.categories())
            .isNotEqualTo(recipe.categories())
            .isEqualTo(categories);
        assertThat(updatedRecipe.cookingTimeInMinutes())
            .isNotEqualTo(recipe.cookingTimeInMinutes())
            .isEqualTo(minutes);
        assertThat(updatedRecipe.rating())
            .isEqualTo(recipe.rating());
        assertThat(updatedRecipe.ratingCount())
            .isEqualTo(recipe.ratingCount());
    }

    @Test
    void testChangeRating() {
        // ARRANGE
        Recipe recipe = new Recipe();
        float rating = 2.5f;
        int ratingCount = 10;

        // ACT
        Recipe updatedRecipe = recipe.changeRating(rating, ratingCount);

        // ASSERT
        assertThat(updatedRecipe.id())
            .isEqualTo(recipe.id());
        assertThat(updatedRecipe.name())
            .isEqualTo(recipe.name());
        assertThat(updatedRecipe.author())
            .isEqualTo(recipe.author());
        assertThat(updatedRecipe.dateOfCreation())
            .isEqualTo(recipe.dateOfCreation());
        assertThat(updatedRecipe.ingredients())
            .isEqualTo(recipe.ingredients());
        assertThat(updatedRecipe.content())
            .isEqualTo(recipe.content());
        assertThat(updatedRecipe.categories())
            .isEqualTo(recipe.categories());
        assertThat(updatedRecipe.cookingTimeInMinutes())
            .isEqualTo(recipe.cookingTimeInMinutes());
        assertThat(updatedRecipe.rating())
            .isNotEqualTo(recipe.rating())
            .isEqualTo(rating);
        assertThat(updatedRecipe.ratingCount())
            .isNotEqualTo(recipe.ratingCount())
            .isEqualTo(ratingCount);
    }
}
