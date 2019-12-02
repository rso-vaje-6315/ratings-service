package si.rso.ratings.api.endpoints;

import si.rso.ratings.lib.Rating;
import si.rso.ratings.services.RatingService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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
    public Response getProductRating(@PathParam("productId") String productId) {
        Rating rating = ratingService.getProductRating(productId);
        return Response.ok(rating).build();
    }

}
