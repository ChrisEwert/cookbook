import cookbook.CookbookRepository;
import cookbook.RecipeRepository;
import services.AuthenticationService;
import services.RecipeService;
import services.UserService;
import views.StartView;

public class Main {
    public static void main(String[] args) {
        CookbookRepository cookbookRepository = new CookbookRepository();
        RecipeRepository recipeRepository = new RecipeRepository();

        UserService userService = new UserService(cookbookRepository);
        AuthenticationService authenticationService = new AuthenticationService(cookbookRepository);
        RecipeService recipeService = new RecipeService(recipeRepository);

        new StartView(userService, authenticationService, recipeService).display();
    }
}