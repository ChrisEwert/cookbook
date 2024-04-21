package cookbook;

import java.util.Set;

public record User(
    String username,
    String password,
    Set<String> bookmarkedRecipeIds
) {
}
