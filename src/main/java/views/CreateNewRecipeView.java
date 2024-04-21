package views;

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
        System.out.println("┌               ┐");
        System.out.println("  CREATE RECIPE  ");
        System.out.println("└               ┘");

        writeYellowLine("Enter the name of the recipe");
        String name = getUserInput();
        System.out.println();

        writeYellowLine("Enter the ingredients of the recipe");
        List<String> ingredients = new ArrayList<>();
        while (true) {
            System.out.println("Enter the next ingredient or type 'q' to continue with the cooking steps");
            String ingredientsInput = getUserInput();
            if (ingredientsInput.equalsIgnoreCase("q")) {
                break;
            }
            ingredients.add(ingredientsInput);
        }
        System.out.println();

        writeYellowLine("Enter the cooking steps of the recipe");
        List<String> content = new ArrayList<>();
        while (true) {
            System.out.println("Enter the next cooking step or type 'q' to continue with the categories");
            System.out.println("STEP " + (content.size() + 1));
            System.out.print("\t");
            String contentInput = getUserInput();
            if (contentInput.equalsIgnoreCase("q")) {
                break;
            }
            content.add(contentInput);
        }
        System.out.println();

        writeYellowLine("Enter the categories of the recipe");
        List<String> categories = new ArrayList<>();
        while (true) {
            System.out.println("Enter the next category or type 'q' to continue with the cooking time");
            String categoryInput = getUserInput();
            if (categoryInput.equalsIgnoreCase("q")) {
                break;
            }
            categories.add(categoryInput);
        }
        System.out.println();

        System.out.println("Enter how many minutes the recipe will take");
        int minutes = getNumberInput(0, 120);                   // TODO: No maximum value
        System.out.println();

        recipeService.saveRecipe(name, ingredients, content, categories, minutes);
        writeGreenLine("Saved recipe!");

        new RecipeMenuView(userService, authenticationService, recipeService).display();       // TODO: Other options: rate or leave
    }
}
