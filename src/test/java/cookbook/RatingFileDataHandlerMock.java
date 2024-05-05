package cookbook;

import db.RatingFileDataHandler;

import java.util.HashMap;
import java.util.Map;

public class RatingFileDataHandlerMock extends RatingFileDataHandler {
    Map<String, RecipeRating> ratings;

    public RatingFileDataHandlerMock() {
        this.ratings = new HashMap<>();
        RecipeRating rating1 = new RecipeRating("Recipe ID 1", "John", 1, "Title 1", "Comment 1");
        RecipeRating rating2 = new RecipeRating("Recipe ID 2", "Jane", 2, "Title 2", "Comment 2");
        RecipeRating rating3 = new RecipeRating("Recipe ID 2", "Judy", 3, "Title 3", "Comment 3");
        ratings.put(rating1.id(), rating1);
        ratings.put(rating2.id(), rating2);
        ratings.put(rating3.id(), rating3);
    }

    @Override
    public Map<String, RecipeRating> getAllRatingsFromDB() {
        return ratings;
    }

    @Override
    public RecipeRating getRatingById(String id) {
        return ratings.get(id);
    }

    @Override
    public void addRatingToDB(String id, RecipeRating rating) {
        ratings.put(id, rating);
    }

    @Override
    public void updateRatingInDB(String id, RecipeRating newRating) {
        ratings.put(id, newRating);
    }

    @Override
    public void deleteRatingFromDB(String id) {
        ratings.remove(id);
    }
}
