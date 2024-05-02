package views;

import cookbook.Recipe;
import cookbook.RecipeRating;
import services.AuthenticationService;
import services.RatingService;
import services.RecipeService;
import services.UserService;

public class YourSelectedRatingView extends AbstractView {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final RatingService ratingService;
    private final Recipe recipe;

    public YourSelectedRatingView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, RatingService ratingService, Recipe recipe) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.ratingService = ratingService;
        this.recipe = recipe;
    }

    @Override
    public void display() {
        System.out.println("┌             ┐");
        System.out.println("  YOUR RATING  ");
        System.out.println("└             ┘");

        RecipeRating rating = ratingService.getRatingByName(recipe.id(), authenticationService.getCurrentUsername());

        if (rating == null) {
            writeYellowLine("You have not yet rated this recipe.");
            System.out.println();

            new RecipeView(userService, authenticationService, recipeService, ratingService, recipe).display();
            return;
        }

        System.out.println(rating);
        System.out.println();

        writeYellowLine("Do you want to change this rating?");
        printOptions();
        System.out.println();

        int changeRatingInput = getNumberInputMinMax(0, 2);
        System.out.println();

        if (changeRatingInput == 1) {
            // TODO: Change rating
            System.out.println("Change rating");
        }
        else if (changeRatingInput == 2) {
            // TODO: Delete rating
            System.out.println("Delete rating");
        }

        new RecipeView(userService, authenticationService, recipeService, ratingService, recipe).display();
    }

    private void printOptions() {
        System.out.println("1: Change this rating");
        System.out.println("2: Delete this rating");
        System.out.println("0: Return to recipe");
    }
}
