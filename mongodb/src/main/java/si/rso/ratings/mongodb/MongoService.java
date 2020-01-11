package si.rso.ratings.mongodb;
import java.util.*;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import si.rso.ratings.lib.AverageRating;
import si.rso.ratings.lib.Rating;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MongoService {
    private static MongoDatabase database = null;
    private static MongoClient client = null;
    private static MongoCollection defaultCollection = null;

    @Inject
    private MongoConfig config;

    private void connect() {
        MongoCredential credential = MongoCredential.createCredential(config.getUsername(), "admin", config.getPassword().toCharArray());
        client = MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(Collections.singletonList(new ServerAddress(config.getHost(), config.getPort()))))
                        .credential(credential)
                        .build());
        database = client.getDatabase(config.getDatabase());
        defaultCollection = database.getCollection(config.getDefaultCollection());
    }

    @PostConstruct
    private void init () {
        connect();
    }

    @PreDestroy
    private void closeConnection() {
        client.close();
    }

    public void insertDocument(Document document) {
        if (document == null) return;
        defaultCollection.insertOne(document);

    }
    
    public void checkStatus() {
        if (!databaseExists(config.getDatabase())) {
            throw new RuntimeException("Mongo not connected!");
        }
    }
    
    public boolean databaseExists(String database) {
        if (client != null && database != null) {
            for (String s : client.listDatabaseNames()) {
                if (s.equals(database))
                    return true;
            }
        }
        return false;
    }

    public void removeDocument(Document document) {
        if (document == null) return;
        defaultCollection.deleteOne(document);
    }

    public List<Rating> getDocument(Document document) {
        if (document == null) return null;
        List<Rating> ratings = new LinkedList<Rating>();
        MongoCursor<Document> cursor = defaultCollection.find(document).iterator();
        if (cursor == null) {
            return null;
        }
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            Rating rating = new Rating();
            rating.setId(doc.getObjectId(("_id")).toString());
            rating.setComment(doc.getString("comment"));
            rating.setRatingNumber(doc.getInteger("ratingNumber"));
            rating.setProductId(doc.getString("productId"));
            ratings.add(rating);
        }
        return ratings;
    }

    public AverageRating getAverageRating(String productId) {
        AggregateIterable<Document> doc = defaultCollection.aggregate(Arrays.asList(
                Aggregates.match(Filters.eq("productId", productId)),
                Aggregates.group("$avg", Accumulators.avg("ratingNumber", "$ratingNumber"))
        ));
        if (doc == null || doc.first() == null) {
            return null;
        }
        AverageRating averageRating = new AverageRating();
        averageRating.setProductId(productId);
        averageRating.setAverageRatingNumber(doc.first().getDouble("ratingNumber"));
        return averageRating;
    }
}
