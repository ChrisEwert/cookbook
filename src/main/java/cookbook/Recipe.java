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
        int cookingTimeInMinutes,
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
            0,
            0f
        );
    }

    public Recipe(String name, String content, List<String> categories, int cookingTimeInMinutes) {
        this(
            RecipeRepository.getNextId(),
            name,
            CookbookRepository.getUser(),
            LocalDate.now(),
            content,
            categories,
            cookingTimeInMinutes,
            0f
        );
    }
}
