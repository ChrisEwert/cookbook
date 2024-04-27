package services;

import cookbook.CookbookRepository;
import cookbook.CookbookUser;
import cookbook.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserService {
    private final CookbookRepository cookbookRepository;

    public UserService(CookbookRepository cookbookRepository) {
        this.cookbookRepository = cookbookRepository;
    }

    private Map<String, CookbookUser> getAllUsers() {
        return cookbookRepository.getAllUsers();
    }

    public List<String> getAllUsernames() {
        return new ArrayList<>(getAllUsers().keySet());
    }

    public String getUsernameByIndex(int index) {
        return getAllUsernames().get(index);
    }

    public int getUserCount() {
        return getAllUsers().size();
    }

    public boolean noUsersExist() {
        return getUserCount() == 0;
    }

    public void createNewUser(String username, String password) {
        CookbookUser user = new CookbookUser(username, password);
        cookbookRepository.createNewUser(user);
    }

    public List<String> getBookmarkedRecipeIdsByUsername(String username) {
        return new ArrayList<>(cookbookRepository.getBookmarkedRecipeIdsByUsername(username));
    }

    public void bookmarkRecipe(String username, Recipe recipe) {
        String recipeId = recipe.id();
        cookbookRepository.addBookmarkedRecipeId(username, recipeId);
    }
}
