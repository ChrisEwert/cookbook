import cookbook.CookbookRepository;
import services.AuthenticationService;
import services.UserService;
import views.StartView;

public class Main {
    public static void main(String[] args) {
        CookbookRepository cookbookRepository = new CookbookRepository();
        UserService userService = new UserService(cookbookRepository);
        AuthenticationService authenticationService = new AuthenticationService(cookbookRepository);
        new StartView(userService, authenticationService).display();
    }
}