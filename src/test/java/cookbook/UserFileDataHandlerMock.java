package cookbook;

import db.UserFileDataHandler;

import java.util.HashMap;
import java.util.Map;

public class UserFileDataHandlerMock extends UserFileDataHandler {
    private Map<String, CookbookUser> users = new HashMap<>();

    @Override
    public Map<String, CookbookUser> getAllUsersFromDB() {
        return users;
    }

    @Override
    public CookbookUser getUserByUsername(String username) {
        return users.get(username);
    }

    @Override
    public void saveAllUsersToDB(Map<String, CookbookUser> userList) {
        users = userList;
    }

    @Override
    public void saveUserToDB(String username, CookbookUser user) {
        users.put(username, user);
    }

    @Override
    public void updateUserInDB(String username, CookbookUser user) {
        users.put(username, user);
    }
}
