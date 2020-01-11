package si.rso.ratings.api.mappers;

import si.rso.rest.exceptions.RestException;
import si.rso.rest.exceptions.dto.ExceptionResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<RestException> {

    @Override
    public Response toResponse(RestException exception) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatusCode(exception.getStatus());
        response.setMessage(exception.getMessage());
        
        return Response.status(exception.getStatus()).entity(response).build();
    }

}