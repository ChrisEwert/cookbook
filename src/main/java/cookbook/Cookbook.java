package cookbook;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public record Cookbook(
        LocalDate dateOfCreation,
        Map<Long, Recipe> recipes,
        String user
) {

    public Cookbook() {
        this(
            LocalDate.now(),
            new HashMap<>(),
            null
        );
    }

    public Cookbook changeUser(String user) {
        return new Cookbook(
            this.dateOfCreation(),
            this.recipes(),
            user
        );
    }

//    public Cookbook(LocalDate dateOfCreation, List<Recipe> recipes, String user) {
//        this.dateOfCreation = dateOfCreation;
//        this.recipes = recipes;
//        this.user = user;
//    }
}
