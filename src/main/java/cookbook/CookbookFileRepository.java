package cookbook;

import db.UserFileDataHandler;

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

    public void createNewUser(String username, CookbookUser user) {
        userFileDataHandler.saveUserToDB(username, user);
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
}