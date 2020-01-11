package si.rso.ratings.services;

import si.rso.ratings.lib.AverageRating;
import si.rso.ratings.lib.Rating;

import java.util.List;

public interface RatingService {
    
    Rating getRating(String ratingId);
    
    List<Rating> getProductRatings(String productId);

    AverageRating getAverageRating(String productId);

    Rating addRating(Rating rating);

    void removeRating(String ratingId);
}