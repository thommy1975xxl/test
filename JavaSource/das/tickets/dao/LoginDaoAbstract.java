package das.tickets.dao;

import java.util.List;

import das.tickets.domain.Role;
import das.tickets.domain.User;

public interface LoginDaoAbstract extends BaseDaoAbstract {

	public User findUserByUserNameAndPassword(String userName, String password);

	public List<User> findUsersByUserName(String userName);

	public List<Role> findRolesByUserName(String userName);

}
