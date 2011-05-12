package das.tickets.dao;

import das.tickets.domain.User;

public interface BootstrapDaoAbstract extends BaseDaoAbstract {

	public User detectAdminUser();

}
