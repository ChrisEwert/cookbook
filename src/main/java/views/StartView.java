package views;

import services.AuthenticationService;
import services.RatingService;
import services.RecipeService;
import services.UserService;

public class StartView extends AbstractView {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final RatingService ratingService;

    public StartView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, RatingService ratingService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.ratingService = ratingService;
    }

    @Override
    public void display() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════╗");
        System.out.println("║                    OPEN COOKBOOK                    ║");
        System.out.println("╚═════════════════════════════════════════════════════╝");
        System.out.println("                                             " + authenticationService.getCookbookCreationDate());

        System.out.println("Hello and welcome to the open cookbook.");
        System.out.println("In this cookbook, users can share recipes.");
        System.out.println();

        writeYellowLine("To start, please log in.");
        System.out.println();

        new LoginMenuView(userService, authenticationService, recipeService, ratingService).display();
    }
}
