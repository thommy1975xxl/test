package das.tickets.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DualListModel;

import das.tickets.config.GroupDefinition;
import das.tickets.config.GroupDefinition.GroupName;
import das.tickets.config.RoleDefinition;
import das.tickets.config.RoleDefinition.RoleName;
import das.tickets.dao.RegistrationDao;
import das.tickets.domain.Role;
import das.tickets.domain.User;
import das.tickets.domain.UserGroup;
import das.tickets.service.MessageService;
import das.tickets.service.PasswordService;
import das.tickets.service.SessionService;

@ManagedBean(name = "registrationController")
@SessionScoped
public class RegistrationController {

	private final String UPDATE_PATH = "/pages/administration/registration/update.jsf";

	private final String REMOVE_PATH = "/pages/administration/registration/remove.jsf";

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

	private List<String> sourceRole;

	private List<String> targetGroup;

	private List<String> sourceGroup;

	private List<User> allUsersByCreationDate;

	private List<User> userListToUpdate = new ArrayList<User>();

	private User userToRemove;

	private User userToUpdate;

	// constructors
	public RegistrationController() {

		// Roles
		List<String> sourceRole = new ArrayList<String>();
		targetRole = new ArrayList<String>();
		sourceRole.add(RoleDefinition.RoleName.ASSIGNEE.toString());
		sourceRole.add(RoleDefinition.RoleName.MANAGER.toString());
		sourceRole.add(RoleDefinition.RoleName.REPORTER.toString());
		sourceRole.add(RoleDefinition.RoleName.VISITOR.toString());
		sourceRole.add(RoleDefinition.RoleName.ASSIGNEE.toString());
		sourceRole.add(RoleDefinition.RoleName.MANAGER.toString());
		sourceRole.add(RoleDefinition.RoleName.REPORTER.toString());
		sourceRole.add(RoleDefinition.RoleName.VISITOR.toString());
		sourceRole.add(RoleDefinition.RoleName.ASSIGNEE.toString());
		sourceRole.add(RoleDefinition.RoleName.MANAGER.toString());
		sourceRole.add(RoleDefinition.RoleName.REPORTER.toString());
		sourceRole.add(RoleDefinition.RoleName.VISITOR.toString());
		sourceRole.add(RoleDefinition.RoleName.ASSIGNEE.toString());
		sourceRole.add(RoleDefinition.RoleName.MANAGER.toString());
		sourceRole.add(RoleDefinition.RoleName.REPORTER.toString());
		sourceRole.add(RoleDefinition.RoleName.VISITOR.toString());
		sourceRole.add(RoleDefinition.RoleName.ASSIGNEE.toString());
		sourceRole.add(RoleDefinition.RoleName.MANAGER.toString());
		sourceRole.add(RoleDefinition.RoleName.REPORTER.toString());
		sourceRole.add(RoleDefinition.RoleName.VISITOR.toString());
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
		// all users
		allUsersByCreationDate = registrationDao
				.findAllUsersByRegistrationDate();
	}

