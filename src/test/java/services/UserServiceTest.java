package services;

import cookbook.CookbookRepository;
import cookbook.CookbookUser;
import cookbook.Recipe;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    @Test
    void testGetAllUsernames() {
        // ARRANGE
        CookbookRepository cookbookRepository = new CookbookRepositoryMock();
        UserService userService = new UserService(cookbookRepository);

        // ACT
        List<String> usernames = userService.getAllUsernames();

        // ASSERT
        assertThat(usernames)
            .isNotEmpty();
    }

    @Test
    void testCreateNewUser() {
        // ARRANGE
        CookbookRepository cookbookRepository = new CookbookRepositoryMock();
        UserService userService = new UserService(cookbookRepository);
        String username = "Bob";
        String password = "pw";

        // ACT
        userService.createNewUser(username, password);
        CookbookUser user = cookbookRepository.getUserByUsername(username);

        // ASSERT
        assertThat(user)
            .isNotNull();
    }

    @Test
    void testGetBookmarkedRecipeIdsByUsername() {
        // ARRANGE
        CookbookRepository cookbookRepository = new CookbookRepositoryMock();
        UserService userService = new UserService(cookbookRepository);
        userService.createNewUser("Charles", "pw");

        // ACT
        List<String> bookmarkedRecipeIds = userService.getBookmarkedRecipeIdsByUsername("Charles");

        // ASSERT
        assertThat(bookmarkedRecipeIds)
            .isEmpty();
    }

    @Test
    void testBookmarkRecipe() {
        // ARRANGE
        CookbookRepository cookbookRepository = new CookbookRepositoryMock();
        UserService userService = new UserService(cookbookRepository);
        String username = "Charles";
        userService.createNewUser(username, "pw");
        Recipe recipe = new Recipe();

        // ACT
        userService.bookmarkRecipe(username, recipe);

        // ASSERT
        assertThat(userService.getBookmarkedRecipeIdsByUsername(username))
            .contains(recipe.id());
    }
}