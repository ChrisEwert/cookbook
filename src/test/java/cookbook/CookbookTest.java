package cookbook;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CookbookTest {

    @Test
    void changeCurrentUsername() {
        // ARRANGE
        Cookbook cookbook = new Cookbook();

        String username = "New username";

        // ACT
        Cookbook updatedCookbook = cookbook.changeCurrentUsername(username);

        // ASSERT
        assertThat(updatedCookbook.dateOfCreation())
            .isEqualTo(cookbook.dateOfCreation());
        assertThat(updatedCookbook.currentUsername())
            .isNotEqualTo(cookbook.currentUsername())
            .isEqualTo(username);
    }
}