	// business methods
	public String saveUserRegistration() {
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
			newUser.setFirstName(firstName);
			newUser.setLastName(lastName);

			// roles
			Set<Role> userRoles = new HashSet<Role>();
			for (String newRole : roleDefinitions.getTarget()) {
				Role role = new Role();
				role.setCreatedBy(createdBy);
				role.setCreatedOn(createdOn);
				role.setRoleName(newRole);
				registrationDao.persist(role);
				userRoles.add(role);
			}
			newUser.setRoles(userRoles);

			// groups
			Set<UserGroup> userGroups = new HashSet<UserGroup>();
			for (String newGroup : groupDefinitions.getTarget()) {
				UserGroup userGroup = new UserGroup();
				userGroup.setCreatedBy(createdBy);
				userGroup.setCreatedOn(createdOn);
				userGroup.setGroupName(newGroup);
				registrationDao.persist(userGroup);
				userGroups.add(userGroup);
			}
			newUser.setGroups(userGroups);

			registrationDao.persist(newUser);
			MessageService.displayFacesMessageInfo("regProp",
					"REGISTRATION_SUCCESS_SUMMARY",
					"REGISTRATION_SUCCESS_DETAIL");
		}
		allUsersByCreationDate = registrationDao
				.findAllUsersByRegistrationDate();
		return "/pages/administration/registration/new.jsf";
	}

	@SuppressWarnings("rawtypes")
	public String prepareUpdateUser(Long id) {
		userListToUpdate.clear();
		userToUpdate = registrationDao.getEntityManager().find(User.class, id);
		userName = userToUpdate.getUserName();
		firstName = userToUpdate.getFirstName();
		lastName = userToUpdate.getLastName();
		email = userToUpdate.getEmail();
		// roles
		targetRole = new ArrayList<String>();
		sourceRole = new ArrayList<String>();
		for (Role role : registrationDao.findRolesByUser(userToUpdate)) {
			targetRole.add(role.getRoleName());
		}
		// role-enum to source roles
		for (RoleName roleName : RoleDefinition.RoleName.values()) {
			sourceRole.add(roleName.toString());
		}
		// remove found target roles from source roles
		Iterator sourceRoleIterator = sourceRole.iterator();
		while (sourceRoleIterator.hasNext()) {
			for (String readTargetRole : targetRole) {
				if (readTargetRole.equals(sourceRoleIterator.next())) {
					sourceRoleIterator.remove();
				}
			}
		}
		roleDefinitions = new DualListModel<String>(sourceRole, targetRole);
		// groups
		targetGroup = new ArrayList<String>();
		sourceGroup = new ArrayList<String>();
		for (UserGroup userGroup : registrationDao
				.findGroupsByUser(userToUpdate)) {
			targetGroup.add(userGroup.getGroupName());
		}
		// group-enum to source roles
		for (GroupName groupName : GroupDefinition.GroupName.values()) {
			sourceGroup.add(groupName.toString());
		}
		// remove found target groups from source groups
		Iterator sourceGroupIterator = sourceGroup.iterator();
		while (sourceGroupIterator.hasNext()) {
			for (String readTargetGroup : targetGroup) {
				if (readTargetGroup.equals(sourceGroupIterator.next())) {
					sourceGroupIterator.remove();
				}
			}
		}
		groupDefinitions = new DualListModel<String>(sourceGroup, targetGroup);
		userListToUpdate.add(userToUpdate);
		allUsersByCreationDate = registrationDao
				.findAllUsersByRegistrationDate();
		return UPDATE_PATH;
	}

	public String updateUser(User user) {
		userToUpdate = user;
		userToUpdate.setModifiedOn(new Date());
		User modifiedByUser = (User) SessionService.getSessionAttribute("user");
		userToUpdate.setModifiedBy(modifiedByUser.getUserName());
		registrationDao.mergeUser(userToUpdate);
		MessageService.displayFacesMessageInfo("regProp",
				"REGISTRATION_USER_UPDATED_SUMMARY",
				"REGISTRATION_USER_UPDATED_DETAIL");
		return UPDATE_PATH;
	}

	public String toggleUserActivity(User user) {
		userToUpdate = user;
		userToUpdate.setDisabled(!userToUpdate.getDisabled());
		userToUpdate.setModifiedOn(new Date());
		User modifiedByUser = (User) SessionService.getSessionAttribute("user");
		userToUpdate.setModifiedBy(modifiedByUser.getUserName());
		registrationDao.mergeUser(userToUpdate);
		userToUpdate = null;
		if (user.getDisabled()) {
			MessageService.displayFacesMessageInfo("regProp",
					"REGISTRATION_USER_DISABLED_SUMMARY",
					"REGISTRATION_USER_DISABLED_DETAIL");
		} else {
			MessageService.displayFacesMessageInfo("regProp",
					"REGISTRATION_USER_ENABLED_SUMMARY",
					"REGISTRATION_USER_ENABLED_DETAIL");
		}
		return UPDATE_PATH;
	}

	public String removeUser(Long id) {
		// remove user
		userToRemove = registrationDao.getEntityManager().find(User.class, id);
		registrationDao.remove(userToRemove);
		MessageService.displayFacesMessageInfo("regProp",
				"REGISTRATION_REMOVED_SUMMARY", "REGISTRATION_REMOVED_DETAIL");
		allUsersByCreationDate = registrationDao
				.findAllUsersByRegistrationDate();
		userToRemove = null;
		return REMOVE_PATH;
	}

	public String toggleRegistrationMode() {
		if (userToUpdate != null) {
			userToUpdate = null;
		}
		return UPDATE_PATH;
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

	public List<User> getAllUsersByCreationDate() {
		return allUsersByCreationDate;
	}

	public void setAllUsersByCreationDate(List<User> allUsersByCreationDate) {
		this.allUsersByCreationDate = allUsersByCreationDate;
	}

	public User getUserToRemove() {
		return userToRemove;
	}

	public void setUserToRemove(User userToRemove) {
		this.userToRemove = userToRemove;
	}

	public User getUserToUpdate() {
		return userToUpdate;
	}

	public void setUserToUpdate(User userToUpdate) {
		this.userToUpdate = userToUpdate;
	}

	public List<String> getSourceRole() {
		return sourceRole;
	}

	public void setSourceRole(List<String> sourceRole) {
		this.sourceRole = sourceRole;
	}

	public List<String> getSourceGroup() {
		return sourceGroup;
	}

	public void setSourceGroup(List<String> sourceGroup) {
		this.sourceGroup = sourceGroup;
	}

	public List<User> getUserListToUpdate() {
		return userListToUpdate;
	}

	public void setUserListToUpdate(List<User> userListToUpdate) {
		this.userListToUpdate = userListToUpdate;
	}

}
