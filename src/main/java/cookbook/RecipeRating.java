package cookbook;

public record RecipeRating(
    String recipeId,
    String author,
    int stars,
    String title,
    String comment
) {
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        showTopRow(builder);
        builder.append("\n");

        builder.append(title.toUpperCase());
        builder.append("\n");

        builder.append(comment);
        builder.append("\n");
        builder.append("───────────────────");

        return builder.toString();
    }

    private void showTopRow(StringBuilder builder) {
        builder.append("───────────────────\n");
        builder.append(stars);
        builder.append("⭑");
        builder.append("\t\t\t");
        builder.append(author);
    }
}
