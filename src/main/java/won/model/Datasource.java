package won.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 *
 */
@Entity
public class Datasource extends AbstractEntity implements Serializable{

	private String name;
	private List<DatasourceMetrics> metrics;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DatasourceMetrics> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<DatasourceMetrics> metrics) {
		this.metrics = metrics;
	}
}
