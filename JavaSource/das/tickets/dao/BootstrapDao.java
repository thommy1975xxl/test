package das.tickets.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import das.tickets.config.Bootstrap;
import das.tickets.config.RoleDefinition;
import das.tickets.domain.Role;
import das.tickets.domain.User;
import das.tickets.service.PasswordService;

public class BootstrapDao implements BootstrapDaoAbstract {

	@Override
	public void persist(Object obj) {
		entityManager.getTransaction().begin();
		entityManager.persist(obj);
		entityManager.getTransaction().commit();

	}

	@Override
	public void remove(Object obj) {
		entityManager.getTransaction().begin();
		entityManager.remove(obj);
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
			Set<Role> adminRoles = new HashSet<Role>();
			Role role = new Role();
			role.setCreatedBy(Bootstrap.ADMIN_USER_CREATED_BY);
			role.setCreatedOn(Bootstrap.ADMIN_USER_CREATED_ON);
			role.setRoleName(RoleDefinition.RoleName.ADMINISTRATOR.toString());
			persist(role);
			adminRoles.add(role);

			User adminUser = new User();
			adminUser.setCreatedBy(Bootstrap.ADMIN_USER_CREATED_BY);
			adminUser.setCreatedOn(Bootstrap.ADMIN_USER_CREATED_ON);
			adminUser.setDisabled(false);
			adminUser.setEmail(Bootstrap.ADMIN_USER_EMAIL);
			adminUser.setPassword(PasswordService
					.createHashedPassword(Bootstrap.ADMIN_USER_PASSWORD));
			adminUser.setUserName(Bootstrap.ADMIN_USER_NAME);
			adminUser.setRoles(adminRoles);
			persist(adminUser);

			return adminUser;
		}
		return users.get(0);
	}

	@Override
	public void mergeUser(Object obj) {
		entityManager.getTransaction().begin();
		entityManager.merge(obj);
		entityManager.getTransaction().commit();

	}

}
