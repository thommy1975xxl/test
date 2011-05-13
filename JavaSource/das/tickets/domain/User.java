package das.tickets.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User extends BaseEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 8537646136419941009L;

	@NotNull
	@Size(min = 5, max = 30)
	private String userName;

	@NotNull
	private String password;

	@NotNull
	private Boolean disabled;

	private String email;

	@OneToMany(mappedBy = "user")
	private Collection<UserRoleJoin> userRoleJoins;

	@OneToMany(mappedBy = "user")
	private Collection<UserGroupJoin> userGroupJoins;

	// constructors
	public User() {
	}

	// getter & setter
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<UserRoleJoin> getUserRoleJoins() {
		return userRoleJoins;
	}

	public void setUserRoleJoins(Collection<UserRoleJoin> userRoleJoins) {
		this.userRoleJoins = userRoleJoins;
	}

	public Collection<UserGroupJoin> getUserGroupJoins() {
		return userGroupJoins;
	}

	public void setUserGroupJoins(Collection<UserGroupJoin> userGroupJoins) {
		this.userGroupJoins = userGroupJoins;
	}

}
