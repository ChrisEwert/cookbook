package views;

import services.AuthenticationService;
import services.RatingService;
import services.RecipeService;
import services.UserService;

import java.util.List;

public class SelectAvailableUserView extends AbstractView {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;
    private final RatingService ratingService;

    public SelectAvailableUserView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService, RatingService ratingService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
        this.ratingService = ratingService;
    }

    @Override
    public void display() {
        System.out.println("┌             ┐");
        System.out.println("  SELECT USER  ");
        System.out.println("└             ┘");

        List<String> usernames = userService.getAllUsernames();

        if (usernames.isEmpty()) {
            writeYellowLine("There are no users yet.");
            System.out.println();

            new CreateNewUserView(userService, authenticationService, recipeService, ratingService).display();
            return;
        }

        writeYellowLine("Select the number of your username or type 0 to go back to the login menu");
        printOptions(usernames);
        System.out.println();

        int userIndex = getNumberInRange(0, usernames.size());
        System.out.println();

        if (userIndex == 0) {
            new LoginMenuView(userService, authenticationService, recipeService, ratingService).display();
            return;
        }

        String username = usernames.get(userIndex - 1);

        new LoginView(userService, authenticationService, recipeService, ratingService, username).display();
    }

    private void printOptions(List<String> usernames) {
        for (int i = 0; i < usernames.size(); i++) {
            System.out.println(i+1 + ": " + usernames.get(i));
        }
        System.out.println("0: Go back");
    }
}
