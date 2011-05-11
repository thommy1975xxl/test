package das.tickets.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "loginController")
public class LoginController {

	public void display() {
		System.out.println("!!!!!!!!!!!!!!!!!!!!å‚‚");
		FacesContext.getCurrentInstance().addMessage("123",
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "234", "567"));
		System.out.println(FacesContext.getCurrentInstance().getMessageList()
				.get(0).getDetail());
	}

}
