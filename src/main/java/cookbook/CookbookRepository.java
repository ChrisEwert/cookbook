package cookbook;

import db.UserDataHandler;

import java.util.List;
import java.util.Set;

public class CookbookRepository {
    private final UserDataHandler userDataHandler = new UserDataHandler();
    private static Cookbook cookbook = new Cookbook();

    public void setUsername(String username) {
        cookbook = cookbook.changeUser(username);
    }

    public static String getUsername() {
        return cookbook.username();
    }

    public void saveUser(User user) {
        userDataHandler.saveUserToDB(user);
    }

    public List<User> getUserList() {
        return userDataHandler.readUsersFromDB();
    }

    public void bookmarkRecipeById(String id) {
        userDataHandler.bookmarkRecipeById(getUsername(), id);
    }

    public Set<String> getBookmarkedRecipeIds() {
        return userDataHandler.getBookmarkIds(getUsername());
    }
}