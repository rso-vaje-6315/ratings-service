package si.rso.ratings.services;

import si.rso.ratings.lib.AverageRating;
import si.rso.ratings.lib.Rating;

import java.util.List;

public interface RatingService {
    
    List<Rating> getProductRatings(String productId);

    AverageRating getAverageRating(String productId);

    Rating addRating(Rating rating);

    Boolean removeRating(String ratingId);
}