package recipe;

public class CookbookRepository {
    private Cookbook cookbook = new Cookbook();
//
    public void setUser(String user) {
        cookbook = cookbook.changeUser(user);
    }
//
//    public String getUser() {
//        return cookbook.user();
//    }
//
//    public void setRecipes(Map<Long, Recipe> recipes) {
//        cookbook = new Cookbook(
//            cookbook.dateOfCreation(),
//            recipes,
//            cookbook.user()
//        );
//    }
}