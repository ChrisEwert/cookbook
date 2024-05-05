package cookbook;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class CookbookTest {

    @Test
    void testSetCurrentUsername() {
        // ARRANGE
        Cookbook cookbook = new Cookbook();
        LocalDate expectedDate = LocalDate.now();
        String username = "New username";

        // ACT
        cookbook.setCurrentUsername(username);

        // ASSERT
        assertThat(cookbook.getDateOfCreation())
            .isEqualTo(expectedDate);
        assertThat(cookbook.getCurrentUsername())
            .isEqualTo(username);
    }
}