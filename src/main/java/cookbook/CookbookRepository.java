package cookbook;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface CookbookRepository {

    String getUsername();

    void setUsername(String username);

    LocalDate getCreationDate();

    void saveUser(CookbookUser user);

    List<CookbookUser> getUserList();

    void bookmarkRecipeById(String id);

    Set<String> getBookmarkedRecipeIds();

}
