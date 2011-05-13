package das.tickets.dao;

import java.util.List;

import javax.persistence.EntityManager;

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

}
