package won.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import won.model.CLI;
import won.model.DC;
import won.repository.DCRepository;
import won.util.HTTPUtil;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 */
@Path("/dc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DCRest{
	
	@Inject
	private DCRepository repository;

    @GET
    @Path("is-any-registered")
    public Boolean isAnyRegistered(){
       return repository.isAnyRegistered();
    }

    @POST
    public Response create(DC dc) {
    	try{
    		repository.save(dc);
    	}catch(Exception e){
            e.printStackTrace();
            return throwError(e.getMessage());
        }
    	
        return success(dc);
    }

    @PUT
    @Path("/{id:[0-9][0-9]*}")
    public Response update(@PathParam("id") Integer id, DC dc){
    	repository.save(dc);
    	return success(dc);
    }

    @GET
    public List<DC> list(){
    	return repository.findAll();
    }


    @GET
    @Path("/{id:[0-9][0-9]*}")
    public Response find(@PathParam("id") Integer id){
        DC dc;

        if (id != null){
               dc = repository.find(id);
        }else{
            return throwError("ID cannot be null");
        }

        return success(dc);
    }


    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response destroy(@PathParam("id") Integer id){
        if (id != null){
            repository.remove(id);
        }else{
            return notFound();
        }

        return success();
    }

    @GET
    @Path("test-connection/{id:[0-9][0-9]*}")
    public Response testConnection(@PathParam("id") Integer id){
        if (id != null){
            DC dc = repository.find(id);

            if (dc == null)
                return notFound();

                CLI cli = new CLI("", false, false);

                try {
                    JSONObject json = HTTPUtil.retrieveJSONFromDC(dc, cli);
                    return (json != null && !json.toString().isEmpty()) ? success(true) : success(false);
                } catch (Exception e) {
                    e.printStackTrace();
                    return throwError(e.getMessage());
                }
        }else{
            return throwError("ID cannot be null");
        }
    }

    private Response success(){
        return Response.ok().build();
    }

    private Response success(Object object){
        return Response.ok().entity(object).build();
    }

    private Response notFound(){
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    private Response throwError(String message) {
        return Response.serverError().entity(message).build();
    }

}
