package views;

import cookbook.Recipe;
import cookbook.RecipeRating;
import services.AuthenticationService;
import services.RatingService;
import services.RecipeService;
import services.UserService;

import java.util.List;

public class ShowRatingsView extends AbstractView {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final RatingService ratingService;
    private final Recipe recipe;

    public ShowRatingsView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, RatingService ratingService, Recipe recipe) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.ratingService = ratingService;
        this.recipe = recipe;
    }

    @Override
    public void display() {
        System.out.println("┌         ┐");
        System.out.println("  RATINGS  ");
        System.out.println("└         ┘");

        List<RecipeRating> ratings = ratingService.getRatingsByRecipeId(recipe.id());

        if (ratings.isEmpty()) {
            writeYellowLine("This recipe has not been rated yet.");
            System.out.println();

            new RecipeView(userService, authenticationService, recipeService, ratingService, recipe).display();
            return;
        }

        printRatings(ratings);
        System.out.println();

        writeYellowLine("What do you want to do now?");
        printOptions();
        System.out.println();

        int input = getNumberInputMinMax(0, 1);
        System.out.println();

        if (input == 1) {
            // TODO show only my ratings
        }

        new RecipeView(userService, authenticationService, recipeService, ratingService, recipe).display();
    }

    private void printRatings(List<RecipeRating> ratings) {
        for (RecipeRating rating : ratings) {
            System.out.println(rating);
        }
    }

    private void printOptions() {
        System.out.println("1: Show only my rating");
        System.out.println("0: Return to recipe");
    }
}