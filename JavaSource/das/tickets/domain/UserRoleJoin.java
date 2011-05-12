package das.tickets.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserRoleJoin implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 4401265989595201635L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private User user;

	@ManyToOne
	private Role role;

	// constructors
	public UserRoleJoin() {

	}

	public UserRoleJoin(User user, Role role) {
		this.user = user;
		this.role = role;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
