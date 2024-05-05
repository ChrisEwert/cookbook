package services;

import cookbook.CookbookRepository;
import cookbook.CookbookUser;

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
        this.creationDate = LocalDate.now();
        this.allUsers = new HashMap<>();
        allUsers.put("John", new CookbookUser("John", "pw"));
        allUsers.put("Jane", new CookbookUser("Jane", "pw"));
        this.bookmarkedRecipeIdsByUsername = new HashMap<>();
        bookmarkedRecipeIdsByUsername.put("John", new HashSet<>());
        bookmarkedRecipeIdsByUsername.put("Jane", new HashSet<>());
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
    public void createNewUser(String username, CookbookUser user) {
        allUsers.put(username, user);
        bookmarkedRecipeIdsByUsername.put(username, new HashSet<>());
    }

    @Override
    public Set<String> getBookmarkedRecipeIdsByUsername(String username) {
        return bookmarkedRecipeIdsByUsername.get(username);
    }

    @Override
    public void addBookmarkedRecipeId(String username, String recipeId) {
        Set<String> bookmarkedRecipeIds = bookmarkedRecipeIdsByUsername.get(username);
        bookmarkedRecipeIds.add(recipeId);
        bookmarkedRecipeIdsByUsername.put(username, bookmarkedRecipeIds);
    }
}
