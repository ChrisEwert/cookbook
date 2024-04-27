package views;

import cookbook.Recipe;
import services.AuthenticationService;
import services.RecipeService;
import services.UserService;

import java.util.List;

public class BookmarkView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;

    public BookmarkView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
    }

    @Override
    public void display() {
        System.out.println("┌                    ┐");
        System.out.println("  BOOKMARKED RECIPES  ");
        System.out.println("└                    ┘");

        List<String> ids = userService.getBookmarkedRecipeIdsByUsername(authenticationService.getCurrentUsername());

        if (ids.isEmpty()) {
            writeYellowLine("There are no bookmarked recipes!");
            System.out.println();

            new RecipeMenuView(userService, authenticationService, recipeService).display();
            return;
        }

        writeYellowLine("Select the number of the recipe that you want to read or type 0 to go back to the recipe menu");
        printOptions(ids);
        System.out.println();

        int input = getNumberInputMinMax(0, ids.size());
        System.out.println();

        if (input == 0) {
            new RecipeMenuView(userService, authenticationService, recipeService).display();
            return;
        }

        String id = ids.get(input - 1);
        Recipe selectedRecipe = recipeService.getRecipeById(id);
        System.out.println(selectedRecipe);
        System.out.println();

        new RecipeMenuView(userService, authenticationService, recipeService).display();
    }

    private void printOptions(List<String> idList) {
        for (int i = 0; i < idList.size(); i++) {
            System.out.println(i+1 + ": " + recipeService.getRecipeSelectDataById(idList.get(i)));
        }
        System.out.println("0: Go back");
    }
}
