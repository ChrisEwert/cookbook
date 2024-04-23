package cookbook;

import db.UserFileDataHandler;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class CookbookFileRepository implements CookbookRepository {
    private final UserFileDataHandler userFileDataHandler = new UserFileDataHandler();
    private Cookbook cookbook = new Cookbook();

    public String getUsername() {
        return cookbook.username();
    }

    public void setUsername(String username) {
        cookbook = cookbook.changeUser(username);
    }

    public LocalDate getCreationDate() {
        return cookbook.dateOfCreation();
    }

    public void saveUser(CookbookUser user) {
        userFileDataHandler.saveUserToDB(user);
    }

    public List<CookbookUser> getUserList() {
        return userFileDataHandler.readUsersFromDB();
    }

    public void bookmarkRecipeById(String id) {
        userFileDataHandler.bookmarkRecipeById(getUsername(), id);
    }

    public Set<String> getBookmarkedRecipeIds() {
        return userFileDataHandler.getBookmarkIds(getUsername());
    }
}