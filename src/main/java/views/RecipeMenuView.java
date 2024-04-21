package views;

import services.AuthenticationService;
import services.RecipeService;
import services.UserService;

public class RecipeMenuView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;

    public RecipeMenuView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
    }

    @Override
    public void display() {
        System.out.println("┌             ┐");
        System.out.println("  RECIPE MENU  ");
        System.out.println("└             ┘");

        int input = getRecipeMenuInput();
        System.out.println();

        if (input == 1) {
            new ShowRecipesView(userService, authenticationService, recipeService).display();
        }
        else if (input == 2) {
            new CreateNewRecipeView(userService, authenticationService, recipeService).display();
        }
        else if (input == 3) {
            authenticationService.logout();
            writeGreenLine("Logged out.");

            new LoginMenuView(userService, authenticationService, recipeService).display();
        }
        else {
            writeYellowLine("Have a nice day.");
        }
    }

    private int getRecipeMenuInput() {
        writeYellowLine("What do you want to do now?");
        System.out.println("1: I want to read a recipe");
        System.out.println("2: I want to create a new recipe");
        System.out.println("3: I want to log out");
        System.out.println("0: Close cookbook");
        System.out.println();
        return getNumberInput(0, 3);
    }
}
