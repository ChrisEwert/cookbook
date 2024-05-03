package cookbook;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RecipeRatingTest {

    @Test
    void testUpdateRating() {
        // ARRANGE
        RecipeRating rating = new RecipeRating();
        String author = "Test author";
        int stars = 5;
        String title = "Test title";
        String comment = "Test comment";

        // ACT
        RecipeRating updatedRating = rating.updateRating(
            author,
            stars,
            title,
            comment
        );

        // ASSERT
        assertThat(updatedRating.id())
            .isEqualTo(rating.id());
        assertThat(updatedRating.recipeId())
            .isEqualTo(rating.recipeId());
        assertThat(updatedRating.author())
            .isNotEqualTo(rating.author())
            .isEqualTo(author);
        assertThat(updatedRating.stars())
            .isNotEqualTo(rating.stars())
            .isEqualTo(stars);
        assertThat(updatedRating.title())
            .isNotEqualTo(rating.title())
            .isEqualTo(title);
        assertThat(updatedRating.comment())
            .isNotEqualTo(rating.comment())
            .isEqualTo(comment);

    }

    @Test
    void testToString() {
        // ARRANGE
        String recipeId = "Recipe ID";
        String author = "Test author";
        int stars = 5;
        String title = "Test title";
        String comment = "Test comment";
        RecipeRating rating = new RecipeRating(recipeId, author, stars, title, comment);

        StringBuilder builder = new StringBuilder();
        builder.append("───────────────────\n");
        builder.append(stars);
        builder.append("⭑");
        builder.append("\t\t\t");
        builder.append(author);
        builder.append("\n");
        builder.append(title.toUpperCase());
        builder.append("\n");
        builder.append(comment);
        builder.append("\n");
        builder.append("───────────────────");

        String expectedResult = builder.toString();

        // ACT
        String actualResult = rating.toString();

        // ASSERT
        assertThat(actualResult)
            .isEqualTo(expectedResult);
    }
}
