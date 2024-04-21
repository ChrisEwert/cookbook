package views;

import services.AuthenticationService;
import services.RecipeService;
import services.UserService;

public class CreateNewUserView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;

    public CreateNewUserView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
    }

    @Override
    public void display() {
        System.out.println("┌             ┐");
        System.out.println("  CREATE USER  ");
        System.out.println("└             ┘");

        System.out.println("Please enter your username");
        String username = getUserInput();

        if (authenticationService.containsUser(username)) {
            System.out.println("This user already exists!");
            System.out.println();
            new LoginView(userService, authenticationService, recipeService, username).display();
        } else {
            System.out.println("Please enter your password");
            String password = getUserInput();

            userService.createUser(username, password);

            new LoginView(userService, authenticationService, recipeService, username, password).display();
        }
    }
}
