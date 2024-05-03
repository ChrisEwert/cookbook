package cookbook;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CookbookRepositoryMock implements CookbookRepository {
    private String currentUsername;
    private final LocalDate creationDate;
    private final Map<String, CookbookUser> allUsers;
    private final Map<String, Set<String>> bookmarkedRecipeIdsByUsername;

    public CookbookRepositoryMock() {
        this.currentUsername = null;
        this.creationDate = null;
        this.allUsers = new HashMap<>();
        this.bookmarkedRecipeIdsByUsername = new HashMap<>();
    }

    @Override
    public String getCurrentUsername() {
        return currentUsername;
    }

    @Override
    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    @Override
    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public Map<String, CookbookUser> getAllUsers() {
        return allUsers;
    }

    @Override
    public CookbookUser getUserByUsername(String username) {
        return allUsers.get(username);
    }

    @Override
    public void createNewUser(CookbookUser user) {
        allUsers.put(user.username(), user);
    }

    @Override
    public Set<String> getBookmarkedRecipeIdsByUsername(String username) {
        return bookmarkedRecipeIdsByUsername.getOrDefault(username, new HashSet<>());
    }

    @Override
    public void addBookmarkedRecipeId(String username, String recipeId) {
        Set<String> bookmarkedRecipeIds = bookmarkedRecipeIdsByUsername.getOrDefault(username, new HashSet<>());
        bookmarkedRecipeIds.add(recipeId);
        bookmarkedRecipeIdsByUsername.put(username, bookmarkedRecipeIds);
    }
}
