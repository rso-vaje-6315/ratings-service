package si.rso.ratings.services.impl;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import si.rso.ratings.lib.AverageRating;
import si.rso.ratings.mappers.RatingsMapper;
import si.rso.ratings.mongodb.MongoService;
import si.rso.ratings.services.RatingService;
import si.rso.ratings.lib.Rating;
import si.rso.rest.exceptions.NotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class RatingServiceImpl implements RatingService {

    @Inject
    private MongoService mongoService;
    
    @Override
    public Rating getRating(String ratingId) {
        Document document = mongoService.getDocument(ratingId);
        if (document == null) {
            throw new NotFoundException("Rating not found!");
        }
        return RatingsMapper.fromDocument(document);
    }
    
    @Retry
    @Timeout
    @CircuitBreaker
    @Override
    public List<Rating> getProductRatings(String productId){
        Document document = new Document();
        document.put(Rating.FIELD_PRODUCT_ID, productId);
    
        List<Rating> ratings = new ArrayList<>();
        MongoCursor<Document> documentsIterator = mongoService.getDocuments(document);
        
        while(documentsIterator.hasNext()) {
            Document currentDocument = documentsIterator.next();
            Rating rating = RatingsMapper.fromDocument(currentDocument);
            ratings.add(rating);
        }
        
        return ratings;
    }

    @Retry
    @Timeout
    @CircuitBreaker
    @Override
    public AverageRating getAverageRating(String productId) {
    
        AggregateIterable<Document> aggregate = mongoService.getAggregatedData(Arrays.asList(
            Aggregates.match(Filters.eq(Rating.FIELD_PRODUCT_ID, productId)),
            Aggregates.group("$avg", Accumulators.avg(Rating.FIELD_RATING_NUM, "$ratingNumber"))
        ));
        
        if (aggregate.first() == null) {
            throw new NotFoundException("Rating for product not found!");
        }
    
        AverageRating averageRating = new AverageRating();
        averageRating.setProductId(productId);
        averageRating.setAverageRatingNumber(aggregate.first().getDouble(Rating.FIELD_RATING_NUM));
        return averageRating;
    }

    @Retry
    @Timeout
    @CircuitBreaker
    @Override
    @Transactional
    public Rating addRating(Rating rating) {
        Document document = RatingsMapper.toDocument(rating);
        mongoService.insertDocument(document);
        return RatingsMapper.fromDocument(document);
    }

    @Retry
    @Timeout
    @CircuitBreaker
    @Override
    @Transactional
    public void removeRating(String ratingId){
        Document document = new Document();
        document.put(Rating.FIELD_ID, new ObjectId(ratingId));
        mongoService.removeDocument(document);
    }
}