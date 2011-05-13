package das.tickets.config;

public class RegistrationValidationDefinition {
	public static final int VALUE_USERNAME_MIN_LENGTH = 5;

	public static final int VALUE_USERNAME_MAX_LENGTH = 30;

	public static final int VALUE_USER_PASSWORD_MIN_LENGTH = 5;

	public static final int VALUE_USER_PASSWORD_MAX_LENGTH = 30;

	public static final int VALUE_USER_EMAIL_MIN_LENGTH = 5;

	public static final int VALUE_USER_EMAIL_MAX_LENGTH = 150;

	public static final int VALUE_USER_FIRSTNAME_MIN_LENGTH = 5;

	public static final int VALUE_USER_FIRSTNAME_MAX_LENGTH = 100;

	public static final int VALUE_USER_LASTNAME_MIN_LENGTH = 5;

	public static final int VALUE_USER_LASTNAME_MAX_LENGTH = 100;

	public static final String MESSAGE_EMPTY_VALUE = "May not be empty";

	public static final String MESSAGE_USERNAME_LENGTH = "Length between "
			+ RegistrationValidationDefinition.VALUE_USERNAME_MIN_LENGTH
			+ " and "
			+ RegistrationValidationDefinition.VALUE_USERNAME_MAX_LENGTH
			+ " characters only";

	public static final String MESSAGE_USER_PASSWORD_LENGTH = "Length between "
			+ RegistrationValidationDefinition.VALUE_USER_PASSWORD_MIN_LENGTH
			+ " and "
			+ RegistrationValidationDefinition.VALUE_USER_PASSWORD_MAX_LENGTH
			+ " characters only";

	public static final String MESSAGE_USER_EMAIL_LENGTH = "Length between "
			+ RegistrationValidationDefinition.VALUE_USER_EMAIL_MIN_LENGTH
			+ " and "
			+ RegistrationValidationDefinition.VALUE_USER_EMAIL_MAX_LENGTH
			+ " characters only";

	public static final String MESSAGE_USER_FIRSTNAME_LENGTH = "Length between "
			+ RegistrationValidationDefinition.VALUE_USER_FIRSTNAME_MIN_LENGTH
			+ " and "
			+ RegistrationValidationDefinition.VALUE_USER_FIRSTNAME_MAX_LENGTH
			+ " characters only";

	public static final String MESSAGE_USER_LASTNAME_LENGTH = "Length between "
			+ RegistrationValidationDefinition.VALUE_USER_LASTNAME_MIN_LENGTH
			+ " and "
			+ RegistrationValidationDefinition.VALUE_USER_LASTNAME_MAX_LENGTH
			+ " characters only";

}
