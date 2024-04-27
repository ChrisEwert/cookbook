package views;

import cookbook.Recipe;
import cookbook.RecipeRating;
import services.AuthenticationService;
import services.RecipeService;
import services.UserService;

import java.util.List;

public class ShowRatingsView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final Recipe recipe;

    public ShowRatingsView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, Recipe recipe) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.recipe = recipe;
    }

    @Override
    public void display() {
        System.out.println("┌         ┐");
        System.out.println("  RATINGS  ");
        System.out.println("└         ┘");

        if (recipeService.hasRatings(recipe.id())) {
            writeYellowLine("This recipe has not been rated yet.");
            System.out.println();

            new RecipeView(userService, authenticationService, recipeService, recipe).display();
            return;
        }

        List<RecipeRating> ratings = recipeService.getRecipeRatingsByRecipeId(recipe.id());
        ratings.forEach(System.out::println);
        System.out.println();

        writeYellowLine("What do you want to do now?");
        printOptions();
        System.out.println();

        int input = getNumberInputMinMax(0, 1);
        System.out.println();

        if (input == 1) {
            // TODO
        }

        new RecipeView(userService, authenticationService, recipeService, recipe).display();
    }

    private void printOptions() {
        System.out.println("1: Show only my rating");
        System.out.println("0: Return to recipe");
    }
}