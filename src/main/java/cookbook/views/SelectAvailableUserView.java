package cookbook.views;

import java.util.List;
import java.util.Scanner;

public class SelectAvailableUserView implements View {

    private final List<String> users;

    public SelectAvailableUserView(List<String> users) {
        this.users = users;
    }

    @Override
    public void display() {
        showAvailableUsers();

        int userIndex = getUserIndex();
        if (userIndex < users.size()) {
            System.out.println(COLOR_GREEN + "You are now logged in as " + users.get(userIndex) + COLOR_RESET);
        } else {
            System.out.println(COLOR_RED + "Please select the number of a user that exists." + COLOR_RESET);
            System.out.println();
        }
    }

    private void showAvailableUsers() {
        System.out.println("Here is a list of all users:");
        for (int i = 0 ; i < users.size() ; i++) {
            System.out.println(i + ": " + users.get(i));
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
            }
        }
    }
}
