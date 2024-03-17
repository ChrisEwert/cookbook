package recipe;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class CookBook {
    private String name;
    private LocalDate dateOfCreation;
    private final static AtomicLong ID_COUNTER = new AtomicLong();
    private final Map<Long, Recipe> recipes = new HashMap<>();
    private String user;

    public static long getNextId() {
        return ID_COUNTER.incrementAndGet();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Map<Long, Recipe> getRecipes() {
        return recipes;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
