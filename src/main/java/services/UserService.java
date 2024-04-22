package services;

import cookbook.CookbookRepository;
import cookbook.CookbookUser;
import cookbook.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserService {
    private final CookbookRepository cookbookRepository;

    public UserService(CookbookRepository cookbookRepository) {
        this.cookbookRepository = cookbookRepository;
    }

    public void createUser(String username, String password) {
        CookbookUser user = new CookbookUser(username, password, Set.of());
        cookbookRepository.saveUser(user);
    }

    public List<String> getUsernames() {
        List<String> usernames = new ArrayList<>();
        cookbookRepository.getUserList().forEach(user -> usernames.add(user.username()));
        return usernames;
    }

    public List<String> getBookmarkedIds() {
        return new ArrayList<>(cookbookRepository.getBookmarkedRecipeIds());
    }

    public void bookmarkRecipe(Recipe recipe) {
        String id = recipe.id();
        cookbookRepository.bookmarkRecipeById(id);
    }
}
