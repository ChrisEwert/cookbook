package views;

import cookbook.Recipe;
import services.AuthenticationService;
import services.RecipeService;
import services.UserService;

public class RecipeView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final Recipe recipe;

    public RecipeView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, Recipe recipe) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.recipe = recipe;
    }

    @Override
    public void display() {
        System.out.println(recipe);
        System.out.println();

        writeYellowLine("What do you want to do?");
        int userInput = getMenuInput();
        System.out.println();

        if (userInput == 1) {
            userService.bookmarkRecipe(recipe);
            writeGreenLine("Bookmarked this recipe");
            System.out.println();

            new RecipeView(userService, authenticationService, recipeService, recipe).display();
            return;
        }
        else if (userInput == 2) {
            new RatingView(userService, authenticationService, recipeService, recipe).display();
            return;
        }
        else if (userInput == 3) {
            // TODO
            return;
        }

        new RecipeMenuView(userService, authenticationService, recipeService).display();
    }

    private int getMenuInput() {
        System.out.println("1: Bookmark recipe");
        System.out.println("2: Rate recipe");
        System.out.println("3: Read comments");
        System.out.println("0: Exit to recipe menu");
        System.out.println();

        return getNumberInputMinMax(0, 2);
    }
}
