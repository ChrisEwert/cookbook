package cookbook.views;

import java.util.List;
import java.util.Scanner;

public class CreateNewUserView implements View {

    private final List<String> users;

    public CreateNewUserView(List<String> users) {
        this.users = users;
    }

    @Override
    public void display() {
        System.out.print("Please enter the user: ");
        String userName = new Scanner(System.in).nextLine();

        if (users.contains(userName)) {
            System.out.println("This user already exists!");
        } else {
            users.add(userName);
        }
        System.out.println(COLOR_GREEN + "You are now logged in as " + userName + COLOR_RESET);
    }
}
