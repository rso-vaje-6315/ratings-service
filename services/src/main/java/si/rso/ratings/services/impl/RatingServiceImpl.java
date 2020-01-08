package si.rso.ratings.services.impl;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import si.rso.ratings.lib.AverageRating;
import si.rso.ratings.mongodb.MongoService;
import si.rso.ratings.services.RatingService;
import si.rso.ratings.lib.Rating;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class RatingServiceImpl implements RatingService {

    private EntityManager em;

    @Inject
    private MongoService mongoService;

    @Retry
    @Timeout
    @CircuitBreaker
    @Override
    public List<Rating> getProductRatings(String productId){
        Document document = new Document();
        document.put("productId", productId);
        List<Rating> ratings = mongoService.getDocument(document);
        return ratings;
    }

    @Retry
    @Timeout
    @CircuitBreaker
    @Override
    public List<Rating> generateProductRatings(){
        Document document = new Document();
        document.put("productId", "caa08db8-b2c3-43e8-b419-542126b841bd");
        document.put("ratingNumber", 1);
        document.put("comment", "terrible product");
        mongoService.insertDocument(document);

        document = new Document();
        document.put("productId", "caa08db8-b2c3-43e8-b419-542126b841bd");
        document.put("ratingNumber", 2);
        document.put("comment", "just bad");
        mongoService.insertDocument(document);

        document = new Document();
        document.put("productId", "caa08db8-b2c3-43e8-b419-542126b841bd");
        List<Rating> ratings = mongoService.getDocument(document);

        return ratings;
    }

    @Retry
    @Timeout
    @CircuitBreaker
    @Override
    public AverageRating getAverageRating(String productId) {
        AverageRating averageRating = mongoService.getAverageRating(productId);
        return averageRating;
    }

    @Retry
    @Timeout
    @CircuitBreaker
    @Override
    @Transactional
    public Rating addRating(Rating rating) {
        Document document = new Document();
        document.put("productId", rating.getProductId());
        document.put("ratingNumber", rating.getRatingNumber());
        document.put("comment", rating.getComment());
        mongoService.insertDocument(document);
        return rating;
    }

    @Retry
    @Timeout
    @CircuitBreaker
    @Override
    @Transactional
    public Boolean removeRating(String ratingId){
        Document document = new Document();
        document.put("_id", new ObjectId(ratingId));
        mongoService.removeDocument(document);
        return true;
    }
}