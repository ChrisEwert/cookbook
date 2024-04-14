package cookbook;

import db.UserDataHandler;

import java.util.List;

public class CookbookRepository {
    private Cookbook cookbook = new Cookbook();

    public void setUser(String user) {
        cookbook = cookbook.changeUser(user);
    }
//
//    public String getUser() {
//        return cookbook.user();
//    }
//
//    public void setRecipes(Map<Long, Recipe> recipes) {
//        cookbook = new Cookbook(
//            cookbook.dateOfCreation(),
//            recipes,
//            cookbook.user()
//        );
//    }

    private final UserDataHandler userDataHandler = new UserDataHandler();

    public void saveUser(User user) {
        userDataHandler.saveUserToDB(user);
    }

    public List<User> getUserList() {
        return userDataHandler.readUsersFromFile();
    }
}