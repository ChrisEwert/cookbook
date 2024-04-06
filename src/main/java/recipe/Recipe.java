package recipe;

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

    public Recipe(String name, String author, LocalDate dateOfCreation, String content, List<String> categories, LocalTime cookingTime, float rating) {
        this(
            RecipeRepository.getNextId(),
            name,
            author,
            dateOfCreation,
            content,
            categories,
            cookingTime,
            rating
        );
    }

    //    public static Recipe updateRecipe(Recipe recipe) {
//        return new Recipe(
//            recipe.id(),
//            recipe.name(),
//            recipe.author(),
//            recipe.dateOfCreation(),
//            recipe.content(),
//            recipe.categories(),
//            recipe.cookingTime(),
//            recipe.rating()
//        );
//    }
}
