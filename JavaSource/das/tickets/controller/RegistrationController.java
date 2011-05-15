package das.tickets.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.DualListModel;

import das.tickets.config.GroupDefinition;
import das.tickets.config.RoleDefinition;
import das.tickets.dao.RegistrationDao;
import das.tickets.domain.Role;
import das.tickets.domain.User;
import das.tickets.domain.UserGroup;
import das.tickets.domain.UserGroupJoin;
import das.tickets.domain.UserRoleJoin;
import das.tickets.service.MessageService;
import das.tickets.service.PasswordService;
import das.tickets.service.SessionService;

@ManagedBean(name = "registrationController")
@ApplicationScoped
public class RegistrationController {

	private String userName;

	private String password;

	private String passwordConfirmation;

	private String email;

	private String firstName;

	private String lastName;

	private String groupName;

	private String roleName;

	private DualListModel<String> roleDefinitions;

	private DualListModel<String> groupDefinitions;

	private final RegistrationDao registrationDao = new RegistrationDao();

	private List<String> targetRole;

	private List<String> targetGroup;

	// business methods
	public void performUserRegistration() {
		if (!roleDefinitions.getTarget().isEmpty()
				&& !groupDefinitions.getTarget().isEmpty()) {
			Date createdOn = new Date();
			User createdByUser = (User) SessionService
					.getSessionAttribute("user");
			String createdBy = createdByUser.getUserName();
			// user
			User newUser = new User();
			newUser.setCreatedBy(createdBy);
			newUser.setCreatedOn(createdOn);
			newUser.setDisabled(false);
			newUser.setEmail(email);
			newUser.setPassword(PasswordService.createHashedPassword(password));
			newUser.setUserName(userName);
			registrationDao.persist(newUser);
			// roles
			for (String newRole : roleDefinitions.getTarget()) {
				Role role = new Role();
				role.setCreatedBy(createdBy);
				role.setCreatedOn(createdOn);
				role.setRoleName(newRole);
				registrationDao.persist(role);
				// join
				UserRoleJoin userRoleJoin = new UserRoleJoin();
				userRoleJoin.setRole(role);
				userRoleJoin.setUser(newUser);
				registrationDao.persist(userRoleJoin);
			}
			// groups
			for (String newGroup : groupDefinitions.getTarget()) {
				UserGroup userGroup = new UserGroup();
				userGroup.setCreatedBy(createdBy);
				userGroup.setCreatedOn(createdOn);
				userGroup.setGroupName(newGroup);
				registrationDao.persist(userGroup);
				// join
				UserGroupJoin userGroupJoin = new UserGroupJoin();
				userGroupJoin.setGroup(userGroup);
				userGroupJoin.setUser(newUser);
				registrationDao.persist(userGroupJoin);
			}
			MessageService.addFacesMessageInfo(
					MessageService.REGISTRATION_SUCCESS, null);
		}
	}

	public RegistrationController() {
		// Roles
		List<String> sourceRole = new ArrayList<String>();
		targetRole = new ArrayList<String>();
		sourceRole.add(RoleDefinition.RoleName.ASSIGNEE.toString());
		sourceRole.add(RoleDefinition.RoleName.MANAGER.toString());
		sourceRole.add(RoleDefinition.RoleName.REPORTER.toString());
		sourceRole.add(RoleDefinition.RoleName.VISITOR.toString());
		roleDefinitions = new DualListModel<String>(sourceRole, targetRole);
		// Groups
		List<String> sourceGroup = new ArrayList<String>();
		targetGroup = new ArrayList<String>();
		sourceGroup.add(GroupDefinition.GroupName.DEVELOPMENT.toString());
		sourceGroup.add(GroupDefinition.GroupName.MARKETING.toString());
		sourceGroup.add(GroupDefinition.GroupName.QA.toString());
		sourceGroup.add(GroupDefinition.GroupName.SUPPORT.toString());
		groupDefinitions = new DualListModel<String>(sourceGroup, targetGroup);
	}

	// getter & setter
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public DualListModel<String> getRoleDefinitions() {
		return roleDefinitions;
	}

	public void setRoleDefinitions(DualListModel<String> roleDefinitions) {
		this.roleDefinitions = roleDefinitions;
	}

	public DualListModel<String> getGroupDefinitions() {
		return groupDefinitions;
	}

	public void setGroupDefinitions(DualListModel<String> groupDefinitions) {
		this.groupDefinitions = groupDefinitions;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<String> getTargetRole() {
		return targetRole;
	}

	public void setTargetRole(List<String> targetRole) {
		this.targetRole = targetRole;
	}

	public List<String> getTargetGroup() {
		return targetGroup;
	}

	public void setTargetGroup(List<String> targetGroup) {
		this.targetGroup = targetGroup;
	}

}
