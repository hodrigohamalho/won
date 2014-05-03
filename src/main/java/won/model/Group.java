package won.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 */
public class Group {

    public Group() {
        servers = new ArrayList<Server>();
    }

    private String name;
    private List<Server> servers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Server> getServers() { return servers; }
    public void setServers(List<Server> servers) { this.servers = servers; }
}
