package cookbook;

import java.time.LocalDate;

public record Cookbook(
        LocalDate dateOfCreation,
        String username
) {

    public Cookbook() {
        this(
            LocalDate.now(),
            null
        );
    }

    public Cookbook changeUser(String user) {
        return new Cookbook(
            this.dateOfCreation(),
            user
        );
    }
}
