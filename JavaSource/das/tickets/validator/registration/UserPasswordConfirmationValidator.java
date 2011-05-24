package das.tickets.validator.registration;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import das.tickets.config.RegistrationValidationDefinition;

@FacesValidator(value = "das.tickets.validator.registration.UserPasswordConfirmationValidator")
public class UserPasswordConfirmationValidator implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent,
			Object value) throws ValidatorException {

		if (value instanceof String) {
			UIComponent passwordComponent = facesContext.getViewRoot()
					.findComponent(":registrationForm:passwordInput");
			String passwordValue = (String) ((org.primefaces.component.password.Password) passwordComponent)
					.getValue();
			if (!value.equals(passwordValue)) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						RegistrationValidationDefinition.MESSAGE_PASSWORD_CONFIRMATION,
						null);
				throw new ValidatorException(facesMessage);
			}

		}

	}

}
