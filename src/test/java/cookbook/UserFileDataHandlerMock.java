package cookbook;

import db.UserFileDataHandler;

import java.util.HashMap;
import java.util.Map;

public class UserFileDataHandlerMock extends UserFileDataHandler {
    private Map<String, CookbookUser> users = new HashMap<>();

    public Map<String, CookbookUser> getAllUsersFromDB() {
        return users;
    }

    public CookbookUser getUserByUsername(String username) {
        return users.get(username);
    }

    public void saveAllUsersToDB(Map<String, CookbookUser> userList) {
        users = userList;
    }

    public void saveUserToDB(CookbookUser user) {
        users.put(user.username(), user);
    }

    public void updateUserInDB(String username, CookbookUser user) {
        users.put(username, user);
    }
}
