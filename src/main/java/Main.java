import cookbook.*;
import services.AuthenticationService;
import services.RecipeService;
import services.UserService;
import views.StartView;

public class Main {
    public static void main(String[] args) {
        CookbookRepository cookbookRepository = new CookbookFileRepository();
        RecipeRepository recipeRepository = new RecipeFileRepository();

        UserService userService = new UserService(cookbookRepository);
        AuthenticationService authenticationService = new AuthenticationService(cookbookRepository);
        RecipeService recipeService = new RecipeService(recipeRepository);

        new StartView(userService, authenticationService, recipeService).display();
    }
}