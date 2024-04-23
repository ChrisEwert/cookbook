package cookbook;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public record Recipe(
        String id,
        String name,
        String author,
        LocalDate dateOfCreation,
        List<String> ingredients,
        List<String> content,
        List<String> categories,
        int cookingTimeInMinutes,
        float rating,
        int ratingCount
) {

    public Recipe() {
        this(
            UUID.randomUUID().toString(),
            "",
            "",
            LocalDate.now(),
            List.of(),
            List.of(),
            List.of(),
            0,
            0f,
            0
        );
    }

    public Recipe(String name, String author, List<String> ingredients, List<String> content, List<String> categories, int cookingTimeInMinutes) {
        this(
            UUID.randomUUID().toString(),
            name,
//            CookbookFileRepository.getUsername(),
            author,
            LocalDate.now(),
            ingredients,
            content,
            categories,
            cookingTimeInMinutes,
            0f,
            0
        );
    }

    public Recipe changeRating(float rating, int ratingCount) {
        return new Recipe(
            this.id,
            this.name,
            this.author,
            this.dateOfCreation,
            this.ingredients,
            this.content,
            this.categories,
            this.cookingTimeInMinutes,
            rating,
            ratingCount
        );
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        showRecipeHeader(builder);
        builder.append("\n");

        showRecipeMetadata(builder);
        builder.append("\n");

        showRecipeCookingTime(builder);
        builder.append("\t\t");

        showRecipeCategories(builder);
        builder.append("\n\n");

        showRecipeIngredients(builder);
        builder.append("\n");

        showRecipeCookingSteps(builder);

        builder.append("═══════════════════════════════════════════════════════");

        return builder.toString();
    }

    private void showRecipeHeader(StringBuilder builder) {
        builder.append("═══════════════════════════════════════════════════════\n");
        builder.append(" ").append(name).append("\n");
        builder.append("═══════════════════════════════════════════════════════");
    }

    private void showRecipeMetadata(StringBuilder builder) {
        builder.append(author).append("\t\t");
        showRecipeRating(builder);
        builder.append("/5⭑ [");
        builder.append(ratingCount).append(" rating(s)]\t\t");
        builder.append(dateOfCreation.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }

    private void showRecipeRating(StringBuilder builder) {
        float roundedRating = (float) Math.round(rating * 10) / 10;
        builder.append(roundedRating);
    }

    private void showRecipeCategories(StringBuilder builder) {
        for (int i = 0; i < categories.size() - 1; i++) {
            builder.append(categories.get(i)).append(", ");
        }
        builder.append(categories.get(categories.size() - 1));
    }

    private void showRecipeCookingTime(StringBuilder builder) {
        int hours = cookingTimeInMinutes / 60;
        int remainingMinutes = cookingTimeInMinutes % 60;

        String formattedHours = String.format("%02d", hours);
        String formattedMinutes = String.format("%02d", remainingMinutes);

        String time = formattedHours + ":" + formattedMinutes + " h";

        builder.append(time);
    }

    private void showRecipeIngredients(StringBuilder builder) {
        builder.append("Ingredients:\n");
        for (String ingredient : ingredients) {
            builder.append("- ").append(ingredient).append("\n");
        }
    }

    private void showRecipeCookingSteps(StringBuilder builder) {
        builder.append("Cooking steps:\n");
        for (int i = 0 ; i < content.size() ; i++) {
            builder.append("STEP ").append(i+1).append("\n");
            builder.append("\t").append(content.get(i)).append("\n");
        }
    }
}
