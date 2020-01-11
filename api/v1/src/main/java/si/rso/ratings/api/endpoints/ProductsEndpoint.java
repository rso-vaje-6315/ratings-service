package si.rso.ratings.api.endpoints;

import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.metrics.annotation.Timed;
import si.rso.ratings.lib.AverageRating;
import si.rso.ratings.lib.Rating;
import si.rso.ratings.services.RatingService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Log
@Path("/products")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductsEndpoint {
    
    @Inject
    private RatingService ratingService;
    
    @GET
    @Path("/{productId}/ratings")
    @Timed(name = "all-product-ratings-query-time")
    public Response getProductRatings(@PathParam("productId") String productId) {
        List<Rating> ratings = ratingService.getProductRatings(productId);
        return Response.ok(ratings).build();
    }
    
    @GET
    @Path("/{productId}/average-rating")
    @Timed(name = "average-rating-query-time")
    public Response getAverageNumberProductRating(@PathParam("productId") String productId) {
        AverageRating averageRating = ratingService.getAverageRating(productId);
        return Response.ok(averageRating).build();
    }
}
