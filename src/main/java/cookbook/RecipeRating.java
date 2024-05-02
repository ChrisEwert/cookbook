package cookbook;

import java.util.UUID;

public record RecipeRating(
    String id,
    String recipeId,
    String author,
    int stars,
    String title,
    String comment
) {
    public RecipeRating(String recipeId, String author, int stars, String title, String comment) {
        this(
            UUID.randomUUID().toString(),
            recipeId,
            author,
            stars,
            title,
            comment
        );
    }

    public RecipeRating updateRating(String author, int stars, String title, String comment) {
        return new RecipeRating(
            this.id(),
            this.recipeId(),
            author,
            stars,
            title,
            comment
        );
    }

    public RecipeRating(String id, String recipeId, String author, int stars, String title, String comment) {
        this.id = id;
        this.recipeId = recipeId;
        this.author = author;
        this.stars = stars;
        this.title = title;
        this.comment = comment;
    }

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
