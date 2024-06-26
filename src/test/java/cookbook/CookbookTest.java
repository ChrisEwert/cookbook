package cookbook;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CookbookTest {

    @Test
    void testSetCurrentUsername() {
        // ARRANGE
        Cookbook cookbook = new Cookbook();
        String username = "New username";

        // ACT
        cookbook.setCurrentUsername(username);

        // ASSERT
        assertThat(cookbook.getCurrentUsername())
            .isEqualTo(username);
    }
}