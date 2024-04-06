package views;

import services.AuthenticationService;
import services.UserService;

public class SelectAvailableUserView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public SelectAvailableUserView(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @Override
    public void display() {
        showAvailableUsers();

        while (true) {
            int userIndex = getUserIndex();
            if (userIndex < userService.getUserCount()) {
                String username = userService.getUser(userIndex);
                authenticationService.login(username);
                writeGreenLine("You are now logged in as " + username);
                System.out.println();

                new RecipeMenuView(userService, authenticationService).display();
                break;
            } else {
                writeRedLine("Please select the number of a user that exists.");
                System.out.println();
            }
        }
    }

    private void showAvailableUsers() {
        System.out.println("Here is a list of all users:");
        for (int i = 0; i < userService.getUserCount(); i++) {
            System.out.println(i + ": " + userService.getUser(i));
        }
        System.out.println();
    }

    private int getUserIndex() {
        while (true) {
            System.out.print("Please enter the matching number of your user: ");
            String userInput = getUserInput();
            try {
                return Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                writeRedLine("Please enter a real number.");
                System.out.println();
            }
        }
    }
}
