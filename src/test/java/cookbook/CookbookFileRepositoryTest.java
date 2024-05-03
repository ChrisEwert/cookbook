package cookbook;

import db.UserFileDataHandler;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CookbookFileRepositoryTest {

    @Test
    void getCurrentUsername() {
        // ARRANGE
        CookbookFileRepository repository = new CookbookFileRepository();
        String expectedUsername = "New username";
        repository.setCurrentUsername(expectedUsername);

        // ACT
        String actualUsername = repository.getCurrentUsername();

        // ASSERT
        assertThat(actualUsername)
            .isEqualTo(expectedUsername);
    }

    @Test
    void setCurrentUsername() {
        // ARRANGE
        CookbookFileRepository repository = new CookbookFileRepository();

        // ACT
        repository.setCurrentUsername("New username");

        // ASSERT
        assertThat(repository.getCurrentUsername())
            .isNotNull();
    }

    @Test
    void getCreationDate() {
        // ARRANGE
        CookbookFileRepository repository = new CookbookFileRepository();
        LocalDate expectedCreationDate = LocalDate.now();

        // ACT

        // ASSERT
        assertThat(repository.getCreationDate())
            .isEqualTo(expectedCreationDate);
    }

    @Test
    public void testGetAllUsers() {
        // ARRANGE
        UserFileDataHandler userFileDataHandler = new UserFileDataHandlerMock();
        CookbookFileRepository repository = new CookbookFileRepository(userFileDataHandler);
        Map<String, CookbookUser> expectedUsers = Map.ofEntries(
            Map.entry("John", new CookbookUser("John", "pw")),
            Map.entry("Jane", new CookbookUser("Jane", "pw")),
            Map.entry("Alice", new CookbookUser("Alice", "pw"))
        );
        userFileDataHandler.saveAllUsersToDB(expectedUsers);

        // ACT
        Map<String, CookbookUser> allUsers = repository.getAllUsers();

        // ASSERT
        assertThat(allUsers)
            .isEqualTo(expectedUsers);
    }

    @Test
    void testGetUserByUsername() {
        // ARRANGE
        UserFileDataHandler userFileDataHandler = new UserFileDataHandlerMock();
        CookbookFileRepository repository = new CookbookFileRepository(userFileDataHandler);
        CookbookUser expectedUser = new CookbookUser("John", "pw");
        Map<String, CookbookUser> users = Map.of(expectedUser.username(), expectedUser);
        userFileDataHandler.saveAllUsersToDB(users);

        // ACT
        CookbookUser actualUser = repository.getUserByUsername(expectedUser.username());

        // ASSERT
        assertThat(actualUser)
            .isEqualTo(expectedUser);
    }

    @Test
    void testCreateNewUser() {
        // ARRANGE
        UserFileDataHandler userFileDataHandler = new UserFileDataHandlerMock();
        CookbookFileRepository repository = new CookbookFileRepository(userFileDataHandler);
        CookbookUser newUser = new CookbookUser();

        // ACT
        repository.createNewUser(newUser);

        // ASSERT
        assertThat(userFileDataHandler.getAllUsersFromDB())
            .isNotEmpty();
    }

    @Test
    void testGetBookmarkedRecipeIdsByUsername() {
        // ARRANGE
        UserFileDataHandler userFileDataHandler = new UserFileDataHandlerMock();
        CookbookFileRepository repository = new CookbookFileRepository(userFileDataHandler);
        CookbookUser newUser = new CookbookUser("John", "pw");
        repository.createNewUser(newUser);
        String recipeId = "New recipe ID";
        repository.addBookmarkedRecipeId(newUser.username(), recipeId);
        Set<String> expectedRecipeIds = Set.of(recipeId);

        // ACT
        Set<String> bookmarkedRecipeIds = repository.getBookmarkedRecipeIdsByUsername(newUser.username());

        // ASSERT
        assertThat(bookmarkedRecipeIds)
            .isEqualTo(expectedRecipeIds);
    }

    @Test
    void testAddBookmarkedRecipeId() {
        // ARRANGE
        UserFileDataHandler userFileDataHandler = new UserFileDataHandlerMock();
        CookbookFileRepository repository = new CookbookFileRepository(userFileDataHandler);
        CookbookUser newUser = new CookbookUser("John", "pw");
        repository.createNewUser(newUser);

        // ACT
        repository.addBookmarkedRecipeId(newUser.username(), "New recipe ID");

        // ASSERT
        assertThat(repository.getBookmarkedRecipeIdsByUsername(newUser.username()))
            .isNotEmpty();
    }
}
