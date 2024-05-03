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
        RecipeRating updatedRating = rating.updateRating(author, stars, title, comment);

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
}
