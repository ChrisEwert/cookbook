package views;

import services.AuthenticationService;
import services.UserService;

public class ShowRecipesView implements View {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public ShowRecipesView(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @Override
    public void display() {
        writeYellowLine("Here is a list of all the recipes");
        listRecipes();
    }

    private void listRecipes() {
        // TODO
    }
}
