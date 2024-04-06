package views;

import services.AuthenticationService;
import services.UserService;

import java.util.Objects;

public class LogInView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public LogInView(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @Override
    public void display() {
        while(true) {
            String userMenuInput = getUserMenuInput();
            System.out.println();

            if (Objects.equals(userMenuInput, "1")) {
                new SelectAvailableUserView(userService, authenticationService).display();
                break;
            } else if (Objects.equals(userMenuInput, "2")) {
                new CreateNewUserView(userService, authenticationService).display();
                break;
            } else if (Objects.equals(userMenuInput, "0")) {
                System.out.println("Have a nice day.");
                break;
            } else {
                writeRedLine("Please enter a number between 0 and 2.");
                System.out.println();
            }
        }
    }

    private String getUserMenuInput() {
        System.out.println("To start, please log in. Do you have an account?");
        System.out.println("1: I already have an account");
        System.out.println("2: I want to create a new account");
        System.out.println("0: Close cookbook");
        System.out.print("Your input: ");
        return getUserInput();
    }
}
