package das.tickets.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import das.tickets.dao.LoginDao;
import das.tickets.domain.User;
import das.tickets.service.MessageService;
import das.tickets.service.SessionService;

@ManagedBean(name = "loginController")
public class LoginController {

	private String userName;

	private String password;

	@Inject
	private LoginDao loginDao;

	public void login() {
		User user = loginDao.findUserByName(userName, password);
		if (user == null) {
			FacesContext.getCurrentInstance().addMessage(
					MessageService.WRONG_LOGIN,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							MessageService.WRONG_LOGIN,
							MessageService.WRONG_LOGIN));
		} else {
			SessionService.setSessionAttribute("user", user);
		}

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

}
