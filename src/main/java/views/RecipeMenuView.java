//package views;
//
//import db.RecipeDataHandler;
//import cookbook.Recipe;
//import services.AuthenticationService;
//import services.UserService;
//
//public class RecipeMenuView implements View {
//    private final UserService userService;
//    private final AuthenticationService authenticationService;
//
//    public RecipeMenuView(UserService userService, AuthenticationService authenticationService) {
//        this.userService = userService;
//        this.authenticationService = authenticationService;
//    }
//
//    @Override
//    public void display() {
//        while(true) {
//            String input = getRecipeMenuInput();
//            System.out.println();
//
//            if (input.equals("1")) {
//                // TODO: list all recipes (id, name + author)
//                break;
//            } else if (input.equals("2")) {
//                // TODO: Use the RecipeService
//                System.out.print("Enter id: ");
//                long id = Long.parseLong(getUserInput());
//                System.out.print("Enter name: ");
//                String name = getUserInput();
//                System.out.print("Enter author: ");
//                String author = getUserInput();
//                break;
//            } else if (input.equals("3")) {
//                authenticationService.logout();
//                writeGreenLine("Logged out.");
//                System.out.println();
//                new LogInView(userService, authenticationService).display();
//                break;
//            } else if (input.equals("0")) {
//                System.out.println("Have a nice day.");
//                break;
//            } else {
//                writeRedLine("Invalid input.");
//            }
//        }
//    }
//
//    private String getRecipeMenuInput() {
//        System.out.println("What do you want to do now?");
//        System.out.println("1: I want to read a recipe");
//        System.out.println("2: I want to create a new recipe");
//        System.out.println("3: I want to log out");
//        System.out.println("0: Close cookbook");
//
//        System.out.print("Your input: ");
//        return getUserInput();
//    }
//}
