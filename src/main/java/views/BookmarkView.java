package views;

import cookbook.Recipe;
import services.AuthenticationService;
import services.RatingService;
import services.RecipeService;
import services.UserService;

import java.util.List;

public class BookmarkView extends AbstractView {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final RatingService ratingService;

    public BookmarkView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, RatingService ratingService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.ratingService = ratingService;
    }

    @Override
    public void display() {
        System.out.println("┌                    ┐");
        System.out.println("  BOOKMARKED RECIPES  ");
        System.out.println("└                    ┘");

        String username = authenticationService.getCurrentUsername();
        List<String> bookmarkedIds = userService.getBookmarkedRecipeIdsByUsername(username);

        if (bookmarkedIds.isEmpty()) {
            writeYellowLine("There are no bookmarked recipes!");
            System.out.println();

            new RecipeMenuView(userService, authenticationService, recipeService, ratingService).display();
            return;
        }

        writeYellowLine("Select the number of the recipe that you want to read or type 0 to go back to the recipe menu");
        printOptions(bookmarkedIds);
        System.out.println();

        int input = getNumberInputMinMax(0, bookmarkedIds.size());
        System.out.println();

        if (input == 0) {
            new RecipeMenuView(userService, authenticationService, recipeService, ratingService).display();
            return;
        }

        String id = bookmarkedIds.get(input - 1);
        Recipe selectedRecipe = recipeService.getRecipeById(id);
        System.out.println(selectedRecipe);
        System.out.println();

        new RecipeMenuView(userService, authenticationService, recipeService, ratingService).display();
    }

    private void printOptions(List<String> bookmarkedIds) {
        for (int i = 0; i < bookmarkedIds.size(); i++) {
            String id = bookmarkedIds.get(i);
            Recipe recipe = recipeService.getRecipeById(id);
            String data = recipeService.formatRecipeToSelectData(recipe);
            System.out.println(i+1 + ": " + data);
        }
        System.out.println("0: Go back");
    }
}
