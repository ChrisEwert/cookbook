package cookbook;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public interface CookbookRepository {

    String getCurrentUsername();

    void setCurrentUsername(String username);

    LocalDate getCreationDate();

    Map<String, CookbookUser> getAllUsers();

    void createNewUser(CookbookUser user);

    Set<String> getBookmarkedRecipeIdsByUsername(String username);

    void addBookmarkedRecipeId(String username, String recipeId);
}
