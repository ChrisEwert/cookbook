package views;

import cookbook.Recipe;
import services.AuthenticationService;
import services.RecipeService;
import services.UserService;

import java.util.List;

public class ShowRecipeListView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;

    public ShowRecipeListView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
    }

    @Override
    public void display() {
        System.out.println("┌             ┐");
        System.out.println("  RECIPE LIST  ");
        System.out.println("└             ┘");

        if (recipeService.noRecipesExist()) {
            writeYellowLine("There are no recipes as of yet!");
            System.out.println();

            new RecipeMenuView(userService, authenticationService, recipeService).display();
            return;
        }

        writeYellowLine("Here is a list of all recipes");
        printOptions();
        System.out.println();                                                                                           // TODO: Filter mechanic

        writeYellowLine("Enter the number of a recipe to read it or type 0 to go back to the recipe menu");
        int recipeIndex = getNumberInputMinMax(0, recipeService.recipeCount());
        System.out.println();

        if (recipeIndex == 0) {
            new RecipeMenuView(userService, authenticationService, recipeService).display();
            return;
        }

        String recipeId = recipeService.getRecipeIdByIndex(recipeIndex);
        Recipe recipe = recipeService.getRecipeById(recipeId);

        new RecipeView(userService, authenticationService, recipeService, recipe).display();
    }

    private void printOptions() {
        List<String> recipeData = recipeService.getAllRecipeSelectData();
        for (int i = 0; i < recipeData.size(); i++) {
            System.out.println(i+1 + ": " + recipeData.get(i));
        }
        System.out.println("0: Go back");
    }
}
