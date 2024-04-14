package views;

import services.AuthenticationService;
import services.UserService;

import java.util.List;

public class SelectAvailableUserView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public SelectAvailableUserView(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @Override
    public void display() {
        List<String> usernameList = userService.getUsernames();
        if (!usernameList.isEmpty()) {
            showUsernames(usernameList);
            int userIndex = getNumberInput(0, usernameList.size() - 1);
            System.out.println();

            String username = usernameList.get(userIndex);
            new LoginView(userService, authenticationService, username).display();
        } else {
            System.out.println("There are no users yet.");
            System.out.println();
        }
    }

    private void showUsernames(List<String> usernameList) {
        writeYellowLine("Here is a list of all users:");
        for (int i = 0; i < usernameList.size(); i++) {
            System.out.println(i + ": " + usernameList.get(i));
        }
        System.out.println();
    }
}
