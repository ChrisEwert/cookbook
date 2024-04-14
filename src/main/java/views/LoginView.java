package views;

import services.AuthenticationService;
import services.UserService;

public class LoginView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final String username;

    public LoginView(UserService userService, AuthenticationService authenticationService, String username) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.username = username;
    }

    @Override
    public void display() {
        while (true) {
            writeYellowLine("Selected user: " + username);
            System.out.println("Please enter the password or type 'q' to quit: ");
            String password = getUserInput();

            if (password.equals("q")) {
                System.out.println();
                new LoginMenuView(userService, authenticationService).display();
                return;
            }

            if (authenticationService.credentialsMatch(username, password)) {
                authenticationService.login(username);
                writeGreenLine("You are now logged in as " + username);
                break;
            } else {
                writeRedLine("Wrong password. Please try again!");
            }
        }
    }
}
