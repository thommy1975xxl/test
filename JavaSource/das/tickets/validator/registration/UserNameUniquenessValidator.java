package das.tickets.validator.registration;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import das.tickets.config.RegistrationValidationDefinition;
import das.tickets.dao.RegistrationDao;
import das.tickets.domain.User;

@FacesValidator(value = "das.tickets.validator.registration.UserNameUniquenessValidator")
public class UserNameUniquenessValidator implements Validator {

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent,
			Object value) throws ValidatorException {

		if (value instanceof String) {
			RegistrationDao registrationDao = new RegistrationDao();
			List<User> users = registrationDao.findUserByName((String) value);
			if (users.size() > 0) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						RegistrationValidationDefinition.MESSAGE_USERNAME_UNIQUENESS,
						null);
				throw new ValidatorException(facesMessage);
			}

		}

	}
}
