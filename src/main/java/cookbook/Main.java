package cookbook;

import cookbook.services.UserService;
import cookbook.views.StartView;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        new StartView(userService).display();
    }
}