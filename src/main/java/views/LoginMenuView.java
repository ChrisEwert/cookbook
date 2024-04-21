package views;

import services.AuthenticationService;
import services.RecipeService;
import services.UserService;

import java.util.List;
import java.util.Objects;

public class LoginMenuView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final RecipeService recipeService;

    public LoginMenuView(UserService userService, AuthenticationService authenticationService, RecipeService recipeService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.recipeService = recipeService;
    }

    @Override
    public void display() {
        System.out.println("┌            ┐");
        System.out.println("  LOGIN MENU  ");
        System.out.println("└            ┘");

        List<String> usernames = userService.getUsernames();

        if (usernames.isEmpty()) {
            writeYellowLine("You are the first username of this cookbook!");
            System.out.println();
            new CreateNewUserView(userService, authenticationService, recipeService).display();
            return;
        }

        showAllUsernames(usernames);
        System.out.println();

        int userMenuInput = getUserMenuInput();
        System.out.println();

        if (Objects.equals(userMenuInput, 1)) {
            new SelectAvailableUserView(userService, authenticationService, recipeService).display();
        } else if (Objects.equals(userMenuInput, 2)) {
            new CreateNewUserView(userService, authenticationService, recipeService).display();
        } else {
            writeYellowLine("Have a nice day.");
        }
    }

    private int getUserMenuInput() {
        writeYellowLine("Are you one of those users?");
        System.out.println("1: I am one of those users");
        System.out.println("2: I am a new username");
        System.out.println("0: Close cookbook");
        System.out.println();
        return getNumberInput(0, 2);
    }

    private void showAllUsernames(List<String> usernames) {
        writeYellowLine("Here is a list of all current users:");
        for (String username : usernames) {
            System.out.println(username);
        }
    }
 }
