package das.tickets.service;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageService {

	public static final String WRONG_LOGIN = "Wrong login. Please try again.";

	/**
	 * add a message to faces-context
	 * 
	 * @param shortDescription
	 * @param longDescription
	 */
	public static void addFacesMessageError(String shortDescription,
			String longDescription) {
		FacesContext.getCurrentInstance().addMessage(
				shortDescription,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, shortDescription,
						longDescription));
		System.out.println(">>>>>>>>>>>>>>>>MESSAGE________________"
				+ FacesContext.getCurrentInstance().getMessageList().get(0)
						.getDetail());
	}

}
