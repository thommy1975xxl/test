package das.tickets.service;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageService {

	public static final String WRONG_LOGIN = "Wrong login. Please try again";

	public static final String REGISTRATION_SUCCESS = "New user registrated";

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
	}

	public static void addFacesMessageInfo(String shortDescription,
			String longDescription) {
		FacesContext.getCurrentInstance().addMessage(
				shortDescription,
				new FacesMessage(FacesMessage.SEVERITY_INFO, shortDescription,
						longDescription));
	}

}
