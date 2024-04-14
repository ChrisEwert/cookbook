package views;

import services.AuthenticationService;
import services.UserService;

import java.util.Objects;

public class LoginMenuView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public LoginMenuView(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @Override
    public void display() {
        int userMenuInput = getUserMenuInput();
        System.out.println();

        if (Objects.equals(userMenuInput, 1)) {
            new SelectAvailableUserView(userService, authenticationService).display();
        } else if (Objects.equals(userMenuInput, 2)) {
            new CreateNewUserView(userService, authenticationService).display();
        } else {
            writeYellowLine("Have a nice day.");
        }
    }

    private int getUserMenuInput() {
        writeYellowLine("To start, please log in.");
        writeYellowLine("Here is a list of all current users:");
        for (String username : userService.getUsernames()) {
            System.out.println(username);
        }
        System.out.println();

        writeYellowLine("Are you one of those users?");
        System.out.println("1: I am one of those users");
        System.out.println("2: I am a new user");
        System.out.println("0: Close cookbook");
        System.out.println();
        return getNumberInput(0, 2);
    }
 }
