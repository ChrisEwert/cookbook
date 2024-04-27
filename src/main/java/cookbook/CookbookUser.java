package cookbook;

import java.util.HashSet;
import java.util.Set;

public record CookbookUser(
    String username,
    String password,
    Set<String> bookmarkedRecipeIds
) {

    public CookbookUser() {
        this(
            "",
            "",
            new HashSet<>()
        );
    }

    public CookbookUser(String username, String password) {
        this(
            username,
            password,
            new HashSet<>()
        );
    }

    public CookbookUser updateBookmarkedRecipeIds(Set<String> newBookmarkedRecipeIds) {
        return new CookbookUser(
            this.username(),
            this.password(),
            newBookmarkedRecipeIds
        );
    }
}
