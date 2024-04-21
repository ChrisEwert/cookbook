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
        List<String> ingredients = getList("ingredient", "cooking steps");
        System.out.println();

        writeYellowLine("Enter the cooking steps of the recipe");
        List<String> content = getList("cooking step", "categories");
        System.out.println();

        writeYellowLine("Enter the categories of the recipe");
        List<String> categories = getList("category", "cooking time");
        System.out.println();

        System.out.println("Enter how many minutes the recipe will take");
        int minutes = getNumberInput(0, 120);                                                                           // TODO: No maximum value
        System.out.println();

        recipeService.saveRecipe(name, ingredients, content, categories, minutes);
        writeGreenLine("Saved recipe!");

        new RecipeMenuView(userService, authenticationService, recipeService).display();
    }

    private List<String> getList(String listKind, String nextTopic) {
        List<String> list = new ArrayList<>();
        while (true) {
            System.out.println("Enter the next " + listKind + " or type 'q' to continue with the " + nextTopic);
            String input = getUserInput();
            if (input.equalsIgnoreCase("q")) {
                break;
            }
            list.add(input);
        }
        return list;
    }
}
