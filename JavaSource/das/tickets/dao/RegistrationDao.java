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

	@SuppressWarnings("unchecked")
	@Override
	public boolean findRoleForUser(String roleName, User user) {
		List<Role> roles = entityManager
				.createQuery(
						"SELECT r FROM Role r JOIN r.users u WHERE r.roleName =:roleName AND u =:user")
				.setParameter("roleName", roleName).setParameter("user", user)
				.getResultList();
		if (!roles.isEmpty()) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findAllRoles() {
		return entityManager.createQuery(
				"SELECT DISTINCT r FROM Role r ORDER BY r.roleName ASC")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Role findRoleByRoleName(String roleName) {
		List<Role> roles = entityManager
				.createQuery(
						"SELECT DISTINCT r FROM Role r WHERE r.roleName =:roleName")
				.setParameter("roleName", roleName).getResultList();
		if (!roles.isEmpty()) {
			return roles.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserGroup findGroupByGroupName(String groupName) {
		List<UserGroup> groups = entityManager
				.createQuery(
						"SELECT DISTINCT g FROM UserGroup g WHERE g.groupName =:groupName")
				.setParameter("groupName", groupName).getResultList();
		if (!groups.isEmpty()) {
			return groups.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findNotAssignedRolesByUser(User user) {
		return entityManager
				.createQuery(
						"SELECT DISTINCT r FROM Role r WHERE r NOT IN(SELECT DISTINCT r FROM Role r JOIN r.users u WHERE u =:user)")
				.setParameter("user", user).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroup> findNotAssignedUserGroupsByUser(User user) {
		return entityManager
				.createQuery(
						"SELECT DISTINCT g FROM UserGroup g WHERE g NOT IN(SELECT DISTINCT g FROM UserGroup g JOIN g.users u WHERE u =:user)")
				.setParameter("user", user).getResultList();
	}

}
