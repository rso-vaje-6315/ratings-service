package si.rso.ratings.api.context;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import org.bson.Document;
import si.rso.ratings.mongodb.MongoService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@ApplicationScoped
public class StartupContextListener implements ServletContextListener {
    
    private static final Logger LOG = LogManager.getLogger(StartupContextListener.class.getSimpleName());
    
    @Inject
    private MongoService mongoService;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            mongoService.healtcheck();
            LOG.info("MongoDB connected!");
        } catch (Exception e) {
            LOG.error("Error connecting to MongoDB!");
            throw new RuntimeException("Error connection to Mongo!", e);
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    
    }
}
