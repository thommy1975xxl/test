package das.tickets.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import das.tickets.config.RegistrationValidationDefinition;

@FacesValidator(value = "das.tickets.validator.UserLastNameValidator")
public class UserLastNameValidator implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent,
			Object value) throws ValidatorException {
		if (value instanceof String) {
			String readValue = (String) value;
			if (readValue.length() < RegistrationValidationDefinition.VALUE_USER_FIRSTNAME_MIN_LENGTH
					|| readValue.length() > RegistrationValidationDefinition.VALUE_USER_FIRSTNAME_MAX_LENGTH) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						RegistrationValidationDefinition.MESSAGE_USER_FIRSTNAME_LENGTH,
						null);
				throw new ValidatorException(facesMessage);
			}
		}

	}

}
