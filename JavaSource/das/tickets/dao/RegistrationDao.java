package das.tickets.dao;

import java.util.List;

import javax.persistence.EntityManager;

import das.tickets.domain.Role;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsersByRegistrationDate() {
		return entityManager.createQuery(
				"SELECT u FROM User u ORDER BY u.createdOn DESC")
				.getResultList();
	}

	@Override
	public void removeUser(Long id) {
		User user = entityManager.find(User.class, id);
		User userToRemove = entityManager.merge(user);
		this.remove(userToRemove);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findRolesByUser(User user) {
		return entityManager
				.createQuery(
						"SELECT r FROM Role r JOIN r.users u WHERE u =:user")
				.setParameter("user", user).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroup> findGroupsByUser(User user) {
		return entityManager
				.createQuery(
						"SELECT g FROM UserGroup g JOIN g.users u WHERE u =:user")
				.setParameter("user", user).getResultList();
	}

	@Override
	public void mergeUser(Object obj) {
		entityManager.getTransaction().begin();
		entityManager.merge(obj);
		entityManager.getTransaction().commit();

	}

}
