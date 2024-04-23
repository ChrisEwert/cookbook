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

        List<RecipeRating> ratings = recipeService.getRecipeRatingsByRecipeId(recipe.id());                             // TODO: Only show 3 ratings. User has to ask for more if he wants more
        ratings.forEach(System.out::println);
        System.out.println();

        new RecipeView(userService, authenticationService, recipeService, recipe).display();
    }
}
