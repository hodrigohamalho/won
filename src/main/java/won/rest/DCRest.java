package won.rest;

import won.model.DC;
import won.util.HTTPUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 */
@Path("/dc")
@Stateless
public class DCRest {

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
    public DC create(DC dc) {
        if (dc != null && dc.getId() != null && dc.getId() == -1){
            dc.setId(null);
            em.persist(dc);
        }

        return dc;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DC update(DC dc){
        System.out.println(dc);
        if (dc != null && dc.getId() != null && dc.getId() != -1){
            em.merge(dc);
        }else{
            throw new IllegalArgumentException("You need to pass a existing DC as parameter to update");
        }

        return dc;
    }

    @GET
    @Produces("application/json")
    public List<DC> getDCs(){
        try{
            Query query = em.createQuery("SELECT d FROM DC d");
            return (List<DC>) query.getResultList();
        }catch(NullPointerException nre){
            return new ArrayList<DC>();
        }
    }

    @Path("{id}")
    @DELETE
    public void destroy(@PathParam("id")Integer id){
        if (id != null){
            DC dc = em.find(DC.class, id);

            if (dc != null){
                em.remove(dc);
            }else{
                throw new NoResultException("No results found for id "+id);
            }
        }else{
            throw new IllegalArgumentException("ID cannot be null");
        }
    }


    /**
     * @deprecated it is now configurable trough database.
     */
    @GET
    @Path("configurations")
    @Produces("application/json")
    public Map<String, String> getConfigurations(){
        return HTTPUtil.propertiesToMap("configuration.properties");
    }

}
