package das.tickets.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public interface BaseDaoAbstract {

	String PERSISTENCE_UNIT_NAME = "dasTicketsPU";
	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	EntityManager entityManager = factory.createEntityManager();

	public void persist(Object obj);

	public void remove(Object obj);

	public void mergeUser(Object obj);

	public EntityManager getEntityManager();

}
