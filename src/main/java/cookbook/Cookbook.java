package cookbook;

import java.time.LocalDate;

public class Cookbook {
    private static Cookbook instance;
    private final LocalDate dateOfCreation;
    private String currentUsername;

    public Cookbook() {
        this.dateOfCreation = LocalDate.now();
        this.currentUsername = "";
    }

    public Cookbook(LocalDate dateOfCreation, String currentUsername) {
        this.dateOfCreation = dateOfCreation;
        this.currentUsername = currentUsername;
    }

    public static Cookbook getInstance() {
        if (instance == null) {
            synchronized (Cookbook.class) {
                if (instance == null) {
                    instance = new Cookbook(LocalDate.now(), null);
                }
            }
        }
        return instance;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }
}
