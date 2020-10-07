package by.epamtc.restaurant.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.restaurant.bean.user.UserRegistrationData;
import by.epamtc.restaurant.controller.command.Command;
import by.epamtc.restaurant.service.ServiceFactory;
import by.epamtc.restaurant.service.UserService;
import by.epamtc.restaurant.service.exception.ServiceException;
import by.epamtc.restaurant.service.exception.UserExistsServiceException;

public class RegistrationCommand implements Command {

	private static final String PARAMETER_NAME = "name";
	private static final String PARAMETER_SURNAME = "surname";
	private static final String PARAMETER_PATRONYMIC = "patronymic";
	private static final String PARAMETER_LOGIN = "login";
	private static final String PARAMETER_PASSWORD = "password";
	private static final String PARAMETER_PHONE_NUMBER = "phoneNumber";
	private static final String PARAMETER_AGE = "age";
	private static final String PARAMETER_EMAIL = "email";

	private static final String ATTRIBUTE_REGISTRATION_MESSAGE = "registration_message";
	private static final String ATTRIBUTE_ERROR = "error";

	private static final String REGISTRATION_SUCCESSFUL_MESSAGE = "registration_successful";
	private static final String REGISTRATION_UNSUCCESSFUL_MESSAGE = "registration_unsuccessful";
	private static final String USER_EXIST_MESSAGE = "user_exist";
	private static final String LOGGER_MESSAGE = "RegistrationCommand exception";

	private static final String REGISTRATION_PAGE = "Controller?command=go_to_registration_page";
	private static final String ERROR_PAGE = "Controller?command=go_to_error_page";

	private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);
	private static final ServiceFactory factory = ServiceFactory.getInstance();
	private final UserService service = factory.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter(PARAMETER_NAME);
		String surname = request.getParameter(PARAMETER_SURNAME);
		String patronymic = request.getParameter(PARAMETER_PATRONYMIC);
		String login = request.getParameter(PARAMETER_LOGIN);
		String password = request.getParameter(PARAMETER_PASSWORD);
		String phoneNumber = request.getParameter(PARAMETER_PHONE_NUMBER);
		Integer age = Integer.parseInt(request.getParameter(PARAMETER_AGE));
		String email = request.getParameter(PARAMETER_EMAIL);

		UserRegistrationData userRegistrationData = new UserRegistrationData();

		userRegistrationData.setName(name);
		userRegistrationData.setSurname(surname);
		userRegistrationData.setPatronymic(patronymic);
		userRegistrationData.setLogin(login);
		userRegistrationData.setPassword(password);
		userRegistrationData.setPhoneNumber(phoneNumber);
		userRegistrationData.setAge(age);
		userRegistrationData.setEmail(email);

		try {

			if (service.registration(userRegistrationData)) {
				request.getSession().setAttribute(ATTRIBUTE_REGISTRATION_MESSAGE, REGISTRATION_SUCCESSFUL_MESSAGE);
			} else {
				request.getSession().setAttribute(ATTRIBUTE_REGISTRATION_MESSAGE, REGISTRATION_UNSUCCESSFUL_MESSAGE);
			}

		} catch (UserExistsServiceException e) {
			request.getSession().setAttribute(ATTRIBUTE_REGISTRATION_MESSAGE, USER_EXIST_MESSAGE);
		} catch (ServiceException e) {
			logger.error(LOGGER_MESSAGE);
			request.getSession().setAttribute(ATTRIBUTE_ERROR, e);
			response.sendRedirect(ERROR_PAGE);
		}

		response.sendRedirect(REGISTRATION_PAGE);
	}
}
