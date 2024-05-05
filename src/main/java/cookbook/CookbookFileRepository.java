package cookbook;

import db.UserFileDataHandler;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class CookbookFileRepository implements CookbookRepository {
    private final UserFileDataHandler userFileDataHandler;
    private final Cookbook cookbook;

    public CookbookFileRepository() {
        this.userFileDataHandler = new UserFileDataHandler();
        this.cookbook = Cookbook.getInstance();
    }

    public CookbookFileRepository(UserFileDataHandler userFileDataHandler) {
        this.userFileDataHandler = userFileDataHandler;
        this.cookbook = Cookbook.getInstance();
    }

    public String getCurrentUsername() {
        return cookbook.getCurrentUsername();
    }

    public void setCurrentUsername(String newUsername) {
        cookbook.setCurrentUsername(newUsername);
    }

    public LocalDate getCreationDate() {
        return cookbook.getDateOfCreation();
    }

    public Map<String, CookbookUser> getAllUsers() {
        return userFileDataHandler.getAllUsersFromDB();
    }

    public CookbookUser getUserByUsername(String username) {
        return userFileDataHandler.getUserByUsername(username);
    }

    public void createNewUser(CookbookUser user) {
        userFileDataHandler.saveUserToDB(user);
    }

    public Set<String> getBookmarkedRecipeIdsByUsername(String username) {
        CookbookUser user = getUserByUsername(username);

        return user.bookmarkedRecipeIds();
    }

    public void addBookmarkedRecipeId(String username, String recipeId) {
        CookbookUser currentUser = getUserByUsername(username);

        Set<String> bookmarkedRecipeIds = getBookmarkedRecipeIdsByUsername(username);
        bookmarkedRecipeIds.add(recipeId);

        CookbookUser newUser = currentUser.updateBookmarkedRecipeIds(bookmarkedRecipeIds);

        userFileDataHandler.updateUserInDB(username, newUser);
    }

    public void deleteAllUsers() {
        Map<String, CookbookUser> allUsers = getAllUsers();

        for (String user : allUsers.keySet()) {
            userFileDataHandler.deleteUserFromDB(user);
        }

        resetFile();
    }

    private void resetFile() {
        userFileDataHandler.deleteDB();

        userFileDataHandler.createFile(Path.of("db", "users.json"));
    }
}