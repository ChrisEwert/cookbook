package views;

import services.AuthenticationService;
import services.RecipeService;
import services.UserService;

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

        if (recipeService.noRecipesExist()) {
            writeYellowLine("There are no recipes as of yet!");
            System.out.println();

            new RecipeMenuView(userService, authenticationService, recipeService).display();
            return;
        }

        writeYellowLine("Here is a list of all the recipes");
        listRecipes();
        System.out.println("0: Go back");
        System.out.println();

        writeYellowLine("Enter the number of the recipe to read it or type 0 to leave");
        int recipeIndex = getNumberInput(0, recipeService.recipeCount());
        System.out.println();

        if (recipeIndex > 0) {
            System.out.println(recipeService.getRecipeByIndex(recipeIndex - 1));
            System.out.println();
        }

        new RecipeMenuView(userService, authenticationService, recipeService).display();
    }

    private void listRecipes() {
        for (int i = 0; i < recipeService.recipeCount(); i++) {
            System.out.println(i+1 + ": " + recipeService.getRecipeTitle(i));
        }
    }
}
