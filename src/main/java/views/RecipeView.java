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
        System.out.println("1: Bookmark recipe");
        System.out.println("2: Rate recipe");
        System.out.println("0: Exit to recipe menu");
        System.out.println();

        int userInput = getNumberInput(0, 2);
        System.out.println();

        if (userInput == 1) {
            userService.bookmarkRecipe(recipe);
            writeGreenLine("Bookmarked this recipe");
        }
        else if (userInput == 2) {
            // TODO: Rating View
        }

        new RecipeMenuView(userService, authenticationService, recipeService).display();
    }
}
