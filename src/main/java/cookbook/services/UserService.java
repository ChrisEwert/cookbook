package cookbook.services;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<String> users = new ArrayList<>(List.of("Karl", "Anna", "Tom"));

    public int getUserCount() {
        return users.size();
    }

    public void addNewUser(String newUser) {
        users.add(newUser);
    }

    public String getUser(int id) {
        return users.get(id);
    }

    public boolean containsUser(String user) {
        return users.contains(user);
    }
}
