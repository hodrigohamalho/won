package won.model;

import java.util.List;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 */
public class Server extends AbstractEntity{

    private String name;
    private String host;
    private DC dc;
    private List<Datasource> datasources;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public DC getDc() {
		return dc;
	}
	public void setDc(DC dc) {
		this.dc = dc;
	}
	@Override
	public String toString() {
		return "Server [name=" + name + ", host=" + host + ", dc=" + dc.getId() + "]";
	}
	
	
}
