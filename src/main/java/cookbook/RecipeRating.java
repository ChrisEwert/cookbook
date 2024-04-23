package cookbook;

public record RecipeRating(
    String recipeId,
    String author,
    int stars,
    String title,
    String comment
) {                                                                                                                     // TODO: toString()
}
