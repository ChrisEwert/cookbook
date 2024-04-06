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
        System.out.print("Please enter the user: ");
        String userName = getUserInput();

        if (userService.containsUser(userName)) {
            System.out.println("This user already exists!");
            // TODO: Add password authentication
        } else {
            userService.addNewUserToDB(userName);
        }
        authenticationService.login(userName);
        writeGreenLine("You are now logged in as: " + userName);
        System.out.println();
        new RecipeMenuView(userService, authenticationService).display();
    }
}
