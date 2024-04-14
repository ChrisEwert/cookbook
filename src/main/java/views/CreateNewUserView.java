package views;

import services.AuthenticationService;
import services.UserService;

public class CreateNewUserView implements View {
    private final UserService userService;
//    private final AuthenticationService authenticationService;

//    public CreateNewUserView(UserService userService, AuthenticationService authenticationService) {
//        this.userService = userService;
//        this.authenticationService = authenticationService;
//    }

    public CreateNewUserView(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void display() {
        System.out.println("Please enter the username");
        String userName = getUserInput();
        System.out.println("Please enter the password");
        String password = getUserInput();
//
//        if (userService.containsUser(userName)) {
//            System.out.println("This user already exists!");
//            // TODO: Add password authentication
//        } else {
            userService.createUser(userName, password);
//        }
//        authenticationService.login(userName);
//        new RecipeMenuView(userService, authenticationService).display();
    }
}
