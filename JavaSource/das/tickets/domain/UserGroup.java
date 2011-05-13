package das.tickets.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class UserGroup extends BaseEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 6565661196641630683L;

	@NotNull
	@Size(max = 50)
	private String groupName;

	@OneToMany(mappedBy = "group")
	private Collection<UserGroupJoin> userGroupJoins;

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

	public Collection<UserGroupJoin> getUserGroupJoins() {
		return userGroupJoins;
	}

	public void setUserGroupJoins(Collection<UserGroupJoin> userGroupJoins) {
		this.userGroupJoins = userGroupJoins;
	}

}
