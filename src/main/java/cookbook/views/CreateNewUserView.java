package cookbook.views;

import cookbook.services.UserService;

import java.util.Scanner;

public class CreateNewUserView implements View {
    private final UserService userService;

    public CreateNewUserView(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void display() {
        System.out.print("Please enter the user: ");
        String userName = new Scanner(System.in).nextLine();

        if (userService.containsUser(userName)) {
            System.out.println("This user already exists!");
        } else {
            userService.addNewUser(userName);
        }
        System.out.println(COLOR_GREEN + "You are now logged in as " + userName + COLOR_RESET);
    }
}
