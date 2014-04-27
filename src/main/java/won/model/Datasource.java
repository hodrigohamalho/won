package won.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 *
 */
@Entity
public class Datasource extends AbstractEntity implements Serializable{

	private String name;
	private Integer minPoolSize;
	private Integer maxPoolSize;
	private String driverName;
	private String connectionUrl;
	private String profile;
	
	@OneToMany(targetEntity=DatasourceMetrics.class, mappedBy="datasource", cascade=javax.persistence.CascadeType.REMOVE, fetch=FetchType.LAZY)
	private List<DatasourceMetrics> metrics;

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public Integer getMinPoolSize() {
		if (minPoolSize == null){
			return Integer.valueOf(0);
		}
		return minPoolSize;
	}

	public void setMinPoolSize(Integer minPoolSize) {
		this.minPoolSize = minPoolSize;
	}

	public Integer getMaxPoolSize() {
		if (maxPoolSize == null){
			return Integer.valueOf(0);
		}
		return maxPoolSize;
	}

	public void setMaxPoolSize(Integer maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}
	public List<DatasourceMetrics> getMetrics() {
		if (metrics == null) {
			metrics = new ArrayList<DatasourceMetrics>();
		}

		return metrics;
	}

	public void setMetrics(List<DatasourceMetrics> metrics) {
		this.metrics = metrics;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	
}
