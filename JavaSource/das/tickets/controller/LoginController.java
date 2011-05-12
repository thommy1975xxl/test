package das.tickets.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "loginController")
public class LoginController {

	private String userName;

	private String password;

	public void display() {
		System.out.println("!!!!ONLY FOR TESTING!!!!!!!!!!");
		FacesContext.getCurrentInstance().addMessage("123",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "234", "567"));
		System.out.println(FacesContext.getCurrentInstance().getMessageList()
				.get(0).getDetail());
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
