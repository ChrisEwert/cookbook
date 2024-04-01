package cookbook.views;

import cookbook.services.UserService;

public class StartView implements View {
    private final UserService userService;

    public StartView(UserService userService) {
        this.userService = userService;
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

        new LogInView(userService).display();
    }
}
