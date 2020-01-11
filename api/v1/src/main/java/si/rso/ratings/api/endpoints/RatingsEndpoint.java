package si.rso.ratings.api.endpoints;

import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.metrics.annotation.Timed;
import si.rso.ratings.lib.Rating;
import si.rso.ratings.services.RatingService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log
@Path("/ratings")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RatingsEndpoint {
    
    @Inject
    private RatingService ratingService;
    
    @GET
    @Path("/{id}")
    public Response getRating(@PathParam("id") String ratingId) {
        Rating rating = ratingService.getRating(ratingId);
        return Response.ok(rating).build();
    }
    
    @POST
    @Timed(name = "rating-addition-time")
    public Response addRating(Rating rating) {
        Rating newRating = ratingService.addRating(rating);
        return Response.status(Response.Status.CREATED).entity(newRating).build();
    }
    
    @DELETE
    @Path("/{ratingId}")
    @Timed(name = "rating-removal-time")
    public Response removeRating(@PathParam("ratingId") String ratingId) {
        ratingService.removeRating(ratingId);
        return Response.noContent().build();
    }
}
