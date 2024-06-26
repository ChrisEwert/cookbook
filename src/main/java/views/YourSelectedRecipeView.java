package views;

import cookbook.Recipe;
import services.AuthenticationService;
import services.RatingService;
import services.RecipeService;
import services.UserService;

public class YourSelectedRecipeView extends AbstractView {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final RatingService ratingService;
    private final Recipe recipe;

    public YourSelectedRecipeView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, RatingService ratingService, Recipe recipe) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.ratingService = ratingService;
        this.recipe = recipe;
    }

    @Override
    public void display() {
        System.out.println("┌                 ┐");
        System.out.println("  SELECTED RECIPE  ");
        System.out.println("└                 ┘");

        System.out.println(recipe);
        System.out.println();

        writeYellowLine("Do you want to change the recipe?");
        printOptions();
        System.out.println();

        int input = getNumberInRange(0, 2);
        System.out.println();

        if (input == 1) {
            new ChangeYourRecipeView(userService, authenticationService, recipeService, ratingService, recipe).display();
            return;
        }
        else if (input == 2) {
            writeYellowLine("Are you sure that you want to delete the recipe " + recipe.name() + "? Type 1 to delete it. Type 0 to go back");
            int deleteInput = getNumberInRange(0, 1);
            System.out.println();

            if (deleteInput == 1) {
                recipeService.deleteRecipe(recipe.id());
                ratingService.deleteRatingsOfRecipeWithId(recipe.id());

                writeGreenLine("Deleted the recipe");
                System.out.println();

                new YourRecipesView(userService, authenticationService, recipeService, ratingService).display();
            }
        }

        new RecipeMenuView(userService, authenticationService, recipeService, ratingService).display();
    }

    private void printOptions() {
        System.out.println("1: Change recipe");
        System.out.println("2: Delete recipe");
        System.out.println("0: Go back to recipe menu");
    }
}
