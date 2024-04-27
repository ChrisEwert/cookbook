package cookbook;

import db.UserFileDataHandler;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class CookbookFileRepository implements CookbookRepository {
    private final UserFileDataHandler userFileDataHandler = new UserFileDataHandler();
    private Cookbook cookbook = new Cookbook();

    public String getCurrentUsername() {
        return cookbook.currentUsername();
    }

    public void setCurrentUsername(String newUsername) {
        cookbook = cookbook.changeCurrentUsername(newUsername);
    }

    public LocalDate getCreationDate() {
        return cookbook.dateOfCreation();
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
}