package views;

import services.AuthenticationService;
import services.UserService;

public class CreateNewUserView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public CreateNewUserView(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @Override
    public void display() {
        System.out.println("┌             ┐");
        System.out.println("  CREATE USER  ");
        System.out.println("└             ┘");

        System.out.println("Please enter your username");
        String username = getUserInput();

        if (authenticationService.containsUser(username)) {
            System.out.println("This user already exists!");
            System.out.println();
            new LoginView(userService, authenticationService, username).display();
        } else {
            System.out.println("Please enter your password");
            String password = getUserInput();

            userService.createUser(username, password);

            new LoginView(userService, authenticationService, username, password).display();
        }
    }
}
