package services;

import cookbook.CookbookRepository;
import cookbook.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final CookbookRepository cookbookRepository;

    public UserService(CookbookRepository cookbookRepository) {
        this.cookbookRepository = cookbookRepository;
    }

    public void createUser(String username, String password) {
        User user = new User(username, password);
        cookbookRepository.saveUser(user);
    }

    public List<String> getUsernames() {
        List<String> usernames = new ArrayList<>();
        cookbookRepository.getUserList().forEach(user -> usernames.add(user.username()));
        return usernames;
    }
}
