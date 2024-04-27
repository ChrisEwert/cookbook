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

    public String getUsernameByIndex(int index) {
        List<String> usernames = getAllUsernames();

        return usernames.get(index);
    }

    public int getUserCount() {
        Map<String, CookbookUser> users = getAllUsers();

        return users.size();
    }

    public boolean noUsersExist() {
        return getUserCount() == 0;
    }

    public void createNewUser(String username, String password) {
        CookbookUser user = new CookbookUser(username, password);

        cookbookRepository.createNewUser(user);
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
