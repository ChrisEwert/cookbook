package cookbook.recipe;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record Recipe(
        long id,
        String name,
        String author,
        LocalDate dateOfCreation,
        LocalDateTime lastUpdated,
        String content,
        List<String> categories,
        LocalTime cookingTime,
        int rating
) {
    public Recipe() {
        this(
            CookBook.getNextId(),
            "Recipe name",
            "Author",
            LocalDate.now(),
            null,
            "Recipe content",
            List.of(),
            LocalTime.of(0, 0),
            0
        );
    }

    public Recipe(String name, String content, List<String> categories, LocalTime cookingTime) {
        this(
            CookBook.getNextId(),
            name,
            CookBook.getUser(),
            LocalDate.now(),
            null,
            content,
            categories,
            cookingTime,
            0
        );
    }

    public static Recipe updatedRecipe(Recipe recipe) {
        return new Recipe(
            recipe.id(),
            recipe.name(),
            recipe.author(),
            recipe.dateOfCreation(),
            LocalDateTime.now(),
            recipe.content(),
            recipe.categories(),
            recipe.cookingTime(),
            recipe.rating()
        );
    }
}
