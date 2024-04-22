package cookbook;

public record RecipeRating(
    String recipeId,
    int stars,
    String title,
    String comment                                                                                                      // TODO: Author of comment
) {                                                                                                                     // TODO: toString()
}
