package cookbook;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record Recipe(
        long id,
        String name,
        String author,
        LocalDate dateOfCreation,
        String content,
        List<String> categories,
        LocalTime cookingTime,
        float rating
) {

    public Recipe() {
        this(
            RecipeRepository.getNextId(),
            "Recipe name",
            "Recipe Author",
            LocalDate.now(),
            "Recipe content",
            List.of(),
            LocalTime.of(0, 0),
            0f
        );
    }

    // TODO: Remove this constructor (only needed for current state of the RecipeDateHandler)
    public Recipe(long id, String name, String author) {
        this(
            id,
            name,
            author,
            LocalDate.now(),
            "content",
            List.of(),
            LocalTime.of(0, 0),
            0f
        );
    }

    // TODO: add the whole recipe logic
}
