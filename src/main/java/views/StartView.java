package views;

import services.UserService;

public class StartView implements View {
    private final UserService userService;

    public StartView(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void display() {
        // TODO: Make this the first step of the CookBook
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════╗");
        System.out.println("║                OPEN COOKBOOK                ║");
        System.out.println("╚═════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("Hello and welcome to the open cookbook.");
        System.out.println("In this cookbook, users can share recipes.");
        System.out.println();

        new LogInView(userService).display();
    }
}
