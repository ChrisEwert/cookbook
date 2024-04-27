package views;

import services.AuthenticationService;
import services.RatingService;
import services.RecipeService;
import services.UserService;

public class CreateNewUserView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final RatingService ratingService;

    public CreateNewUserView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, RatingService ratingService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.ratingService = ratingService;
    }

    @Override
    public void display() {
        System.out.println("┌             ┐");
        System.out.println("  CREATE USER  ");
        System.out.println("└             ┘");

        writeYellowLine("Please enter your username");
        String username = getUserInput();
        System.out.println();

        if (authenticationService.userExists(username)) {
            writeRedLine("This username already exists!");
            System.out.println();

            new LoginView(userService, authenticationService, recipeService, ratingService, username).display();
            return;
        }

        writeYellowLine("Please enter your password");
        String password = getUserInput();

        userService.createNewUser(username, password);
        System.out.println();

        new LoginView(userService, authenticationService, recipeService, ratingService, username, password).display();
    }
}
