package si.rso.ratings.services.impl;

import org.bson.Document;
import org.bson.types.ObjectId;
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
    
    @Override
    public List<Rating> getProductRatings(String productId){
        Document document = new Document();
        document.put("productId", productId);
        List<Rating> ratings = mongoService.getDocument(document);
        return ratings;
    }

    @Override
    public AverageRating getAverageRating(String productId) {
        AverageRating averageRating = mongoService.getAverageRating(productId);
        return averageRating;
    }

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

    @Override
    @Transactional
    public Boolean removeRating(String ratingId){
        Document document = new Document();
        document.put("_id", new ObjectId(ratingId));
        mongoService.removeDocument(document);
        return true;
    }
}