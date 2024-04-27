package views;

import cookbook.Recipe;
import services.AuthenticationService;
import services.RecipeService;
import services.UserService;

import java.util.List;

public class YourRecipesView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;

    public YourRecipesView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
    }

    @Override
    public void display() {
        System.out.println("┌              ┐");
        System.out.println("  YOUR RECIPES  ");
        System.out.println("└              ┘");

//        List<Recipe> recipes = recipeService.getRecipesByUsername(authenticationService.getCurrentUsername());
//
//        if (recipes.isEmpty()) {
//            writeYellowLine("You have not created any recipes.");
//            System.out.println();
//
//            new RecipeMenuView(userService, authenticationService, recipeService).display();
//            return;
//        }
//
//        recipes.forEach(System.out::println);

        new RecipeMenuView(userService, authenticationService, recipeService).display();
    }
}
