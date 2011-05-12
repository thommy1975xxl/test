package das.tickets.dao;

import das.tickets.domain.User;

public interface LoginDaoAbstract extends BaseDaoAbstract {

	public User findUserByName(String userName, String password);

	public User detectAdminUser();

}
