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
        String productId = "d519b320-80a3-40e2-ad96-7edd8c878630";
    
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

        productId = "fbace5c1-653c-42c0-aa02-78cc4ea4fac1";
        document = new Document();
        document.put(Rating.FIELD_PRODUCT_ID, productId);
        document.put(Rating.FIELD_RATING_NUM, 5);
        document.put(Rating.FIELD_COMMENT, "Excellent");
        mongoService.insertDocument(document);

        document = new Document();
        document.put(Rating.FIELD_PRODUCT_ID, productId);
        document.put(Rating.FIELD_RATING_NUM, 4);
        document.put(Rating.FIELD_COMMENT, "Great");
        mongoService.insertDocument(document);

        productId = "66100dac-ff08-4ac2-9c1a-fa5120ff4838";
        document = new Document();
        document.put(Rating.FIELD_PRODUCT_ID, productId);
        document.put(Rating.FIELD_RATING_NUM, 3);
        document.put(Rating.FIELD_COMMENT, "Average");
        mongoService.insertDocument(document);

        document = new Document();
        document.put(Rating.FIELD_PRODUCT_ID, productId);
        document.put(Rating.FIELD_RATING_NUM, 2);
        document.put(Rating.FIELD_COMMENT, "Poor packaging");
        mongoService.insertDocument(document);

        productId = "18c18291-6541-4317-a374-b9a03b65b90a";
        document = new Document();
        document.put(Rating.FIELD_PRODUCT_ID, productId);
        document.put(Rating.FIELD_RATING_NUM, 3);
        document.put(Rating.FIELD_COMMENT, "Average");
        mongoService.insertDocument(document);
    
        return ratingService.getProductRatings(productId);
    }
    
    @Override
    public void removeData() {
        mongoService.cleanData();
    }
}
