package views;

import cookbook.Recipe;
import services.AuthenticationService;
import services.RatingService;
import services.RecipeService;
import services.UserService;

public class RatingView extends AbstractView {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final RatingService ratingService;
    private final Recipe recipe;

    public RatingView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, RatingService ratingService, Recipe recipe) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.ratingService = ratingService;
        this.recipe = recipe;
    }

    @Override
    public void display() {
        System.out.println("┌             ┐");
        System.out.println("  RATE RECIPE  ");
        System.out.println("└             ┘");

        String currentUsername = authenticationService.getCurrentUsername();

        if (recipeService.isAuthor(currentUsername, recipe)) {
            writeYellowLine("You cannot rate your own recipe!");
            System.out.println();

            new RecipeView(userService, authenticationService, recipeService, ratingService, recipe).display();
            return;
        }

        if (ratingService.hasRated(currentUsername, recipe.id())) {
            writeYellowLine("You have already rated this recipe!");
            System.out.println();

            new RecipeView(userService, authenticationService, recipeService, ratingService, recipe).display();
            return;
        }

        createRating();

        Recipe updatedRecipe = recipeService.getRecipeById(recipe.id());

        new RecipeView(userService, authenticationService, recipeService, ratingService, updatedRecipe).display();
    }

    private void createRating() {
        String id = recipe.id();

        String username = authenticationService.getCurrentUsername();

        writeYellowLine("Enter how many stars out of 5 you would give this recipe");
        int stars = getNumberInputMinMax(1, 5);
        System.out.println();

        writeYellowLine("Enter a comment about the rating");
        String comment = getUserInput();
        System.out.println();

        writeYellowLine("Enter the title of the comment");
        String title = getUserInput();
        System.out.println();

        ratingService.addRating(id, username, stars, title, comment);
    }
}
