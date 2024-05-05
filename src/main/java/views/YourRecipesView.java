package views;

import cookbook.Recipe;
import services.AuthenticationService;
import services.RatingService;
import services.RecipeService;
import services.UserService;

import java.util.List;

public class YourRecipesView extends AbstractView {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final RatingService ratingService;

    public YourRecipesView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, RatingService ratingService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.ratingService = ratingService;
    }

    @Override
    public void display() {
        System.out.println("┌              ┐");
        System.out.println("  YOUR RECIPES  ");
        System.out.println("└              ┘");

        String currentUsername = authenticationService.getCurrentUsername();
        List<Recipe> yourRecipes = recipeService.getRecipesByAuthor(currentUsername);

        if (yourRecipes.isEmpty()) {
            writeYellowLine("You have not created any recipes.");
            System.out.println();

            new RecipeMenuView(userService, authenticationService, recipeService, ratingService).display();
            return;
        }

        writeYellowLine("Select the recipe that you want to read");
        printOptions(yourRecipes);
        System.out.println();

        int input = getNumberInRange(0, yourRecipes.size());
        System.out.println();

        if (input == 0) {
            new RecipeMenuView(userService, authenticationService, recipeService, ratingService).display();
            return;
        }

        Recipe selectedRecipe = yourRecipes.get(input - 1);

        new YourSelectedRecipeView(userService, authenticationService, recipeService, ratingService, selectedRecipe).display();
    }

    private void printOptions(List<Recipe> recipes) {
        for (int i=0 ; i<recipes.size() ; i++) {
            System.out.println(i+1 + ": " + recipes.get(i).name());
        }
        System.out.println("0: Go back to recipe menu");
    }
}
