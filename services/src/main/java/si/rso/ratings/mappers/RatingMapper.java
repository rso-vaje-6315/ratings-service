package si.rso.ratings.mappers;

import si.rso.ratings.lib.Rating;
import si.rso.ratings.persistence.RatingEntity;

public class RatingMapper {
    
    public static Rating fromEntity(RatingEntity entity) {
        Rating rating = new Rating();
        rating.setId(entity.getId());
        rating.setTimestamp(entity.getTimestamp());
        rating.setComments(entity.getComments());
        rating.setRatingNumber(entity.getRatingNumber());
        rating.setProductId(entity.getProductId());
        return rating;
    }
    
}