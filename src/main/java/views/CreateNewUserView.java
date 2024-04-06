package views;

import services.UserService;

public class CreateNewUserView implements View {
    private final UserService userService;

    public CreateNewUserView(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void display() {
        System.out.print("Please enter the user: ");
        String userName = getUserInput();

        if (userService.containsUser(userName)) {
            System.out.println("This user already exists!");
            // TODO: Add password authentication
        } else {
            userService.addNewUser(userName);
        }
        userService.login(userName);
        writeGreenLine("You are now logged in as: " + userName);
    }
}
