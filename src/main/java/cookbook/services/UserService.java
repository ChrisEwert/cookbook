package cookbook.services;

import cookbook.db.UserDataHandler;

import java.util.List;

public class UserService {
    private final List<String> users = new UserDataHandler().getUsers();

    public int getUserCount() {
        return users.size();
    }

    public void addNewUser(String newUser) {
        users.add(newUser);
        new UserDataHandler().saveUsersToFile(users);
    }

    public String getUser(int id) {
        return users.get(id);
    }

    public boolean containsUser(String user) {
        return users.contains(user);
    }
}
