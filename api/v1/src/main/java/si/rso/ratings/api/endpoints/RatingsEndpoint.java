package si.rso.ratings.api.endpoints;

import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.metrics.annotation.Timed;
import si.rso.ratings.lib.AverageRating;
import si.rso.ratings.lib.Rating;
import si.rso.ratings.services.RatingService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Log
@Path("/ratings")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RatingsEndpoint {
    
    @Inject
    private RatingService ratingService;
    
    @Context
    protected UriInfo uriInfo;
    
    @GET
    @Path("/{productId}")
    @Timed(name = "all-product-ratings-query-time")
    public Response getProductRatings(@PathParam("productId") String productId) {
        List<Rating> ratings = ratingService.getProductRatings(productId);
        return Response.ok(ratings).build();
    }

    @GET
    @Path("/generate")
    public Response getProductRatings() {
        List<Rating> ratings = ratingService.generateProductRatings();
        return Response.ok(ratings).build();
    }

    @GET
    @Path("/averageStarRating/{productId}")
    @Timed(name = "average-rating-query-time")
    public Response getAverageNumberProductRating(@PathParam("productId") String productId) {
        AverageRating averageRating = ratingService.getAverageRating(productId);
        return Response.ok(averageRating).build();
    }

    @POST
    @Path("/")
    @Timed(name = "rating-addition-time")
    public Response addRating(Rating rating) {
        try {
            Rating newRating = ratingService.addRating(rating);
            return Response.ok(newRating).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{ratingId}")
    @Timed(name = "rating-removal-time")
    public Response removeRating(@PathParam("ratingId") String ratingId) {
        try {
            Boolean ratingRemoved = ratingService.removeRating(ratingId);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
