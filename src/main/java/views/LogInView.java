package views;

import services.UserService;

import java.util.Objects;

public class LogInView implements View {
    private final UserService userService;

    public LogInView(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void display() {
        int userMenuInput = getUserMenuInput();
        System.out.println();

        if (Objects.equals(userMenuInput, 1)) {
            new SelectAvailableUserView(userService).display();
        } else if (Objects.equals(userMenuInput, 2)) {
            new CreateNewUserView(userService).display();
        } else {
            System.out.println("Have a nice day.");
        }
    }

    private int getUserMenuInput() {
        System.out.println("To start, please log in. Do you have an account?");
        System.out.println("1: I already have an account");
        System.out.println("2: I want to create a new account");
        System.out.println("0: Close cookbook");
        return getNumberInput(0, 2);
    }
 }
