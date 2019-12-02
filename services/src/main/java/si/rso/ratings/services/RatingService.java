package si.rso.ratings.services;

import si.rso.ratings.lib.Rating;

public interface RatingService {
    
    Rating getProductRating(String productId);
}