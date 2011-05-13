package das.tickets.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.model.DualListModel;

import das.tickets.config.RegistrationValidationDefinition;

@FacesValidator(value = "das.tickets.validator.EmptyFieldValidator")
public class EmptyFieldValidator implements Validator {

	@SuppressWarnings("unchecked")
	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent,
			Object value) throws ValidatorException {
		if (value instanceof String) {
			String readValue = (String) value;
			if (readValue.isEmpty()) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						RegistrationValidationDefinition.MESSAGE_EMPTY_VALUE, null);
				throw new ValidatorException(facesMessage);
			}
		} else if (value instanceof org.primefaces.model.DualListModel) {
			// Primefaces pick list
			DualListModel<String> dualModelList = (DualListModel<String>) value;
			if (dualModelList.getTarget().isEmpty()) {
				FacesMessage facesMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						RegistrationValidationDefinition.MESSAGE_EMPTY_VALUE, null);
				throw new ValidatorException(facesMessage);
			}
		}

	}

}
