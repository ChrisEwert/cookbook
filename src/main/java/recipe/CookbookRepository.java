package recipe;

import java.util.Map;

public class CookbookRepository {
    private static Cookbook cookbook = new Cookbook();

    public static void setUser(String user) {
        cookbook = new Cookbook(
            cookbook.dateOfCreation(),
            cookbook.recipes(),
            user
        );
    }

    public static String getUser() {
        return cookbook.user();
    }

    public static void setRecipes(Map<Long, Recipe> recipes) {
        cookbook = new Cookbook(
            cookbook.dateOfCreation(),
            recipes,
            cookbook.user()
        );
    }
}