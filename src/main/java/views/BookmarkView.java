package views;

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

        writeYellowLine("Here are your bookmarked recipes");
        List<String> ids = userService.getBookmarkedIds();

        if (ids.isEmpty()) {
            writeYellowLine("There are no bookmarked recipes!");
            System.out.println();

            new RecipeMenuView(userService, authenticationService, recipeService).display();
            return;
        }

        writeYellowLine("Select the number of the recipe that you want to read");
        for (int i = 0; i < ids.size(); i++) {
            System.out.println(i+1 + ": " + recipeService.getRecipeTitleById(ids.get(i)));
        }
        System.out.println();

        int input = getNumberInput(1, ids.size());
        System.out.println();

        String id = ids.get(input - 1);
        System.out.println(recipeService.getRecipeById(id));

        new RecipeMenuView(userService, authenticationService, recipeService).display();
    }
}
