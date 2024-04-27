package views;

import services.AuthenticationService;
import services.RatingService;
import services.RecipeService;
import services.UserService;

public class LoginView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final RatingService ratingService;
    private final String username;
    private String password;

    public LoginView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, RatingService ratingService, String username) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.ratingService = ratingService;
        this.username = username;
        password = null;
    }

    public LoginView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, RatingService ratingService, String username, String password) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.ratingService = ratingService;
        this.username = username;
        this.password = password;
    }

    @Override
    public void display() {
        if (password != null) {
            login();

            new RecipeMenuView(userService, authenticationService, recipeService, ratingService).display();
            return;
        }

        while (true) {
            writeYellowLine("Selected username: " + username);
            System.out.println("Please enter the password or type 'q' to quit: ");
            password = getUserInput();

            if (password.equalsIgnoreCase("q")) {
                System.out.println();

                new LoginMenuView(userService, authenticationService, recipeService, ratingService).display();
                return;
            }

            if (authenticationService.credentialsMatch(username, password)) {
                System.out.println();
                break;
            }

            writeRedLine("Wrong password. Please try again!");
            System.out.println();
        }

        login();

        new RecipeMenuView(userService, authenticationService, recipeService, ratingService).display();
    }

    private void login() {
        authenticationService.login(username);

        writeGreenLine("You are now logged in as " + username);
        System.out.println();
    }
}
