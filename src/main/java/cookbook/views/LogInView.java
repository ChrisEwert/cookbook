package cookbook.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class LogInView implements View {
    private final List<String> users = new ArrayList<>();
    @Override
    public void display() {
        fillUserList();

        while(true) {
            String userMenuInput = getUserMenuInput();
            System.out.println();

            if (Objects.equals(userMenuInput, "1")) {
                new SelectAvailableUserView(users).display();
                break;
            } else if (Objects.equals(userMenuInput, "2")) {
                new CreateNewUserView(users).display();
                break;
            } else if (Objects.equals(userMenuInput, "0")) {
                System.out.println("Have a nice day!");
                break;
            } else {
                System.out.println(COLOR_RED + "Please enter a number between 0 and 2." + COLOR_RESET);
                System.out.println();
            }
        }
    }

    private void fillUserList() {
        users.add("Karl");
        users.add("Robin");
        users.add("Tina");
    }

    private String getUserMenuInput() {
        System.out.println("To start, please log in. Do you have an account?");
        System.out.println("1: I already have an account");
        System.out.println("2: I want to create a new account");
        System.out.println("0: Close cookbook");
        System.out.print("Your input: ");
        return new Scanner(System.in).nextLine();
    }
}
