package won.rest;

import won.model.DC;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 */
@Path("/dc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IDCRest {

    @GET
    @Path("is-any-registered")
    public Boolean isAnyRegistered();

    @POST
    public Response create(DC dc);

    @PUT
    public Response update(DC dc);

    @GET
    public List<DC> list();

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Integer id);

    @DELETE
    @Path("{id}")
    public Response destroy(@PathParam("id") Integer id);

    @GET
    @Path("test-connection/{id}")
    public Response testConnection(@PathParam("id") Integer id);
}
