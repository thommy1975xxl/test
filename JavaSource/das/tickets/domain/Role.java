package das.tickets.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Role extends BaseEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = -1883237188542879354L;

	@NotNull
	@Size(max = 50)
	private String roleName;

	@OneToMany(mappedBy = "role")
	private Collection<UserRoleJoin> userRoleJoins;

	// constructors
	public Role() {
	}

	// getter & setter
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Collection<UserRoleJoin> getUserRoleJoins() {
		return userRoleJoins;
	}

	public void setUserRoleJoins(Collection<UserRoleJoin> userRoleJoins) {
		this.userRoleJoins = userRoleJoins;
	}

}