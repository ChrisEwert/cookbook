package cookbook;

import db.UserDataHandler;

import java.util.List;

public class CookbookRepository {
    private final UserDataHandler userDataHandler = new UserDataHandler();
    private static Cookbook cookbook = new Cookbook();

    public void setUser(String user) {
        cookbook = cookbook.changeUser(user);
    }

    public static String getUser() {
        return cookbook.user();
    }

    public void saveUser(User user) {
        userDataHandler.saveUserToDB(user);
    }

    public List<User> getUserList() {
        return userDataHandler.readUsersFromDB();
    }
}