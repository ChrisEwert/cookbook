package services;

import cookbook.CookbookRepository;

public class AuthenticationService {
    private final CookbookRepository cookbookRepository;

    public AuthenticationService(CookbookRepository cookbookRepository) {
        this.cookbookRepository = cookbookRepository;
    }

    public void login(String user) {
        cookbookRepository.setUser(user);
    }

    public void logout() {
        cookbookRepository.setUser(null);
    }
}
