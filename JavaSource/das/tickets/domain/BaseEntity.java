package das.tickets.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseEntity implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 4793269272494865612L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@NotNull
	@Size(min = 5, max = 30)
	protected String createdBy;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdOn;

	@Size(min = 5, max = 30)
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	@Version
	private int version;

	// constructors
	public BaseEntity() {
	}

	// getter & setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

}
