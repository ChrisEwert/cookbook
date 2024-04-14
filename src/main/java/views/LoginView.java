package views;

import services.AuthenticationService;
import services.UserService;

public class LoginView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final String username;
    private String password;

    public LoginView(UserService userService, AuthenticationService authenticationService, String username) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.username = username;
        password = null;
    }

    public LoginView(UserService userService, AuthenticationService authenticationService, String username, String password) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.username = username;
        this.password = password;
    }

    @Override
    public void display() {
        if (password != null) {
            login();
            return;
        }
        while (true) {
            writeYellowLine("Selected user: " + username);
            System.out.println("Please enter the password or type 'q' to quit: ");
            password = getUserInput();

            if (password.equals("q")) {
                System.out.println();
                new LoginMenuView(userService, authenticationService).display();
                return;
            }

            if (authenticationService.credentialsMatch(username, password)) {
                break;
            } else {
                writeRedLine("Wrong password. Please try again!");
            }
        }
        login();
    }

    private void login() {
        authenticationService.login(username);
        writeGreenLine("You are now logged in as " + username);
        new RecipeMenuView(userService, authenticationService).display();
    }
}
