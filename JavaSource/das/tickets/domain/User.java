package das.tickets.domain;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	private String firstName;

	private String lastName;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "USER_ROLE_JOIN", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Collection<Role> roles;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "USER_GROUP_JOIN", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "group_id") })
	private Collection<UserGroup> groups;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Collection<UserGroup> getGroups() {
		return groups;
	}

	public void setGroups(Collection<UserGroup> groups) {
		this.groups = groups;
	}

}
