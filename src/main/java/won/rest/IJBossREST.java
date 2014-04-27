package won.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 */
@Path("/jboss")
public interface IJBossREST {

    @GET
    public String teste();

}
