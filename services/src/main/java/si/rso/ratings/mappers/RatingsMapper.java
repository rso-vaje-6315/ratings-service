package si.rso.ratings.mappers;

import org.bson.Document;
import si.rso.ratings.lib.Rating;


public class RatingsMapper {
    
    public static Rating fromDocument(Document document) {
        Rating rating = new Rating();
        rating.setId(document.getObjectId(Rating.FIELD_ID).toString());
        rating.setComment(document.getString(Rating.FIELD_COMMENT));
        rating.setRatingNumber(document.getInteger(Rating.FIELD_RATING_NUM));
        rating.setProductId(document.getString(Rating.FIELD_PRODUCT_ID));
        
        return rating;
    }
    
    public static Document toDocument(Rating rating) {
        Document doc = new Document();
        doc.put(Rating.FIELD_COMMENT, rating.getComment());
        doc.put(Rating.FIELD_RATING_NUM, rating.getRatingNumber());
        doc.put(Rating.FIELD_PRODUCT_ID, rating.getProductId());
        return doc;
    }
    
}
