package das.tickets.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;

import das.tickets.domain.Role;
import das.tickets.domain.User;
import das.tickets.service.PasswordService;

@ManagedBean(name = "loginDao")
@ApplicationScoped
public class LoginDao implements Serializable, LoginDaoAbstract {

	/** serialVersionUID */
	private static final long serialVersionUID = 107168656390808655L;

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

	@SuppressWarnings("unchecked")
	@Override
	public User findUserByUserNameAndPassword(String userName, String password) {
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

	@Override
	public EntityManager getEntityManager() {
		return entityManager;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUsersByUserName(String userName) {
		return entityManager
				.createQuery("SELECT u FROM User u WHERE u.userName =:userName")
				.setParameter("userName", userName).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findRolesByUserName(String userName) {
		return entityManager
				.createQuery(
						"SELECT r FROM Role r JOIN r.users u WHERE u.userName =:userName")
				.setParameter("userName", userName).getResultList();
	}

	@Override
	public void mergeUser(Object obj) {
		entityManager.getTransaction().begin();
		entityManager.merge(obj);
		entityManager.getTransaction().commit();

	}

}
