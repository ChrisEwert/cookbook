package views;

import services.AuthenticationService;
import services.RatingService;
import services.RecipeService;
import services.UserService;

public class RecipeMenuView extends AbstractView {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final RatingService ratingService;

    public RecipeMenuView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, RatingService ratingService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.ratingService = ratingService;
    }

    @Override
    public void display() {
        System.out.println("┌             ┐");
        System.out.println("  RECIPE MENU  ");
        System.out.println("└             ┘");

        writeYellowLine("What do you want to do now?");
        printOptions();
        System.out.println();

        int input = getNumberInRange(0, 5);
        System.out.println();

        if (input == 1) {
            new ShowRecipeListView(userService, authenticationService, recipeService, ratingService).display();
            return;
        }
        else if (input == 2) {
            new CreateNewRecipeView(userService, authenticationService, recipeService, ratingService).display();
            return;
        }
        else if (input == 3) {
            new BookmarkView(userService, authenticationService, recipeService, ratingService).display();
            return;
        }
        else if (input == 4) {
            new YourRecipesView(userService, authenticationService, recipeService, ratingService).display();
            return;
        }
        else if (input == 5) {
            authenticationService.logout();

            writeGreenLine("Logged out.");
            System.out.println();

            new LoginMenuView(userService, authenticationService, recipeService, ratingService).display();
            return;
        }

        writeYellowLine("Have a nice day.");
    }

    private void printOptions() {
        System.out.println("1: I want to read a recipe");
        System.out.println("2: I want to create a new recipe");
        System.out.println("3: I want to see my bookmarked recipes");
        System.out.println("4: I want to see my recipes");
        System.out.println("5: I want to log out");
        System.out.println("0: Close cookbook");
    }
}
