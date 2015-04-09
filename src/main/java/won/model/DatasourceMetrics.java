package won.model;

import javax.persistence.Entity;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 *
 */
@Entity
public class DatasourceMetrics extends AbstractEntity {

	private Integer minPoolSize;
	private Integer maxPoolSize;
	private String driverName;
	private String connectionUrl;
	private Long inUseCount;
	private Long activeCount;
	private Long availableCount;
	private Long averageBlockingTime;
	private Long averageCreationTime;
	private Long createdCount;
	private Long destroyedCount;
	private Long maxCreationTime;
	private Long maxUsedCount;
	private Long maxWaitCount;
	private Long maxWaitTime;
	private Long timedOut;
	private Long totalBlockingTime;
	private Long totalCreationTime;

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

	public Long getInUseCount() {
		return inUseCount;
	}

	public void setInUseCount(Long inUseCount) {
		this.inUseCount = inUseCount;
	}

	public Long getActiveCount() {
		return activeCount;
	}

	public void setActiveCount(Long activeCount) {
		this.activeCount = activeCount;
	}

	public Long getAvailableCount() {
		return availableCount;
	}

	public void setAvailableCount(Long availableCount) {
		this.availableCount = availableCount;
	}

	public Long getAverageBlockingTime() {
		return averageBlockingTime;
	}

	public void setAverageBlockingTime(Long averageBlockingTime) {
		this.averageBlockingTime = averageBlockingTime;
	}

	public Long getAverageCreationTime() {
		return averageCreationTime;
	}

	public void setAverageCreationTime(Long averageCreationTime) {
		this.averageCreationTime = averageCreationTime;
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

	public Long getMaxCreationTime() {
		return maxCreationTime;
	}

	public void setMaxCreationTime(Long maxCreationTime) {
		this.maxCreationTime = maxCreationTime;
	}

	public Long getMaxUsedCount() {
		return maxUsedCount;
	}

	public void setMaxUsedCount(Long maxUsedCount) {
		this.maxUsedCount = maxUsedCount;
	}

	public Long getMaxWaitCount() {
		return maxWaitCount;
	}

	public void setMaxWaitCount(Long maxWaitCount) {
		this.maxWaitCount = maxWaitCount;
	}

	public Long getMaxWaitTime() {
		return maxWaitTime;
	}

	public void setMaxWaitTime(Long maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}

	public Long getTimedOut() {
		return timedOut;
	}

	public void setTimedOut(Long timedOut) {
		this.timedOut = timedOut;
	}

	public Long getTotalBlockingTime() {
		return totalBlockingTime;
	}

	public void setTotalBlockingTime(Long totalBlockingTime) {
		this.totalBlockingTime = totalBlockingTime;
	}

	public Long getTotalCreationTime() {
		return totalCreationTime;
	}

	public void setTotalCreationTime(Long totalCreationTime) {
		this.totalCreationTime = totalCreationTime;
	}
	
}
