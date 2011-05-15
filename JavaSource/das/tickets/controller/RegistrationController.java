package das.tickets.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.validator.ValidatorException;

import org.primefaces.model.DualListModel;

import das.tickets.config.GroupDefinition;
import das.tickets.config.RegistrationValidationDefinition;
import das.tickets.config.RoleDefinition;
import das.tickets.dao.RegistrationDao;

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

	private List<String> userRoles;

	private List<String> userGroups;

	private DualListModel<String> roleDefinitions;

	private DualListModel<String> groupDefinitions;

	private final RegistrationDao registrationDao = new RegistrationDao();

	private List<String> targetRole;

	private List<String> targetGroup;

	// business methods
	public void performUserRegistration() {
		if (!password.equals(passwordConfirmation)) {
			FacesMessage facesMessage = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					RegistrationValidationDefinition.MESSAGE_EMPTY_VALUE, null);
			throw new ValidatorException(facesMessage);
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

	public List<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<String> userRoles) {
		this.userRoles = userRoles;
	}

	public DualListModel<String> getRoleDefinitions() {
		return roleDefinitions;
	}

	public void setRoleDefinitions(DualListModel<String> roleDefinitions) {
		this.roleDefinitions = roleDefinitions;
	}

	public List<String> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<String> userGroups) {
		this.userGroups = userGroups;
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
