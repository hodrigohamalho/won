package won.rest;

import won.model.CLI;
import won.model.DC;
import won.util.HTTPUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 */
@Path("/dc")
@Stateless
public class DCRest {

    private String errorMessage = "";

    @Inject
    private EntityManager em;

    @GET
    @Path("is-any-registered")
    @Produces("application/json")
    public Boolean isAnyRegistered(){
        try{
            em.createQuery("select 1 from DC").setMaxResults(1).getSingleResult();
            return true;
        }catch(NoResultException nre){
            return false;
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(DC dc) {
        try{
            dc.setId(null);
            em.persist(dc);
        }catch (Exception e){
            e.printStackTrace();
            return throwError(e.getMessage());
        }

        return success(dc);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(DC dc){
        if (dc != null && dc.getId() != null && dc.getId() != -1){
            em.merge(dc);
        }else{
            return throwError("You need to pass a existing DC as parameter to update");
        }

        return success(dc);
    }

    @GET
    @Produces("application/json")
    public List<DC> list(){
        try{
            Query query = em.createQuery("SELECT d FROM DC d");
            return (List<DC>) query.getResultList();
        }catch(NullPointerException nre){
            return new ArrayList<DC>();
        }
    }

    @Path("{id}")
    @GET
    @Produces("application/json")
    public Response find(@PathParam("id") Integer id){
        DC dc;

        if (id != null){
            try{
               dc = em.find(DC.class, id);
            }catch (Exception e){
                return throwError(e.getMessage());
            }
        }else{
            return throwError("ID cannot be null");
        }

        return success(dc);
    }

    @Path("{id}")
    @DELETE
    public Response destroy(@PathParam("id") Integer id){
        if (id != null){
            DC dc = em.find(DC.class, id);

            if (dc != null){
                em.remove(dc);
            }else{
                return throwError("No results found for id "+id);
            }
        }else{
            return throwError("ID cannot be null");
        }

        return success();
    }

    @Path("test-connection/{id}")
    @GET
    public Response testConnection(@PathParam("id") Integer id){
        if (id != null){
            DC dc = em.find(DC.class, id);

            if (dc != null){
                CLI cli = new CLI("", false, false);

                try {
                    String json = HTTPUtil.retrieveJSONFromDC(dc, cli);
                    return json != "" ? success(true) : success(false);
                } catch (Exception e) {
                    e.printStackTrace();
                    return throwError(e.getMessage());
                }
            }else{
                return throwError("No results found for id "+id);
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

    private Response throwError(String message) {
        return Response.serverError().entity(message).build();
    }

}
