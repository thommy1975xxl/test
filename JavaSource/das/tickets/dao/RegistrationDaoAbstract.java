package das.tickets.dao;

import java.util.List;

import das.tickets.domain.Role;
import das.tickets.domain.User;
import das.tickets.domain.UserGroup;

public interface RegistrationDaoAbstract extends BaseDaoAbstract {

	public List<UserGroup> findAllGroups();

	public List<Role> findAllRoles();

	public List<User> findUserByName(String name);

	public List<User> findUserByEmail(String email);

	public List<User> findAllUsersByRegistrationDate();

	public void removeUser(Long id);

	public List<Role> findRolesByUser(User user);

	public List<UserGroup> findGroupsByUser(User user);

	public boolean findRoleForUser(String roleName, User user);

	public Role findRoleByRoleName(String roleName);

	public UserGroup findGroupByGroupName(String groupName);

	public List<Role> findNotAssignedRolesByUser(User user);

	public List<UserGroup> findNotAssignedUserGroupsByUser(User user);

}
