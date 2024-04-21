package views;

import cookbook.Recipe;
import services.AuthenticationService;
import services.RecipeService;
import services.UserService;

import java.util.List;

public class ShowRecipesView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;

    public ShowRecipesView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
    }

    @Override
    public void display() {
        System.out.println("┌             ┐");
        System.out.println("  RECIPE LIST  ");
        System.out.println("└             ┘");

        List<Recipe> recipeList = recipeService.getAllRecipes();

        if (recipeList.isEmpty()) {
            writeYellowLine("There are no recipes as of yet!");
            System.out.println();
            new RecipeMenuView(userService, authenticationService, recipeService).display();
            return;
        }

        writeYellowLine("Here is a list of all the recipes");
        listRecipes(recipeList);
        System.out.println();

        writeYellowLine("Which recipe would you like to read?");
        int recipeIndex = getNumberInput(0, recipeList.size() - 1);
        System.out.println(recipeList.get(recipeIndex));
        System.out.println();

        new RecipeMenuView(userService, authenticationService, recipeService).display();
    }

    private void listRecipes(List<Recipe> recipes) {
        for (int i = 0; i < recipes.size(); i++) {
            System.out.println(i + ": " + recipes.get(i).name().toUpperCase() + " by " + recipes.get(i).author());
        }
    }
}
