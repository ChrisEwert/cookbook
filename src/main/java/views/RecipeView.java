package views;

import cookbook.Recipe;
import services.AuthenticationService;
import services.RatingService;
import services.RecipeService;
import services.UserService;

public class RecipeView extends AbstractView {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final RatingService ratingService;
    private final Recipe recipe;

    public RecipeView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, RatingService ratingService, Recipe recipe) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.ratingService = ratingService;
        this.recipe = recipe;
    }

    @Override
    public void display() {
        System.out.println(recipe);
        System.out.println();

        writeYellowLine("What do you want to do?");
        printOptions();
        System.out.println();

        int userInput = getNumberInputMinMax(0, 3);
        System.out.println();

        if (userInput == 1) {
            userService.bookmarkRecipe(authenticationService.getCurrentUsername(), recipe);

            writeGreenLine("Bookmarked this recipe");
            System.out.println();

            new RecipeView(userService, authenticationService, recipeService, ratingService, recipe).display();
            return;
        }
        else if (userInput == 2) {
            new RatingView(userService, authenticationService, recipeService, ratingService, recipe).display();
            return;
        }
        else if (userInput == 3) {
            new ShowRatingsView(userService, authenticationService, recipeService, ratingService, recipe).display();
            return;
        }

        new RecipeMenuView(userService, authenticationService, recipeService, ratingService).display();
    }

    private void printOptions() {
        System.out.println("1: Bookmark recipe");
        System.out.println("2: Rate recipe");
        System.out.println("3: Read ratings");
        System.out.println("0: Exit to recipe menu");
    }
}
