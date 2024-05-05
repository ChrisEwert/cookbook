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

        RecipeRating yourRating = ratingService.getRatingByAuthor(recipe.id(), authenticationService.getCurrentUsername());

        if (yourRating == null) {
            writeYellowLine("You have not yet rated this recipe.");
            System.out.println();

            new RecipeView(userService, authenticationService, recipeService, ratingService, recipe).display();
            return;
        }

        System.out.println(yourRating);
        System.out.println();

        writeYellowLine("Do you want to change this rating?");
        printOptions();
        System.out.println();

        int changeRatingInput = getNumberInRange(0, 2);
        System.out.println();

        if (changeRatingInput == 1) {
            new ChangeRatingView(userService, authenticationService, recipeService, ratingService, recipe, yourRating).display();
            return;
        }
        else if (changeRatingInput == 2) {
            writeYellowLine("Are you sure that you want to delete this rating? To delete it, press 1. To go back, press 0");
            int deleteRatingInput = getNumberInRange(0, 1);
            System.out.println();

            if (deleteRatingInput == 1) {
                ratingService.deleteRatingWithId(yourRating.id());

                writeGreenLine("Deleted rating");
                System.out.println();

                new RecipeView(userService, authenticationService, recipeService, ratingService, recipe).display();
                return;
            }
        }

        new RecipeView(userService, authenticationService, recipeService, ratingService, recipe).display();
    }

    private void printOptions() {
        System.out.println("1: Change this rating");
        System.out.println("2: Delete this rating");
        System.out.println("0: Return to recipe");
    }
}
