package cookbook;

import java.time.LocalDate;

public record Cookbook(
        LocalDate dateOfCreation,
        String currentUsername
) {

    public Cookbook() {
        this(
            LocalDate.now(),
            null
        );
    }

    public Cookbook changeCurrentUsername(String newUsername) {
        return new Cookbook(
            this.dateOfCreation(),
            newUsername
        );
    }
}
