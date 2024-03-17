package recipe;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record Recipe(
        long id,
        String name,
        String author,
        LocalDate dateOfCreation,
        LocalDateTime lastUpdated,
        String content,
        List<String> categories,
        LocalTime cookingTime,
        int rating
) {
    public Recipe() {
        this(CookBook.getNextId(), "Recipe name", "Author", LocalDate.now(), null, "Recipe content", List.of(), LocalTime.now(), 0);
    }

    public Recipe(long id, String name, String author, LocalDate dateOfCreation, LocalDateTime lastUpdated, String content, List<String> categories, LocalTime cookingTime, int rating) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.dateOfCreation = dateOfCreation;
        this.lastUpdated = lastUpdated;
        this.content = content;
        this.categories = categories;
        this.cookingTime = cookingTime;
        this.rating = rating;
    }
}
