package das.tickets.service;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionService {

	/**
	 * add an attribute to current session
	 * 
	 * @param attributeName
	 * @param value
	 */
	public static void setSessionAttribute(String attributeName, Object value) {
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(true);
		httpSession.setAttribute(attributeName, value);
	}

	/**
	 * removes all attributes from current session
	 */
	public static void removeAllSessionAttributes() {
		HttpSession httpSession = (HttpSession) FacesContext
				.getCurrentInstance().getExternalContext().getSession(true);
		httpSession.invalidate();
	}

}
