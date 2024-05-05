package cookbook;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class RecipeBuilder {
    private String id;
    private String name;
    private String author;
    private LocalDate dateOfCreation;
    private List<String> ingredients;
    private List<String> content;
    private List<String> categories;
    private int cookingTimeInMinutes;
    private float rating;
    private int ratingCount;

    public RecipeBuilder() {
        this.id = UUID.randomUUID().toString();
        this.name = "";
        this.author = "";
        this.dateOfCreation = LocalDate.now();
        this.ingredients = List.of();
        this.content = List.of();
        this.categories = List.of();
        this.cookingTimeInMinutes = 0;
        this.rating = 0f;
        this.ratingCount = 0;
    }

    public RecipeBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public RecipeBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RecipeBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public RecipeBuilder withDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
        return this;
    }

    public RecipeBuilder withIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public RecipeBuilder withContent(List<String> content) {
        this.content = content;
        return this;
    }

    public RecipeBuilder withCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }

    public RecipeBuilder withCookingTimeInMinutes(int cookingTimeInMinutes) {
        this.cookingTimeInMinutes = cookingTimeInMinutes;
        return this;
    }

    public RecipeBuilder withRating(float rating) {
        this.rating = rating;
        return this;
    }

    public RecipeBuilder withRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
        return this;
    }

    public Recipe build() {
        return new Recipe(
            id,
            name,
            author,
            dateOfCreation,
            ingredients,
            content,
            categories,
            cookingTimeInMinutes,
            rating,
            ratingCount
        );
    }
}
