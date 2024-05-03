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
        String username_that_does_exist = "John";
        String username_that_does_not_exist = "Bob";

        // ACT
        boolean user_does_exist = authenticationService.userExists(username_that_does_exist);
        boolean user_does_not_exist = authenticationService.userExists(username_that_does_not_exist);

        // ASSERT
        assertThat(user_does_exist)
            .isEqualTo(true);
        assertThat(user_does_not_exist)
            .isEqualTo(false);
    }

    @Test
    void testCredentialsMatch() {
        // ARRANGE
        CookbookRepository cookbookRepository = new CookbookRepositoryMock();
        AuthenticationService authenticationService = new AuthenticationService(cookbookRepository);
        CookbookUser user_with_right_password = new CookbookUser("John", "pw");
        CookbookUser user_with_wrong_password = new CookbookUser("John", "password");

        // ACT
        boolean password_is_right = authenticationService.credentialsMatch(
            user_with_right_password.username(),
            user_with_right_password.password()
        );
        boolean password_is_wrong = authenticationService.credentialsMatch(
            user_with_wrong_password.username(),
            user_with_wrong_password.password()
        );

        // ARRANGE
        assertThat(password_is_right)
            .isEqualTo(true);
        assertThat(password_is_wrong)
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