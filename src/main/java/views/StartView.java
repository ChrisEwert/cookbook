package views;

import services.AuthenticationService;
import services.UserService;

public class StartView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public StartView(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @Override
    public void display() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════╗");
        System.out.println("║                OPEN COOKBOOK                ║");
        System.out.println("╚═════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("Hello and welcome to the open cookbook.");
        System.out.println("In this cookbook, users can share recipes.");
        System.out.println();

        writeYellowLine("To start, please log in.");
        System.out.println();
        new LoginMenuView(userService, authenticationService).display();
    }
}
