package das.tickets.config;

public class RegistrationValidationDefinition {

	// values
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

	public static final String VALUE_USERNAME_PATTERN = "[a-zA-Z0-9_.@-]+";

	public static final String VALUE_USER_PASSWORD_PATTERN = "[a-zA-Z0-9_-]+";

	public static final String VALUE_USER_EMAIL_PATTERN = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";

	public static final String VALUE_USER_FIRSTNAME_PATTERN = "[a-zA-Z_ .-]+";

	public static final String VALUE_USER_LASTNAME_PATTERN = "[a-zA-Z_ .-]+";

	// messages
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

	public static final String MESSAGE_USER_PATTERN = "Contains invalid characters";

	public static final String MESSAGE_USER_PASSWORD_PATTERN = "Contains invalid characters";

	public static final String MESSAGE_USER_EMAIL_PATTERN = "Contains invalid characters";

	public static final String MESSAGE_USER_FIRSTNAME_PATTERN = "Contains invalid characters";

	public static final String MESSAGE_USER_LASTNAME_PATTERN = "Contains invalid characters";

	public static final String MESSAGE_PASSWORD_CONFIRMATION = "Don't match with password";

	public static final String MESSAGE_USERNAME_UNIQUENESS = "Just registrated";

	public static final String MESSAGE_USER_EMAIL_UNIQUENESS = "Just registrated";

}
