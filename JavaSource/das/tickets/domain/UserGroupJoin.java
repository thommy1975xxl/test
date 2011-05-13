package das.tickets.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserGroupJoin implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -3756455276318191645L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private User user;

	@ManyToOne
	private UserGroup group;

	// constructors
	public UserGroupJoin() {

	}

	public UserGroupJoin(User user, UserGroup group) {
		this.user = user;
		this.group = group;
	}

	// getter & setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserGroup getGroup() {
		return group;
	}

	public void setGroup(UserGroup group) {
		this.group = group;
	}

}
