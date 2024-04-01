package cookbook.views;

import cookbook.services.UserService;

import java.util.Scanner;

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
                System.out.println(COLOR_GREEN + "You are now logged in as " + userService.getUser(userIndex) + COLOR_RESET);
                break;
            } else {
                System.out.println(COLOR_RED + "Please select the number of a user that exists." + COLOR_RESET);
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
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please enter the matching number of your user: ");
            String userInput = scanner.nextLine();
            try {
                return Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println(COLOR_RED + "Please enter a real number." + COLOR_RESET);
                System.out.println();
            }
        }
    }
}
