package das.tickets.dao;

import java.util.List;

import das.tickets.domain.UserGroup;

public interface RegistrationDaoAbstract extends BaseDaoAbstract {

	public List<UserGroup> findAllGroups();

}
