package views;

import recipe.CookbookRepository;
import services.UserService;

public class RecipeMenuView implements View {
    private final UserService userService;

    public RecipeMenuView(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void display() {
        while(true) {
            System.out.println("What do you want to do now?");
            System.out.println("1: I want to read a recipe");
            System.out.println("2: I want to create a new recipe");
            System.out.println("3: I want to log out");
            System.out.println("0: Close cookbook");

            System.out.print("Your input: ");
            String input = getUserInput();
            if (input.equals("1")) {
                // do something
                break;
            } else if (input.equals("2")) {
                // do something
                break;
            } else if (input.equals("3")) {
                userService.logout();
                System.out.println("Logged out.");
                System.out.println();
                System.out.println(CookbookRepository.getUser());
                new LogInView(userService).display();
                break;
            } else if (input.equals("0")) {
                System.out.println("Have a nice day.");
                break;
            } else {
                writeRedLine("Invalid input.");
            }
        }
    }
}
