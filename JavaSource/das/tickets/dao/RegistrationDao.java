package das.tickets.dao;

import java.util.List;

import javax.persistence.EntityManager;

import das.tickets.domain.User;
import das.tickets.domain.UserGroup;

public class RegistrationDao implements RegistrationDaoAbstract {

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
	public List<UserGroup> findAllGroups() {
		return entityManager.createQuery(
				"SELECT g FROM UserGroup g ORDER BY g.groupName ASC")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserByName(String name) {
		return entityManager
				.createQuery("SELECT u FROM User u WHERE u.userName =:userName")
				.setParameter("userName", name).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserByEmail(String email) {
		return entityManager
				.createQuery("SELECT u FROM User u WHERE u.email =:email")
				.setParameter("email", email).getResultList();
	}

}
