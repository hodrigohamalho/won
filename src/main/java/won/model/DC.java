package won.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 *
 *         Class with all data necessary to stablish a connection with Domain Controller.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class DC implements Serializable{

    public DC() {}

    public DC(Integer id, String host, Integer port, String username, String password, Boolean active) {
        this.id = id;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer id;

    private String name;
	@NotNull
    private String host;
    @NotNull
    private Integer port;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private Boolean active;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }
    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    @Override
    public String toString() {
        return "DC{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                '}';
    }


}