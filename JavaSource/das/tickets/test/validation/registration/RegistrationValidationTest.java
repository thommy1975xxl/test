package das.tickets.test.validation.registration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;
import org.primefaces.model.DualListModel;

import das.tickets.config.RegistrationValidationDefinition;
import das.tickets.validator.registration.EmptyFieldValidator;
import das.tickets.validator.registration.UserEmailLengthValidator;
import das.tickets.validator.registration.UserEmailPatternValidator;
import das.tickets.validator.registration.UserFirstNameLengthValidator;
import das.tickets.validator.registration.UserFirstNamePatternValidator;
import das.tickets.validator.registration.UserLastNamePatternValidator;
import das.tickets.validator.registration.UserNameLengthValidator;
import das.tickets.validator.registration.UserNamePatternValidator;
import das.tickets.validator.registration.UserNameUniquenessValidator;
import das.tickets.validator.registration.UserPasswordLengthValidator;
import das.tickets.validator.registration.UserPasswordPatternValidator;

public class RegistrationValidationTest {

	private FacesContext facesContext;
	UIComponent uiComponent = null;

	@Before
	public void setup() {
		facesContext = FacesContext.getCurrentInstance();
	}

	@Test
	public void testEmptyFields() {
		EmptyFieldValidator emptyFieldValidator = new EmptyFieldValidator();
		Object value = new String("");
		try {
			emptyFieldValidator.validate(facesContext, uiComponent, value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_EMPTY_VALUE));
		}
	}

	@Test
	public void testUserNameLength_Min() {
		String userName = "";
		for (int i = 0; i < RegistrationValidationDefinition.VALUE_USERNAME_MIN_LENGTH; i++) {
			userName += "x";
		}
		UserNameLengthValidator userNameLengthValidator = new UserNameLengthValidator();
		Object value = new String("");
		try {
			userNameLengthValidator.validate(facesContext, uiComponent, value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USERNAME_LENGTH));
		}
	}

	@Test
	public void testUserNameLength_Max() {
		String userName = "";
		for (int i = 0; i > RegistrationValidationDefinition.VALUE_USERNAME_MAX_LENGTH; i++) {
			userName += "x";
		}
		UserNameLengthValidator userNameLengthValidator = new UserNameLengthValidator();
		Object value = new String("");
		try {
			userNameLengthValidator.validate(facesContext, uiComponent, value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USERNAME_LENGTH));
		}
	}

	@Test
	public void testUserNamePattern_Success_1() {
		String userName = "this_is_correct";
		UserNamePatternValidator userNamePatternValidator = new UserNamePatternValidator();
		Object value = new String(userName);
		try {
			userNamePatternValidator.validate(facesContext, uiComponent, value);
		} catch (javax.faces.validator.ValidatorException e) {
			assertFalse(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_PATTERN));
		}
	}

	@Test
	public void testUserNamePattern_Success_2() {
		String userName = "James_Bond-007";
		UserNamePatternValidator userNamePatternValidator = new UserNamePatternValidator();
		Object value = new String(userName);
		try {
			userNamePatternValidator.validate(facesContext, uiComponent, value);
		} catch (javax.faces.validator.ValidatorException e) {
			assertFalse(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_PATTERN));
		}
	}

	@Test
	public void testUserNamePattern_Failure_1() {
		String userName = "this_is not_correct";
		UserNamePatternValidator userNamePatternValidator = new UserNamePatternValidator();
		Object value = new String(userName);
		try {
			userNamePatternValidator.validate(facesContext, uiComponent, value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_PATTERN));
		}
	}

	@Test
	public void testUserNamePattern_Failure_2() {
		String userName = "1+1=2";
		UserNamePatternValidator userNamePatternValidator = new UserNamePatternValidator();
		Object value = new String(userName);
		try {
			userNamePatternValidator.validate(facesContext, uiComponent, value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_PATTERN));
		}
	}

	@Test
	public void testUserPasswordLength_Min() {
		String userName = "";
		for (int i = 0; i < RegistrationValidationDefinition.VALUE_USER_PASSWORD_MIN_LENGTH; i++) {
			userName += "x";
		}
		UserPasswordLengthValidator userPasswordLengthValidator = new UserPasswordLengthValidator();
		Object value = new String("");
		try {
			userPasswordLengthValidator.validate(facesContext, uiComponent,
					value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USERNAME_LENGTH));
		}
	}

	@Test
	public void testUserPasswordLength_Max() {
		String userName = "";
		for (int i = 0; i > RegistrationValidationDefinition.VALUE_USER_PASSWORD_MAX_LENGTH; i++) {
			userName += "x";
		}
		UserPasswordLengthValidator userPasswordLengthValidator = new UserPasswordLengthValidator();
		Object value = new String("");
		try {
			userPasswordLengthValidator.validate(facesContext, uiComponent,
					value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_PASSWORD_LENGTH));
		}
	}

	@Test
	public void testUserPasswodPattern_Success() {
		String userName = "00-this_is-myPASSWORD-10";
		UserPasswordPatternValidator userPasswordPatternValidator = new UserPasswordPatternValidator();
		Object value = new String(userName);
		try {
			userPasswordPatternValidator.validate(facesContext, uiComponent,
					value);
		} catch (javax.faces.validator.ValidatorException e) {
			assertFalse(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_PASSWORD_PATTERN));
		}
	}

	@Test
	public void testUserPasswodPattern_Failure1() {
		String userName = "this.could_be@email.com";
		UserPasswordPatternValidator userPasswordPatternValidator = new UserPasswordPatternValidator();
		Object value = new String(userName);
		try {
			userPasswordPatternValidator.validate(facesContext, uiComponent,
					value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_PASSWORD_PATTERN));
		}
	}

	@Test
	public void testUserPasswodPattern_Failure2() {
		String userName = "abc!0815&><";
		UserPasswordPatternValidator userPasswordPatternValidator = new UserPasswordPatternValidator();
		Object value = new String(userName);
		try {
			userPasswordPatternValidator.validate(facesContext, uiComponent,
					value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_PASSWORD_PATTERN));
		}
	}

	@Test
	public void testUserEmailLength_Min() {
		String userName = "";
		for (int i = 0; i < RegistrationValidationDefinition.VALUE_USER_EMAIL_MIN_LENGTH; i++) {
			userName += "x";
		}
		UserEmailLengthValidator userEmailLengthValidator = new UserEmailLengthValidator();
		Object value = new String("");
		try {
			userEmailLengthValidator.validate(facesContext, uiComponent, value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_EMAIL_LENGTH));
		}
	}

	@Test
	public void testUserEmailLength_Max() {
		String userName = "";
		for (int i = 0; i > RegistrationValidationDefinition.VALUE_USER_EMAIL_MAX_LENGTH; i++) {
			userName += "x";
		}
		UserEmailLengthValidator userEmailLengthValidator = new UserEmailLengthValidator();
		Object value = new String("");
		try {
			userEmailLengthValidator.validate(facesContext, uiComponent, value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_EMAIL_LENGTH));
		}
	}

	@Test
	public void testUserEmailPattern_Success() {
		String userName = "this.is_myNew@Email.com";
		UserEmailPatternValidator userEmailPatternValidator = new UserEmailPatternValidator();
		Object value = new String(userName);
		try {
			userEmailPatternValidator
					.validate(facesContext, uiComponent, value);
		} catch (javax.faces.validator.ValidatorException e) {
			assertFalse(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_EMAIL_PATTERN));
		}
	}

	@Test
	public void testUserEmailPattern_Failure1() {
		String userName = "this.is-myNew@Email.com";
		UserEmailPatternValidator userEmailPatternValidator = new UserEmailPatternValidator();
		Object value = new String(userName);
		try {
			userEmailPatternValidator
					.validate(facesContext, uiComponent, value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_EMAIL_PATTERN));
		}
	}

	@Test
	public void testUserEmailPattern_Failure2() {
		String userName = "0815_$_@Email.com";
		UserEmailPatternValidator userEmailPatternValidator = new UserEmailPatternValidator();
		Object value = new String(userName);
		try {
			userEmailPatternValidator
					.validate(facesContext, uiComponent, value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_EMAIL_PATTERN));
		}
	}

	@Test
	public void testFirstNameLength_Min() {
		String userName = "";
		for (int i = 0; i < RegistrationValidationDefinition.VALUE_USER_FIRSTNAME_MIN_LENGTH; i++) {
			userName += "x";
		}
		UserFirstNameLengthValidator userFirstNameLengthValidator = new UserFirstNameLengthValidator();
		Object value = new String("");
		try {
			userFirstNameLengthValidator.validate(facesContext, uiComponent,
					value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_FIRSTNAME_LENGTH));
		}
	}

	@Test
	public void testFirstNameLength_Max() {
		String userName = "";
		for (int i = 0; i > RegistrationValidationDefinition.VALUE_USER_FIRSTNAME_MAX_LENGTH; i++) {
			userName += "x";
		}
		UserFirstNameLengthValidator userFirstNameLengthValidator = new UserFirstNameLengthValidator();
		Object value = new String("");
		try {
			userFirstNameLengthValidator.validate(facesContext, uiComponent,
					value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_FIRSTNAME_LENGTH));
		}
	}

	@Test
	public void testUserFirstNamePattern_Success1() {
		String userName = "Iam a normal USER";
		UserFirstNamePatternValidator userFirstNamePatternValidator = new UserFirstNamePatternValidator();
		Object value = new String(userName);
		try {
			userFirstNamePatternValidator.validate(facesContext, uiComponent,
					value);
		} catch (javax.faces.validator.ValidatorException e) {
			assertFalse(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_FIRSTNAME_PATTERN));
		}
	}

	@Test
	public void testUserFirstNamePattern_Success2() {
		String userName = "Dr. Mabuse";
		UserFirstNamePatternValidator userFirstNamePatternValidator = new UserFirstNamePatternValidator();
		Object value = new String(userName);
		try {
			userFirstNamePatternValidator.validate(facesContext, uiComponent,
					value);
		} catch (javax.faces.validator.ValidatorException e) {
			assertFalse(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_FIRSTNAME_PATTERN));
		}
	}

	@Test
	public void testUserFirstNamePattern_Failure1() {
		String userName = "I'am a normal USER";
		UserFirstNamePatternValidator userFirstNamePatternValidator = new UserFirstNamePatternValidator();
		Object value = new String(userName);
		try {
			userFirstNamePatternValidator.validate(facesContext, uiComponent,
					value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_FIRSTNAME_PATTERN));
		}
	}

	@Test
	public void testUserFirstNamePattern_Failure2() {
		String userName = "you&me";
		UserFirstNamePatternValidator userFirstNamePatternValidator = new UserFirstNamePatternValidator();
		Object value = new String(userName);
		try {
			userFirstNamePatternValidator.validate(facesContext, uiComponent,
					value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_FIRSTNAME_PATTERN));
		}
	}

	@Test
	public void testUserLastNamePattern_Success() {
		String userName = "This is my LAST-Name";
		UserLastNamePatternValidator userLastNamePatternValidator = new UserLastNamePatternValidator();
		Object value = new String(userName);
		try {
			userLastNamePatternValidator.validate(facesContext, uiComponent,
					value);
		} catch (javax.faces.validator.ValidatorException e) {
			assertFalse(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_LASTNAME_PATTERN));
		}
	}

	@Test
	public void testUserLastNamePattern_Success2() {
		String userName = "XTC and CO. KG";
		UserLastNamePatternValidator userLastNamePatternValidator = new UserLastNamePatternValidator();
		Object value = new String(userName);
		try {
			userLastNamePatternValidator.validate(facesContext, uiComponent,
					value);
		} catch (javax.faces.validator.ValidatorException e) {
			assertFalse(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_LASTNAME_PATTERN));
		}
	}

	@Test
	public void testUserLastNamePattern_Failure() {
		String userName = "XTC & CO. KG";
		UserLastNamePatternValidator userLastNamePatternValidator = new UserLastNamePatternValidator();
		Object value = new String(userName);
		try {
			userLastNamePatternValidator.validate(facesContext, uiComponent,
					value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USER_LASTNAME_PATTERN));
		}
	}

	@Test
	public void testEmptyPrimefacesPickList() {
		List<String> sourceRole = new ArrayList<String>();
		sourceRole.add("");
		List<String> targetRole = new ArrayList<String>();
		DualListModel<String> roleDefinitions = new DualListModel<String>(
				sourceRole, targetRole);
		Object value = new String(sourceRole.get(0));
		EmptyFieldValidator emptyFieldValidator = new EmptyFieldValidator();
		try {
			emptyFieldValidator.validate(facesContext, uiComponent, value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_EMPTY_VALUE));
		}
		assertTrue(roleDefinitions.getSource().size() > 0);
		assertFalse(roleDefinitions.getTarget().size() > 0);
	}

	@Test
	public void testUserNameUniqueness() {
		String userName = "admin";
		UserNameUniquenessValidator uniquenessValidator = new UserNameUniquenessValidator();
		Object value = new String(userName);
		try {
			uniquenessValidator.validate(facesContext, uiComponent, value);
			fail();
		} catch (javax.faces.validator.ValidatorException e) {
			assertTrue(e
					.getFacesMessage()
					.getDetail()
					.equals(RegistrationValidationDefinition.MESSAGE_USERNAME_UNIQUENESS));
		}
	}

}
