package cookbook;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public interface CookbookRepository {

    String getCurrentUsername();

    void setCurrentUsername(String username);

    LocalDate getCreationDate();

    Map<String, CookbookUser> getAllUsers();

    CookbookUser getUserByUsername(String username);

    void createNewUser(String username, CookbookUser user);

    Set<String> getBookmarkedRecipeIdsByUsername(String username);

    void addBookmarkedRecipeId(String username, String recipeId);
}
