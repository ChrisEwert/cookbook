package cookbook;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @Test
    void testToString() {
        // ARRANGE
        String name = "New name";
        String author = "New author";
        LocalDate dateOfCreation = LocalDate.now();
        List<String> ingredients = List.of("Ingredient 1", "Ingredient 2");
        List<String> content = List.of("Step 1", "Step 2");
        List<String> categories = List.of("Category 1", "Category 2");
        int cookingTimeInMinutes = 5;
        float rating = 0f;
        int ratingCount = 0;

        Recipe recipe = new Recipe(
            name,
            author,
            ingredients,
            content,
            categories,
            cookingTimeInMinutes
        );

        StringBuilder builder = new StringBuilder();
        builder.append("═══════════════════════════════════════════════════════\n");
        builder.append(" ").append(name).append("\n");
        builder.append("═══════════════════════════════════════════════════════\n");
        builder.append(author).append("\t\t");
        builder.append(rating);
        builder.append("/5⭑ [");
        builder.append(ratingCount).append(" rating(s)]\t\t");
        builder.append(dateOfCreation.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        builder.append("\n");
        int hours = cookingTimeInMinutes / 60;
        int remainingMinutes = cookingTimeInMinutes % 60;
        String formattedHours = String.format("%02d", hours);
        String formattedMinutes = String.format("%02d", remainingMinutes);
        String time = formattedHours + ":" + formattedMinutes + " h";
        builder.append(time);
        builder.append("\t\t");
        for (int i = 0; i < categories.size() - 1; i++) {
            builder.append(categories.get(i)).append(", ");
        }
        builder.append(categories.get(categories.size() - 1));
        builder.append("\n\n");
        builder.append("Ingredients:\n");
        for (String ingredient : ingredients) {
            builder.append("- ").append(ingredient).append("\n");
        }
        builder.append("\n");
        builder.append("Cooking steps:\n");
        for (int i = 0 ; i < content.size() ; i++) {
            builder.append("STEP ").append(i+1).append("\n");
            builder.append("\t").append(content.get(i)).append("\n");
        }
        builder.append("═══════════════════════════════════════════════════════");

        String expectedResult = builder.toString();

        // ACT
        String actualResult = recipe.toString();

        // ASSERT
        assertThat(actualResult)
            .isEqualTo(expectedResult);
    }
}
