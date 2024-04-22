package views;

import cookbook.Recipe;
import services.AuthenticationService;
import services.RecipeService;
import services.UserService;

import java.util.Objects;

public class RatingView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final Recipe recipe;

    public RatingView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, Recipe recipe) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.recipe = recipe;
    }

    @Override
    public void display() {
        System.out.println("┌             ┐");
        System.out.println("  RATE RECIPE  ");
        System.out.println("└             ┘");

        if (Objects.equals(recipe.author(), authenticationService.getCurrentUsername())) {
            writeYellowLine("You cannot rate your own recipe!");
            System.out.println();

            new RecipeMenuView(userService, authenticationService, recipeService).display();
            return;
        }

        writeYellowLine("Enter how many stars out of 5 you would give this recipe");
        int stars = getNumberInputMinMax(0, 5);

        recipeService.addRating(recipe, stars);
        System.out.println();

        new RecipeMenuView(userService, authenticationService, recipeService).display();
    }
}
