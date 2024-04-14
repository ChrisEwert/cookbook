import cookbook.CookbookRepository;
import services.UserService;
import views.StartView;

public class Main {
    public static void main(String[] args) {
        CookbookRepository cookbookRepository = new CookbookRepository();
        UserService userService = new UserService(cookbookRepository);
        new StartView(userService).display();
    }
}