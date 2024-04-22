package services;

import cookbook.CookbookRepository;
import cookbook.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AuthenticationService {
    private final CookbookRepository cookbookRepository;

    public AuthenticationService(CookbookRepository cookbookRepository) {
        this.cookbookRepository = cookbookRepository;
    }

    public String getCookbookCreationDate() {
        LocalDate date = cookbookRepository.getCreationDate();
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public boolean credentialsMatch(String username, String password) {
        List<User> userList = cookbookRepository.getUserList();
        for (User user : userList) {
            if (user.username().equals(username) && user.password().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void login(String username) {
        cookbookRepository.setUsername(username);
    }

    public void logout() {
        cookbookRepository.setUsername(null);
    }

    public boolean containsUser(String username) {
        for (User user : cookbookRepository.getUserList()) {
            if (user.username().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
