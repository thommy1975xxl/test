package das.tickets.dao;

import java.util.List;

import das.tickets.domain.User;
import das.tickets.domain.UserGroup;

public interface RegistrationDaoAbstract extends BaseDaoAbstract {

	public List<UserGroup> findAllGroups();

	public List<User> findUserByName(String name);

	public List<User> findUserByEmail(String email);

}
