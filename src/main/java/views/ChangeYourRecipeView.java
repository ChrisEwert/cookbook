package views;

import cookbook.Recipe;
import services.AuthenticationService;
import services.RatingService;
import services.RecipeService;
import services.UserService;

import java.util.List;
import java.util.Objects;

public class ChangeYourRecipeView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final RatingService ratingService;
    private final Recipe recipe;

    public ChangeYourRecipeView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, RatingService ratingService, Recipe recipe) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.ratingService = ratingService;
        this.recipe = recipe;
    }

    @Override
    public void display() {
        System.out.println("┌               ┐");
        System.out.println("  CHANGE RECIPE  ");
        System.out.println("└               ┘");

        String id = recipe.id();

        String author = authenticationService.getCurrentUsername();

        String newName = getNewName();
        System.out.println();

        System.out.println(recipe);
        List<String> newIngredients = getNewIngredients();
        System.out.println();

        System.out.println(recipe);
        List<String> newContent = getNewContent();
        System.out.println();

        System.out.println(recipe);
        List<String> newCategories = getNewCategories();
        System.out.println();

        System.out.println(recipe);
        int newCookingTime = getNewCookingTime();
        System.out.println();

        recipeService.updateRecipe(id, newName, author, newIngredients, newContent, newCategories, newCookingTime);

        Recipe updatedRecipe = recipeService.getRecipeById(id);

        new YourSelectedRecipeView(userService, authenticationService, recipeService, ratingService, updatedRecipe).display();
    }

    private String getNewName() {
        writeYellowLine("Current name of the recipe");
        System.out.println(recipe.name());

        writeYellowLine("Enter the new name of the recipe or type 'q' to leave it as it is");
        String newName = getUserInput();

        if (Objects.equals(newName, "q")) {
            newName = recipe.name();
        }

        return newName;
    }

    private List<String> getNewIngredients() {
        List<String> ingredients = recipe.ingredients();
        String topicSingular = "ingredient";
        String topicPlural = "ingredients";

        return handleList(ingredients, topicSingular, topicPlural);
    }

    private List<String> getNewContent() {
        List<String> content = recipe.content();
        String topicSingular = "cooking step";
        String topicPlural = "cooking steps";

        return handleList(content, topicSingular, topicPlural);
    }

    private List<String> getNewCategories() {
        List<String> categories = recipe.categories();
        String topicSingular = "category";
        String topicPlural = "categories";

        return handleList(categories, topicSingular, topicPlural);
    }

    private int getNewCookingTime() {
        writeYellowLine("Current cooking time in minutes");
        System.out.println(recipe.cookingTimeInMinutes());

        writeYellowLine("Enter the new cooking time of the recipe in minutes or type 0 to leave it as it is");
        int newTime = getNumberInput();

        if (newTime == 0) {
            newTime = recipe.cookingTimeInMinutes();
        }

        return newTime;
    }

    private List<String> handleList(List<String> list, String topicSingular, String topicPlural) {
        while(true) {
            writeYellowLine("Current " + topicPlural + " of the recipe");
            printOptions(list, topicSingular);

            writeYellowLine("Enter the number of the " + topicSingular + " that you want to change. Type 0 if you are done with your changes. Type '+' to add a new " + topicSingular + ". Type '-' to remove a " + topicSingular + ".");
            String input = getNumberInputMinMaxOrOptions(0, list.size(), List.of("+", "-"));

            if (Objects.equals(input, "+")) {
                writeYellowLine("Enter the " + topicSingular + " that you want to add");
                String newIngredient = getUserInput();

                list.add(newIngredient);
                System.out.println();

                continue;
            }

            if (Objects.equals(input, "-")) {
                if (list.size() > 1) {
                    writeYellowLine("Enter the number of the " + topicSingular + " that you want to remove");
                    int ingredientIndex = getNumberInputMinMax(1, list.size());

                    list.remove(ingredientIndex-1);
                } else {
                    writeRedLine("You need to have at least one " + topicSingular);
                }

                System.out.println();

                continue;
            }

            int index = Integer.parseInt(input);

            if (index == 0) {
                break;
            }

            writeYellowLine("Enter the new value of the " + topicSingular);
            String newIngredient = getUserInput();

            list.set(index-1, newIngredient);
            System.out.println();
        }

        return list;
    }

    private void printOptions(List<String> list, String topicSingular) {
        for (int i=0 ; i<list.size() ; i++) {
            System.out.println(i+1 + ": " + list.get(i));
        }
        System.out.println("0: Changes done");
        System.out.println("+: Add new " + topicSingular);
        System.out.println("-: Remove " + topicSingular);
    }
}
