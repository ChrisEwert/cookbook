package views;

import services.AuthenticationService;
import services.RecipeService;
import services.UserService;

public class LoginView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final String username;
    private String password;

    public LoginView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, String username) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.username = username;
        password = null;
    }

    public LoginView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, String username, String password) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.username = username;
        this.password = password;
    }

    @Override
    public void display() {
        if (password != null) {
            login();
            return;
        }
        while (true) {
            writeYellowLine("Selected username: " + username);
            System.out.println("Please enter the password or type 'q' to quit: ");
            password = getUserInput();

            if (password.equals("q")) {
                System.out.println();
                new LoginMenuView(userService, authenticationService, recipeService).display();
                return;
            }

            if (authenticationService.credentialsMatch(username, password)) {
                break;
            } else {
                writeRedLine("Wrong password. Please try again!");
            }
        }
        login();
    }

    private void login() {
        authenticationService.login(username);
        writeGreenLine("You are now logged in as " + username);
        new RecipeMenuView(userService, authenticationService, recipeService).display();
    }
}
