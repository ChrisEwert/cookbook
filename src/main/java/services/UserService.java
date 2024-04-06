package services;

import db.UserDataHandler;
import recipe.CookbookRepository;

import java.util.List;

public class UserService {
    private final List<String> users = new UserDataHandler().getUserList();

    public int getUserCount() {
        return users.size();
    }

    public void addNewUserToDB(String newUser) {
        users.add(newUser);
        new UserDataHandler().saveUserListToDB(users);
    }

    public String getUser(int id) {
        return users.get(id);
    }

    public boolean containsUser(String user) {
        return users.contains(user);
    }

    public void login(String user) {
        CookbookRepository.setUser(user);
    }

    public void logout() {
        CookbookRepository.setUser("Default User");
    }
}
