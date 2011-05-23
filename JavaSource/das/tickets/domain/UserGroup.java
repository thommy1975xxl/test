package das.tickets.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class UserGroup extends BaseEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 6565661196641630683L;

	@NotNull
	@Size(max = 50)
	private String groupName;

	@ManyToMany(mappedBy = "groups")
	private Collection<User> users;

	// constructors
	public UserGroup() {
	}

	// getter & setter
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

}
