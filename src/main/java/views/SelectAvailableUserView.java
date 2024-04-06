package views;

import services.UserService;

public class SelectAvailableUserView implements View {
    private final UserService userService;

    public SelectAvailableUserView(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void display() {
        showAvailableUsers();

        while (true) {
            int userIndex = getUserIndex();
            if (userIndex < userService.getUserCount()) {
                String username = userService.getUser(userIndex);
                userService.login(username);
                writeGreenLine("You are now logged in as " + username);
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
