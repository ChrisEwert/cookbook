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
        String userName = getUserInput();

        if (authenticationService.containsUser(userName)) {
            System.out.println("This user already exists!");
            System.out.println();
            new LoginView(userService, authenticationService, userName).display();
        } else {
            System.out.println("Please enter your password");
            String password = getUserInput();
            userService.createUser(userName, password);
            authenticationService.login(userName);
            writeGreenLine("You are now logged in as " + userName);
        }
    }
}
