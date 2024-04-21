package cookbook;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record Recipe(
        long id,
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
            RecipeRepository.getNextId(),
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
            RecipeRepository.getNextId(),
            name,
            CookbookRepository.getUser(),
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

        builder.append("═════════════════════════════════════════════\n");
        builder.append(" ").append(name).append("\n");
        builder.append("═════════════════════════════════════════════\n");

        builder.append(author).append("\t");
        builder.append(rating).append("⭑\t");
        builder.append(dateOfCreation.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))).append("\n");

        for (int i = 0; i < categories.size() - 1; i++) {
            builder.append(categories.get(i)).append(", ");
        }
        builder.append(categories.get(categories.size() - 1)).append("\t");
        builder.append(formatTime(cookingTimeInMinutes)).append(" hours\n\n");

        for (String ingredient : ingredients) {
            builder.append("- ").append(ingredient).append("\n");
        }
        builder.append("\n");

        for (int i = 0 ; i < content.size() ; i++) {
            builder.append("STEP ").append(i).append("\n");
            builder.append("\t").append(content.get(i)).append("\n");
        }

        builder.append("═════════════════════════════════════════════");

        return builder.toString();
    }

    public String formatTime(int minutes) {
        int hours = minutes / 60;
        int remainingMinutes = minutes % 60;

        String formattedHours = String.format("%02d", hours);
        String formattedMinutes = String.format("%02d", remainingMinutes);

        return formattedHours + ":" + formattedMinutes;
    }
}
