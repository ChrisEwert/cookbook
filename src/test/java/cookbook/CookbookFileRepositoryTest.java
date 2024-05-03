package cookbook;

import db.UserFileDataHandler;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CookbookFileRepositoryTest {

    @Test
    void testGetCurrentUsername() {
        // ARRANGE
        CookbookFileRepository repository = new CookbookFileRepository();

        // ACT
        String username = repository.getCurrentUsername();

        // ASSERT
        assertThat(username)
            .isNull();
    }

    @Test
    void testSetCurrentUsername() {
        // ARRANGE
        CookbookFileRepository repository = new CookbookFileRepository();

        // ACT
        repository.setCurrentUsername("New username");

        // ASSERT
        assertThat(repository.getCurrentUsername())
            .isNotNull();
    }

    @Test
    void testGetCreationDate() {
        // ARRANGE
        CookbookFileRepository repository = new CookbookFileRepository();
        LocalDate expectedCreationDate = LocalDate.now();

        // ACT
        LocalDate actualCreationDate = repository.getCreationDate();

        // ASSERT
        assertThat(actualCreationDate)
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
