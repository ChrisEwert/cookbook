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

        List<String> usernameList = userService.getUsernames();

        if (usernameList.isEmpty()) {
            System.out.println("There are no users yet.");
            System.out.println();
            return;
        }

        writeYellowLine("Select the number of your username or type 0 to go back to the login menu");
        for (int i = 0; i < usernameList.size(); i++) {
            System.out.println(i+1 + ": " + usernameList.get(i));
        }
        System.out.println("0: Go back");
        System.out.println();

        int userIndex = getNumberInput(0, usernameList.size());
        System.out.println();

        if (userIndex == 0) {
            new LoginMenuView(userService, authenticationService, recipeService).display();
            return;
        }

        String username = usernameList.get(userIndex - 1);
        new LoginView(userService, authenticationService, recipeService, username).display();
    }
}
