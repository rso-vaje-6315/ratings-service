package si.rso.ratings.api.endpoints;

import com.kumuluz.ee.logs.cdi.Log;
import si.rso.ratings.lib.Rating;
import si.rso.ratings.services.AdminService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Log
@Path("/admin")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminResource {
    
    @Inject
    private AdminService adminService;
    
    @POST
    @Path("/generate-data")
    public Response generateDummyData() {
        List<Rating> ratings = adminService.populateData();
        return Response.ok(ratings).build();
    }
    
    @DELETE
    @Path("/empty-data")
    public Response cleanData() {
        adminService.removeData();
        return Response.noContent().build();
    }
    
}
