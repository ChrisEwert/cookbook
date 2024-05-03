package services;

import cookbook.CookbookRepository;
import cookbook.CookbookUser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

class AuthenticationServiceTest {

    @Test
    void testGetCookbookCreationDate() {
        // ARRANGE
        CookbookRepository cookbookRepository = new CookbookRepositoryMock();
        AuthenticationService authenticationService = new AuthenticationService(cookbookRepository);
        String expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        // ACT
        String actualDate = authenticationService.getCookbookCreationDate();

        // ASSERT
        assertThat(actualDate)
            .isEqualTo(expectedDate);
    }

    @Test
    void testGetCurrentUsername() {
        // ARRANGE
        CookbookRepository cookbookRepository = new CookbookRepositoryMock();
        AuthenticationService authenticationService = new AuthenticationService(cookbookRepository);

        // ACT
        String username = authenticationService.getCurrentUsername();

        // ASSERT
        assertThat(username)
            .isNull();
    }

    @Test
    void testUserExists() {
        // ARRANGE
        CookbookRepository cookbookRepository = new CookbookRepositoryMock();
        AuthenticationService authenticationService = new AuthenticationService(cookbookRepository);
        String usernameThatDoesExist = "John";
        String usernameThatDoesNotExist = "Bob";

        // ACT
        boolean userDoesExist = authenticationService.userExists(usernameThatDoesExist);
        boolean userDoesNotExist = authenticationService.userExists(usernameThatDoesNotExist);

        // ASSERT
        assertThat(userDoesExist)
            .isEqualTo(true);
        assertThat(userDoesNotExist)
            .isEqualTo(false);
    }

    @Test
    void testCredentialsMatch() {
        // ARRANGE
        CookbookRepository cookbookRepository = new CookbookRepositoryMock();
        AuthenticationService authenticationService = new AuthenticationService(cookbookRepository);
        CookbookUser userWithRightPassword = new CookbookUser("John", "pw");
        CookbookUser userWithWrongPassword = new CookbookUser("John", "password");

        // ACT
        boolean passwordIsRight = authenticationService.credentialsMatch(
            userWithRightPassword.username(),
            userWithRightPassword.password()
        );
        boolean passwordIsWrong = authenticationService.credentialsMatch(
            userWithWrongPassword.username(),
            userWithWrongPassword.password()
        );

        // ARRANGE
        assertThat(passwordIsRight)
            .isEqualTo(true);
        assertThat(passwordIsWrong)
            .isEqualTo(false);
    }

    @Test
    void testLogin() {
        // ARRANGE
        CookbookRepository cookbookRepository = new CookbookRepositoryMock();
        AuthenticationService authenticationService = new AuthenticationService(cookbookRepository);
        String newUsername = "New username";

        // ACT
        authenticationService.login(newUsername);

        // ASSERT
        assertThat(authenticationService.getCurrentUsername())
            .isNotNull();
    }

    @Test
    void testLogout() {
        // ARRANGE
        CookbookRepository cookbookRepository = new CookbookRepositoryMock();
        AuthenticationService authenticationService = new AuthenticationService(cookbookRepository);

        // ACT
        authenticationService.logout();

        // ASSERT
        assertThat(authenticationService.getCurrentUsername())
            .isNull();
    }
}