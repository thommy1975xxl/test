package das.tickets.dao;

import java.util.List;

import javax.persistence.EntityManager;

import das.tickets.config.Bootstrap;
import das.tickets.config.RoleDefinition;
import das.tickets.domain.Role;
import das.tickets.domain.User;
import das.tickets.domain.UserRoleJoin;
import das.tickets.service.PasswordService;

public class BootstrapDao implements BootstrapDaoAbstract {

	@Override
	public void persist(Object obj) {
		entityManager.getTransaction().begin();
		entityManager.persist(obj);
		entityManager.getTransaction().commit();

	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User detectAdminUser() {
		List<User> users = entityManager
				.createQuery(
						"SELECT u FROM User u WHERE LOWER(u.userName) =LOWER(:userName)")
				.setParameter("userName", "admin").getResultList();
		if (users.isEmpty()) {
			Role role = new Role();
			role.setCreatedBy(Bootstrap.ADMIN_USER_CREATED_BY);
			role.setCreatedOn(Bootstrap.ADMIN_USER_CREATED_ON);
			role.setRoleName(RoleDefinition.RoleName.ADMINISTRATOR.toString());
			persist(role);

			User adminUser = new User();
			adminUser.setCreatedBy(Bootstrap.ADMIN_USER_CREATED_BY);
			adminUser.setCreatedOn(Bootstrap.ADMIN_USER_CREATED_ON);
			adminUser.setDisabled(false);
			adminUser.setEmail(Bootstrap.ADMIN_USER_EMAIL);
			adminUser.setPassword(PasswordService
					.createHashedPassword(Bootstrap.ADMIN_USER_PASSWORD));
			adminUser.setUserName(Bootstrap.ADMIN_USER_NAME);
			persist(adminUser);

			UserRoleJoin userRoleJoin = new UserRoleJoin(adminUser, role);
			persist(userRoleJoin);
			return adminUser;
		}
		return users.get(0);
	}

}
