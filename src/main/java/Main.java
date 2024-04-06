import recipe.CookbookRepository;
import services.AuthenticationService;
import services.UserService;
import views.StartView;

public class Main {
    public static void main(String[] args) {
        CookbookRepository cookbookRepository = new CookbookRepository();
        AuthenticationService authenticationService = new AuthenticationService(cookbookRepository);
        UserService userService = new UserService();
        new StartView(userService, authenticationService).display();
    }
}