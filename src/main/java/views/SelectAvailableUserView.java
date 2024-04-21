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

        showUsernames(usernameList);
        System.out.println();

        int userIndex = getNumberInput(0, usernameList.size() - 1);
        System.out.println();

        String username = usernameList.get(userIndex);
        new LoginView(userService, authenticationService, recipeService, username).display();
    }

    private void showUsernames(List<String> usernameList) {
        writeYellowLine("Here is a list of all users:");
        for (int i = 0; i < usernameList.size(); i++) {
            System.out.println(i + ": " + usernameList.get(i));
        }
    }
}
