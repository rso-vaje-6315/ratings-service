package si.rso.ratings.services.impl;

import org.bson.Document;
import si.rso.ratings.lib.Rating;
import si.rso.ratings.mongodb.MongoService;
import si.rso.ratings.services.AdminService;
import si.rso.ratings.services.RatingService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class AdminServiceImpl implements AdminService {
    
    @Inject
    private MongoService mongoService;
    
    @Inject
    private RatingService ratingService;
    
    @Override
    public List<Rating> populateData() {
        String productId = "caa08db8-b2c3-43e8-b419-542126b841bd";
    
        Document document = new Document();
        document.put(Rating.FIELD_PRODUCT_ID, productId);
        document.put(Rating.FIELD_RATING_NUM, 1);
        document.put(Rating.FIELD_COMMENT, "terrible product");
        mongoService.insertDocument(document);
    
        document = new Document();
        document.put(Rating.FIELD_PRODUCT_ID, productId);
        document.put(Rating.FIELD_RATING_NUM, 2);
        document.put(Rating.FIELD_COMMENT, "just bad");
        mongoService.insertDocument(document);
    
        return ratingService.getProductRatings(productId);
    }
    
    @Override
    public void removeData() {
        mongoService.cleanData();
    }
}
