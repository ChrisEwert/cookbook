package services;

import cookbook.CookbookRepository;
import cookbook.CookbookUser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

public class AuthenticationService {
    private final CookbookRepository cookbookRepository;

    public AuthenticationService(CookbookRepository cookbookRepository) {
        this.cookbookRepository = cookbookRepository;
    }

    private Map<String, CookbookUser> getAllUsers() {
        return cookbookRepository.getAllUsers();
    }

    public String getCurrentUsername() {
        return cookbookRepository.getCurrentUsername();
    }

    public String getCookbookCreationDate() {
        LocalDate date = cookbookRepository.getCreationDate();

        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public boolean userExists(String username) {
        Map<String, CookbookUser> users = getAllUsers();

        return users.containsKey(username);
    }

    public boolean credentialsMatch(String username, String password) {
        CookbookUser user = cookbookRepository.getUserByUsername(username);

        return Objects.equals(user.password(), password);
    }

    public void login(String username) {
        cookbookRepository.setCurrentUsername(username);
    }

    public void logout() {
        cookbookRepository.setCurrentUsername(null);
    }
}
