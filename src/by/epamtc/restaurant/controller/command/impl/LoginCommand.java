package by.epamtc.restaurant.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.restaurant.service.exception.ServiceException;
import by.epamtc.restaurant.bean.user.User;
import by.epamtc.restaurant.bean.user.UserAuthData;
import by.epamtc.restaurant.controller.command.Command;
import by.epamtc.restaurant.service.UserService;
import by.epamtc.restaurant.service.ServiceFactory;

public class LoginCommand implements Command {

	private static final String PARAMETER_LOGIN = "login";
	private static final String PARAMETER_PASSWORD = "password";

	private static final String ATTRIBUTE_USER = "user";
	private static final String ATTRIBUTE_ERROR = "error";
	private static final String ATTRIBUTE_AUTHORIZATION_MESSAGE = "authorization_message";

	private static final String WELCOME_PAGE = "Controller?command=go_to_welcome_page";
	private static final String LOGIN_PAGE = "Controller?command=go_to_login_page";
	private static final String ERROR_PAGE = "Controller?command=go_to_error_page";

	private static final String INCORRECT_DATA_MESSAGE = "incorrect_data";
	private static final String LOGGER_MESSAGE = "LoginCommand exception";

	private static final Logger logger = LogManager.getLogger(LoginCommand.class);

	private static final ServiceFactory factory = ServiceFactory.getInstance();
	private static final UserService userService = factory.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String login = request.getParameter(PARAMETER_LOGIN);
		String password = request.getParameter(PARAMETER_PASSWORD);

		UserAuthData userAuthData = new UserAuthData(login, password);

		String page = WELCOME_PAGE;
		User user;

		try {
			user = userService.authorization(userAuthData);

			if (user != null) {
				request.getSession().setAttribute(ATTRIBUTE_USER, user);
				page = WELCOME_PAGE;

			} else {
				request.getSession().setAttribute(ATTRIBUTE_AUTHORIZATION_MESSAGE, INCORRECT_DATA_MESSAGE);
				page = LOGIN_PAGE;
			}

		} catch (ServiceException e) {
			logger.error(LOGGER_MESSAGE);
			request.getSession().setAttribute(ATTRIBUTE_ERROR, e);
			page = ERROR_PAGE;
		} finally {
			response.sendRedirect(page);
		}
	}
}
