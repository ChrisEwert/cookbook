package views;

import cookbook.RecipeRepository;
import services.AuthenticationService;
import services.RecipeService;
import services.UserService;

import java.util.ArrayList;
import java.util.List;

public class CreateNewRecipeView implements View{
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;

    public CreateNewRecipeView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
    }

    @Override
    public void display() {
        writeYellowLine("Create new recipe");
        System.out.println("Enter the name of the recipe");
        String name = getUserInput();
        System.out.println("Enter the content of the recipe");  // TODO: Not a string
        String content = getUserInput();
        System.out.println("Enter a category of the recipe");   // TODO: More than one
        List<String> categories = new ArrayList<>();
        categories.add(getUserInput());
        System.out.println("Enter how many minutes the recipe will take");
        int minutes = getNumberInput(0, 120);                   // TODO: No maximum value

        recipeService.saveRecipe(name, content, categories, minutes);
        writeGreenLine("Saved recipe!");

        new RecipeMenuView(userService, authenticationService, recipeService).display();       // TODO: Other options: rate or leave
    }
}
