package views;

import cookbook.Recipe;
import services.AuthenticationService;
import services.RatingService;
import services.RecipeService;
import services.UserService;

import java.util.Map;

public class ShowRecipeListView extends AbstractView {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final RatingService ratingService;

    public ShowRecipeListView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, RatingService ratingService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.ratingService = ratingService;
    }

    @Override
    public void display() {
        System.out.println("┌             ┐");
        System.out.println("  RECIPE LIST  ");
        System.out.println("└             ┘");

        Map<String, Recipe> allRecipes = recipeService.getAllRecipes();

        if (allRecipes.isEmpty()) {
            writeYellowLine("There are no recipes yet!");
            System.out.println();

            new RecipeMenuView(userService, authenticationService, recipeService, ratingService).display();
            return;
        }

        writeYellowLine("Here is a list of all recipes");
        printOptions(allRecipes);
        System.out.println();                                                                                           // TODO: Filter mechanic

        writeYellowLine("Enter the number of a recipe to read it or type 0 to go back to the recipe menu");
        int recipeIndex = getNumberInRange(0, allRecipes.size());
        System.out.println();

        if (recipeIndex == 0) {
            new RecipeMenuView(userService, authenticationService, recipeService, ratingService).display();
            return;
        }

        String recipeId = recipeService.getRecipeIdByIndex(recipeIndex);
        Recipe recipe = recipeService.getRecipeById(recipeId);

        new RecipeView(userService, authenticationService, recipeService, ratingService, recipe).display();
    }

    private void printOptions(Map<String, Recipe> allRecipes) {
        int counter = 1;
        for (Recipe recipe : allRecipes.values()) {
            String data = recipeService.formatRecipeToSelectData(recipe);
            System.out.println(counter + ": " + data);
            counter++;
        }
        System.out.println("0: Go back");
    }
}
