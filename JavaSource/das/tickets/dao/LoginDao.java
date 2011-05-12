package das.tickets.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import das.tickets.config.RoleDefinition;
import das.tickets.domain.Role;
import das.tickets.domain.User;
import das.tickets.domain.UserRoleJoin;
import das.tickets.service.PasswordService;

@ManagedBean(name = "loginDao")
@ApplicationScoped
public class LoginDao implements Serializable, LoginDaoAbstract {

	/** serialVersionUID */
	private static final long serialVersionUID = 107168656390808655L;

	private final String adminUserName = "admin";
	private final String adminUserPassword = "admin";
	private final String adminUserEmail = "admin@mail.com";
	private final String adminUserCreatedby = "system";
	private final Date adminUserCreatedOn = new Date();

	@Override
	public void persist(Object obj) {
		entityManager.getTransaction().begin();
		entityManager.persist(obj);
		entityManager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findUserByName(String userName, String password) {
		List<User> users = entityManager
				.createQuery(
						"SELECT u FROM User u WHERE u.userName =:userName AND u.password =:password")
				.setParameter("userName", userName)
				.setParameter("password",
						PasswordService.createHashedPassword(password))
				.getResultList();
		if (users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
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
			role.setCreatedBy(adminUserCreatedby);
			role.setCreatedOn(adminUserCreatedOn);
			role.setRoleName(RoleDefinition.RoleName.ADMINISTRATOR.toString());
			persist(role);

			User adminUser = new User();
			adminUser.setCreatedBy(adminUserCreatedby);
			adminUser.setCreatedOn(adminUserCreatedOn);
			adminUser.setDisabled(false);
			adminUser.setEmail(adminUserEmail);
			adminUser.setPassword(PasswordService
					.createHashedPassword(adminUserPassword));
			adminUser.setUserName(adminUserName);
			persist(adminUser);

			UserRoleJoin userRoleJoin = new UserRoleJoin(adminUser, role);
			persist(userRoleJoin);
			return adminUser;
		}
		return users.get(0);
	}

}
