package das.tickets.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import das.tickets.config.Bootstrap;
import das.tickets.config.RoleDefinition;
import das.tickets.controller.LoginController;
import das.tickets.dao.BootstrapDao;
import das.tickets.dao.LoginDao;
import das.tickets.domain.Role;
import das.tickets.domain.User;

public class BootstrapDaoTest {

	private BootstrapDao bootstrapDao;
	private LoginDao loginDao;
	private EntityManager entityManager;

	@Mock
	LoginController loginController;

	@Before
	public void setup() {
		bootstrapDao = new BootstrapDao();
		loginDao = new LoginDao();
		entityManager = bootstrapDao.getEntityManager();
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void after() {
		entityManager.clear();
	}

	@Test
	public void testEntityManager() {
		assertNotNull(entityManager);
	}

	@Test
	public void testAdminUser() {
		assertNotNull(bootstrapDao.detectAdminUser());
	}

	@Test
	public void testAdminUser_UserName() {
		User adminUser = bootstrapDao.detectAdminUser();
		assertEquals("admin", adminUser.getUserName());
	}

	@Test
	public void testAdminUser_Uniqueness() {
		List<User> users = loginDao.findUsersByUserName("admin");
		assertEquals(1, users.size());
	}

	@Test
	public void testAdminUser_Details() {
		User adminUser = bootstrapDao.detectAdminUser();
		assertEquals(Bootstrap.ADMIN_USER_CREATED_BY, adminUser.getCreatedBy());
		assertNotNull(adminUser.getCreatedOn());
		assertTrue(adminUser.getCreatedOn() instanceof Date);
	}

	@Test
	public void testAdminUser_Roles() {
		List<Role> roles = loginDao.findRolesByUserName("admin");
		String roleNameForSearch = "";
		for (Role role : roles) {
			if (role.getRoleName().equals(
					RoleDefinition.RoleName.ADMINISTRATOR.toString())) {
				roleNameForSearch = role.getRoleName();
			}
		}
		assertTrue(roleNameForSearch.length() > 0);
	}

}
