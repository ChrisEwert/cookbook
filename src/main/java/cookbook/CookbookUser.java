package cookbook;

import java.util.Set;

public record CookbookUser(
    String username,
    String password,
    Set<String> bookmarkedRecipeIds
) {
}
