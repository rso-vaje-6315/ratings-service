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

        productId = "89ed42e1-cde9-4495-a838-6011ce71e08c";
        document = new Document();
        document.put(Rating.FIELD_PRODUCT_ID, productId);
        document.put(Rating.FIELD_RATING_NUM, 3);
        document.put(Rating.FIELD_COMMENT, "Average");
        mongoService.insertDocument(document);

        productId = "c1ef6f2c-4a21-477d-b429-aea2abfcc811";
        document = new Document();
        document.put(Rating.FIELD_PRODUCT_ID, productId);
        document.put(Rating.FIELD_RATING_NUM, 3);
        document.put(Rating.FIELD_COMMENT, "Average");
        mongoService.insertDocument(document);

        productId = "8d8c9560-24b2-46bb-a84e-33a83b411e14";
        document = new Document();
        document.put(Rating.FIELD_PRODUCT_ID, productId);
        document.put(Rating.FIELD_RATING_NUM, 3);
        document.put(Rating.FIELD_COMMENT, "Average");
        mongoService.insertDocument(document);

        productId = "5508d970-94e2-4626-827b-4952b63696d6";
        document = new Document();
        document.put(Rating.FIELD_PRODUCT_ID, productId);
        document.put(Rating.FIELD_RATING_NUM, 3);
        document.put(Rating.FIELD_COMMENT, "Average");
        mongoService.insertDocument(document);

        productId = "8b5b7307-9dbe-40b3-b0b7-592ff7e6604f";
        document = new Document();
        document.put(Rating.FIELD_PRODUCT_ID, productId);
        document.put(Rating.FIELD_RATING_NUM, 3);
        document.put(Rating.FIELD_COMMENT, "Average");
        mongoService.insertDocument(document);

        productId = "862d89c4-34b2-4831-b400-474fb89b8a51";
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
