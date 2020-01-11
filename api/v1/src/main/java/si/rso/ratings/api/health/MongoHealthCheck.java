package si.rso.ratings.api.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import si.rso.ratings.mongodb.MongoService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class MongoHealthCheck implements HealthCheck {
    
    @Inject
    private MongoService mongoService;
    
    @Override
    public HealthCheckResponse call() {
        try {
            mongoService.healtcheck();
        } catch (Exception e) {
            return HealthCheckResponse.named("MongoHealthCheck").down().build();
        }
        return HealthCheckResponse.named("MongoHealthCheck").up().build();
    }
}
