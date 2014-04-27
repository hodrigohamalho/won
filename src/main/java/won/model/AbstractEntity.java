package won.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 *
 */
@MappedSuperclass
public abstract class AbstractEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date createdAt;
	private Date updatedAt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Date getDateTime() {
		return createdAt;
	}
	public void setDateTime(Date dateTime) {
		this.createdAt = dateTime;
	}
}
