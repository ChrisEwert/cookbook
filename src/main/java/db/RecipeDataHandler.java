package db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cookbook.Recipe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RecipeDataHandler implements DataHandler {
    private final String fileName = "recipes.json";
    private final Path filePath;
    private final List<Recipe> recipes;

    public RecipeDataHandler() {
        this.filePath = Path.of(directoryName, fileName);
        if (!fileExists(filePath)) {
            createFile(filePath);
        }
        recipes = loadRecipesFromFile();
    }

    public List<Recipe> loadRecipesFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Recipe> recipes = new ArrayList<>();

        try {
            byte[] fileBytes = Files.readAllBytes(filePath);
            recipes = objectMapper.readValue(fileBytes, new TypeReference<>() {});
        } catch (IOException e) {
            System.err.println("Failed to load recipes from " + filePath);
        }

        return recipes;
    }
}