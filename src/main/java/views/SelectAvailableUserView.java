package views;

import services.AuthenticationService;
import services.RecipeService;
import services.UserService;

import java.util.List;

public class SelectAvailableUserView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;

    public SelectAvailableUserView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
    }

    @Override
    public void display() {
        System.out.println("┌             ┐");
        System.out.println("  SELECT USER  ");
        System.out.println("└             ┘");

        if (userService.noUsersExist()) {
            writeYellowLine("There are no users yet.");
            System.out.println();

            new CreateNewUserView(userService, authenticationService, recipeService).display();
            return;
        }

        writeYellowLine("Select the number of your username or type 0 to go back to the login menu");
        printOptions();
        System.out.println();

        int userIndex = getNumberInputMinMax(0, userService.getUserCount());
        System.out.println();

        if (userIndex == 0) {
            new LoginMenuView(userService, authenticationService, recipeService).display();
            return;
        }

        String username = userService.getUsernameByIndex(userIndex - 1);

        new LoginView(userService, authenticationService, recipeService, username).display();
    }

    private void printOptions() {
        List<String> usernames = userService.getAllUsernames();

        for (int i = 0; i < usernames.size(); i++) {
            System.out.println(i+1 + ": " + usernames.get(i));
        }
        System.out.println("0: Go back");
    }
}
