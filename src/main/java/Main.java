import services.UserService;
import views.StartView;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        new StartView(userService).display();
    }
}