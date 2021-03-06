package das.tickets.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import das.tickets.config.MessageDefinition;
import das.tickets.config.RoleDefinition;
import das.tickets.dao.BootstrapDao;
import das.tickets.dao.LoginDao;
import das.tickets.domain.Role;
import das.tickets.domain.User;
import das.tickets.service.SessionService;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController {

	private String userName;

	private String password;

	private final LoginDao loginDao = new LoginDao();

	private boolean hasAdministratorRole = false;

	private final BootstrapDao bootstrapDao = new BootstrapDao();

	// business methods
	public void login() {
		bootstrapDao.detectAdminUser();
		User user = loginDao.findUserByUserNameAndPassword(userName, password);
		if (user == null || user.getDisabled()) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							MessageDefinition.WRONG_LOGIN,
							MessageDefinition.WRONG_LOGIN));
		} else {
			for (Role role : loginDao.findRolesByUserName(user.getUserName())) {
				if (role.getRoleName().equals(
						RoleDefinition.RoleName.ADMINISTRATOR.toString())) {
					hasAdministratorRole = true;
				}
			}
			SessionService.setSessionAttribute("user", user);
		}
	}

	public void logout() throws IOException {
		SessionService.removeAllSessionAttributes();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("./pages/welcome.jsf");
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

	public boolean isHasAdministratorRole() {
		return hasAdministratorRole;
	}

	public void setHasAdministratorRole(boolean hasAdministratorRole) {
		this.hasAdministratorRole = hasAdministratorRole;
	}

}
