package si.rso.ratings.mongodb;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@ApplicationScoped
public class MongoService {
    
    private static final Logger LOG = LogManager.getLogger(MongoService.class.getSimpleName());
    
    private static MongoDatabase database = null;
    private static MongoClient client = null;
    
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
    }
    
    @PostConstruct
    private void init() {
        connect();
    }
    
    @PreDestroy
    private void closeConnection() {
        client.close();
    }
    
    public void insertDocument(Document document, String collection) {
        if (document == null) {
            return;
        }
        database.getCollection(collection).insertOne(document);
    }
    
    public void insertDocument(Document document) {
        this.insertDocument(document, config.getDefaultCollection());
    }
    
    public void cleanData(String collection) {
        database.getCollection(collection).deleteMany(new Document());
    }
    
    public void cleanData() {
        this.cleanData(config.getDefaultCollection());
    }
    
    public void removeDocument(Document document, String collection) {
        if (document == null) {
            return;
        }
        database.getCollection(collection).deleteOne(document);
    }
    
    public void removeDocument(Document document) {
        this.removeDocument(document, config.getDefaultCollection());
    }
    
    public Document getDocument(String documentId, String collection) {
        return database
            .getCollection(collection)
            .find(eq("_id", new ObjectId(documentId)))
            .first();
    }
    
    public Document getDocument(String documentId) {
        return this.getDocument(documentId, config.getDefaultCollection());
    }
    
    public MongoCursor<Document> getDocuments(Document document) {
        return this.getDocuments(document, config.getDefaultCollection());
    }
    
    public MongoCursor<Document> getDocuments(Document document, String collection) {
        return database.getCollection(collection).find(document).iterator();
    }
    
    public AggregateIterable<Document> getAggregatedData(List<Bson> aggregateBy, String collection) {
        return database.getCollection(collection).aggregate(aggregateBy);
    }
    
    public AggregateIterable<Document> getAggregatedData(List<Bson> aggregateBy) {
        return this.getAggregatedData(aggregateBy, config.getDefaultCollection());
    }
    
    public void healtcheck() {
        database.getCollection(config.getDefaultCollection()).find(new Document()).limit(1);
    }
}
