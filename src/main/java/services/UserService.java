package services;

import cookbook.CookbookRepository;
import cookbook.CookbookUser;
import cookbook.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserService {
    private final CookbookRepository cookbookRepository;

    public UserService(CookbookRepository cookbookRepository) {
        this.cookbookRepository = cookbookRepository;
    }

    private Map<String, CookbookUser> getAllUsers() {
        return cookbookRepository.getAllUsers();
    }

    public List<String> getAllUsernames() {
        Set<String> usernames = getAllUsers().keySet();

        return new ArrayList<>(usernames);
    }

    public void createNewUser(String username, String password) {
        CookbookUser user = new CookbookUser(username, password);

        cookbookRepository.createNewUser(username, user);
    }

    public List<String> getBookmarkedRecipeIdsByUsername(String username) {
        Set<String> bookmarkedIds = cookbookRepository.getBookmarkedRecipeIdsByUsername(username);

        return new ArrayList<>(bookmarkedIds);
    }

    public void bookmarkRecipe(String username, Recipe recipe) {
        String recipeId = recipe.id();

        cookbookRepository.addBookmarkedRecipeId(username, recipeId);
    }
}
