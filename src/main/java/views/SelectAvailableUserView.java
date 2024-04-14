package views;

import services.AuthenticationService;
import services.UserService;

import java.util.List;

public class SelectAvailableUserView implements View {
    private final UserService userService;
//    private final AuthenticationService authenticationService;
//
//    public SelectAvailableUserView(UserService userService, AuthenticationService authenticationService) {
//        this.userService = userService;
//        this.authenticationService = authenticationService;
//    }

    public SelectAvailableUserView(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void display() {
        List<String> usernameList = userService.getUsernames();
        if (!usernameList.isEmpty()) {
            showUsernames(usernameList);
            int userIndex = getNumberInput(0, usernameList.size() - 1);
            System.out.println();
            System.out.println("Selected user: " + usernameList.get(userIndex));
            System.out.println("Please enter the password");
            String password = getUserInput();
            System.out.println(password);
        } else {
            System.out.println("There are no users yet.");
            System.out.println();
        }
    }

    private void showUsernames(List<String> usernameList) {
        System.out.println("Here is a list of all users:");
        for (int i = 0; i < usernameList.size(); i++) {
            System.out.println(i + ": " + usernameList.get(i));
        }
        System.out.println();
    }
}
