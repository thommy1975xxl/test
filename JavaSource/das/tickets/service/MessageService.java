package das.tickets.service;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageService {

	private static ResourceBundle bundle;

	private static ResourceBundle getBundle(String bundleName) {
		if (bundle == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			bundle = context.getApplication().getResourceBundle(context,
					bundleName);
		}
		return bundle;
	}

	public static String getValue(String ressourceBundleName, String key) {

		String result = null;
		try {
			result = getBundle(ressourceBundleName).getString(key);
		} catch (MissingResourceException e) {
			result = "???" + key + "??? not found";
		}
		return result;
	}

	public static void displayFacesMessageInfo(String propertyBundle,
			String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, MessageService
						.getValue(propertyBundle, summary), MessageService
						.getValue(propertyBundle, detail)));
	}

	public static void displayFacesMessageError(String propertyBundle,
			String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageService
						.getValue(propertyBundle, summary), MessageService
						.getValue(propertyBundle, detail)));
	}

	public static void displayFacesMessageWarn(String propertyBundle,
			String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, MessageService
						.getValue(propertyBundle, summary), MessageService
						.getValue(propertyBundle, detail)));
	}

}
