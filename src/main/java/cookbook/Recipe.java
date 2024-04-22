package cookbook;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record Recipe(
        String id,
        String name,
        String author,
        LocalDate dateOfCreation,
        List<String> ingredients,
        List<String> content,
        List<String> categories,
        int cookingTimeInMinutes,
        float rating
) {

    public Recipe() {
        this(
            RecipeRepository.getRandomId(),
            "",
            "",
            LocalDate.now(),
            List.of(),
            List.of(),
            List.of(),
            0,
            0f
        );
    }

    public Recipe(String name, List<String> ingredients, List<String> content, List<String> categories, int cookingTimeInMinutes) {
        this(
            RecipeRepository.getRandomId(),
            name,
            CookbookRepository.getUsername(),
            LocalDate.now(),
            ingredients,
            content,
            categories,
            cookingTimeInMinutes,
            0f
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

        builder.append("═════════════════════════════════════════════");

        return builder.toString();
    }

    private void showRecipeHeader(StringBuilder builder) {
        builder.append("═════════════════════════════════════════════\n");
        builder.append(" ").append(name).append("\n");
        builder.append("═════════════════════════════════════════════");
    }

    private void showRecipeMetadata(StringBuilder builder) {
        builder.append(author).append("\t\t");
        builder.append(rating).append("⭑\t\t");
        builder.append(dateOfCreation.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
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
