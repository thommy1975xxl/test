package das.tickets.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import das.tickets.config.RegistrationValidationDefinition;

@FacesValidator(value = "das.tickets.validator.UserLastNamePatternValidator")
public class UserLastNamePatternValidator implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent,
			Object value) throws ValidatorException {
		if (value instanceof String) {
			String readValue = (String) value;
			Pattern pattern = Pattern
					.compile(RegistrationValidationDefinition.VALUE_USER_LASTNAME_PATTERN);
			Matcher matcher = pattern.matcher(readValue);
			if (!matcher.matches()) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						RegistrationValidationDefinition.MESSAGE_USER_LASTNAME_PATTERN,
						null);
				throw new ValidatorException(facesMessage);
			}
		}

	}

}
