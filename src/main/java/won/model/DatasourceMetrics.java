package won.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 *
 */
@Entity
public class DatasourceMetrics extends AbstractEntity implements Serializable{

	private Long activeCount;
	private Long maxUsedCount;
	private Long createdCount;
	private Long destroyedCount;
	private Double maxWaitTime;
	private Long timedOut;
	private Long availableCount;
	private Double averageBlockingTime;
	private Double AverageCreationTime;
	private Date time;

	@ManyToOne
	@JoinColumn(name="datasource_id")
	private Datasource datasource;


	public Long getActiveCount() {
		if (activeCount == null) {
			activeCount = new Long(0);
		}

		return activeCount;
	}

	public void setActiveCount(Long activeCount) {
		this.activeCount = activeCount;
	}

	public Long getMaxUsedCount() {
		return maxUsedCount;
	}

	public void setMaxUsedCount(Long maxUsedCount) {
		this.maxUsedCount = maxUsedCount;
	}

	public Long getCreatedCount() {
		return createdCount;
	}

	public void setCreatedCount(Long createdCount) {
		this.createdCount = createdCount;
	}

	public Long getDestroyedCount() {
		return destroyedCount;
	}

	public void setDestroyedCount(Long destroyedCount) {
		this.destroyedCount = destroyedCount;
	}
	
	public Double getAverageBlockingTime() {
		return averageBlockingTime;
	}

	public void setAverageBlockingTime(Double averageBlockingTime) {
		this.averageBlockingTime = averageBlockingTime;
	}

	public Double getAverageCreationTime() {
		return AverageCreationTime;
	}

	public void setAverageCreationTime(Double averageCreationTime) {
		AverageCreationTime = averageCreationTime;
	}

	public Long getAvailableCount() {
		return availableCount;
	}

	public void setAvailableCount(Long availableCount) {
		this.availableCount = availableCount;
	}

	public Long getTimedOut() {
		return timedOut;
	}

	public void setTimedOut(Long timedOut) {
		this.timedOut = timedOut;
	}

	public Double getMaxWaitTime() {
		return maxWaitTime;
	}

	public void setMaxWaitTime(Double maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	public Datasource getDatasource() {
		return datasource;
	}

	public void setDatasource(Datasource datasource) {
		this.datasource = datasource;
	}
	
}
