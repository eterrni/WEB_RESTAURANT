package by.epamtc.restaurant.controller.validator;

import by.epamtc.restaurant.bean.user.UserAuthData;
import by.epamtc.restaurant.bean.user.UserRegistrationData;
import by.epamtc.restaurant.bean.user.UserUpdateData;

public class UserValidator {

	private static final String NAME_SURNAME_PATRONYMIC_REGEXP = "[a-zA-Zà-ÿÀ-ß¨¸]{3,20}";
	private static final String AGE_REGEXP = "^(?:1(?:00?|\\d)|[2-5]\\d|[6-9]\\d?)$";
	private static final String LOGIN_REGEXP = "[a-zA-Z][a-zA-Z0-9]{2,14}";
	private static final String PASSWORD_REGEXP = "((?=.*\\d)(?=.*[a-zA-Z]).{5,15})";
	private static final String PHONE_REGEXP = "^[0-9\\(\\)-+\\s]+$";
	private static final String EMAIL_REGEXP = "^[\\w.-_]+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}$";
	
	private static final UserValidator instance = new UserValidator();
	
	public static UserValidator getInstance() {
		return instance;
	}

	public boolean loginValidator(UserAuthData user) {

		String login = user.getLogin();
		String password = user.getPassword();

		return login != null && password != null && login.matches(LOGIN_REGEXP) && password.matches(PASSWORD_REGEXP);
	}

	public boolean registartionValidator(UserRegistrationData user) {

		String name = user.getName();
		String surname = user.getSurname();
		String patronymic = user.getPatronymic();
		String login = user.getLogin();
		String password = user.getPassword();
		String phoneNumber = user.getPhoneNumber();
		String age = user.getAge().toString();
		String email = user.getEmail();

		if ((name == null) || (surname == null) || (patronymic == null) || (login == null) || (password == null)
				|| (phoneNumber == null) || (age == null) || (email == null)) {
			return false;
		}
		return name.matches(NAME_SURNAME_PATRONYMIC_REGEXP) && surname.matches(NAME_SURNAME_PATRONYMIC_REGEXP)
				&& patronymic.matches(NAME_SURNAME_PATRONYMIC_REGEXP) && login.matches(LOGIN_REGEXP)
				&& password.matches(PASSWORD_REGEXP) && phoneNumber.matches(PHONE_REGEXP) && age.matches(AGE_REGEXP)
				&& email.matches(EMAIL_REGEXP);

	}

	public boolean updateDataValidator(UserUpdateData user) {

		String name = user.getName();
		String surname = user.getSurname();
		String patronymic = user.getPatronymic();
		String phoneNumber = user.getPhoneNumber();
		String age = user.getAge().toString();
		String email = user.getEmail();

		if ((name == null) || (surname == null) || (patronymic == null) || (phoneNumber == null) || (age == null)
				|| (email == null)) {
			return false;
		}

		return name.matches(NAME_SURNAME_PATRONYMIC_REGEXP) && surname.matches(NAME_SURNAME_PATRONYMIC_REGEXP)
				&& patronymic.matches(NAME_SURNAME_PATRONYMIC_REGEXP) && phoneNumber.matches(PHONE_REGEXP)
				&& age.matches(AGE_REGEXP) && email.matches(EMAIL_REGEXP);
	}
}
