package won.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 */
@Path("/server")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IServer {

    @GET
    public String serverInfo(String dc, String hc, String server);
}
