package views;

import services.AuthenticationService;
import services.RecipeService;
import services.UserService;

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

        if (userService.noUsersExist()) {
            writeYellowLine("You are the first user of this cookbook!");
            System.out.println();

            new CreateNewUserView(userService, authenticationService, recipeService).display();
            return;
        }

        writeYellowLine("Here is a list of all current users");
        printUsernames();
        System.out.println();

        writeYellowLine("Are you one of those users?");
        printOptions();
        System.out.println();

        int userMenuInput = getNumberInputMinMax(0, 2);
        System.out.println();

        if (Objects.equals(userMenuInput, 1)) {
            new SelectAvailableUserView(userService, authenticationService, recipeService).display();
        } else if (Objects.equals(userMenuInput, 2)) {
            new CreateNewUserView(userService, authenticationService, recipeService).display();
        } else {
            writeYellowLine("Have a nice day.");
        }
    }

    private void printUsernames() {
        for (String username : userService.getAllUsernames()) {
            System.out.println(username);
        }
    }

    private void printOptions() {
        System.out.println("1: I am one of those users");
        System.out.println("2: I am a new user");
        System.out.println("0: Close cookbook");
    }
 }
