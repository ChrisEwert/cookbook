package views;

import cookbook.Recipe;
import cookbook.RecipeRating;
import services.AuthenticationService;
import services.RatingService;
import services.RecipeService;
import services.UserService;

import java.util.Objects;

public class ChangeRatingView extends AbstractView {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final RatingService ratingService;
    private final Recipe recipe;
    private final RecipeRating rating;

    public ChangeRatingView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, RatingService ratingService, Recipe recipe, RecipeRating rating) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.ratingService = ratingService;
        this.recipe = recipe;
        this.rating = rating;
    }

    @Override
    public void display() {
        System.out.println("┌               ┐");
        System.out.println("  CHANGE RATING  ");
        System.out.println("└               ┘");

        String ratingId = rating.id();

        String author = authenticationService.getCurrentUsername();

        int stars = getNewStars();
        System.out.println();

        String title = getNewTitle();
        System.out.println();

        String comment = getNewComment();
        System.out.println();

        ratingService.updateExistingRating(ratingId, author, stars, title, comment);

        new ShowRatingsView(userService, authenticationService, recipeService, ratingService, recipe).display();
    }

    private int getNewStars() {
        writeYellowLine("Current stars of the recipe");
        System.out.println(rating.stars());

        writeYellowLine("Enter a number between 1 and 5 to change the number of stars of the rating or type 0 to leave it as it is");
        int newCount = getNumberInRange(0, 5);

        if (Objects.equals(newCount, 0)) {
            newCount = rating.stars();
        }

        return newCount;
    }

    private String getNewTitle() {
        writeYellowLine("Current title of the rating");
        System.out.println(rating.title());

        writeYellowLine("Enter the new name of the recipe or type 0 to leave it as it is");
        String newTitle = getUserInput();

        if (Objects.equals(newTitle, "0")) {
            newTitle = rating.title();
        }

        return newTitle;
    }

    private String getNewComment() {
        writeYellowLine("Current comment of the recipe");
        System.out.println(rating.comment());

        writeYellowLine("Enter the new comment of the recipe or type 0 to leave it as it is");
        String newComment = getUserInput();

        if (Objects.equals(newComment, "0")) {
            newComment = rating.comment();
        }

        return newComment;
    }
}
