package by.epamtc.restaurant.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.restaurant.service.exception.ServiceException;
import by.epamtc.restaurant.bean.User;
import by.epamtc.restaurant.bean.UserAuthData;
import by.epamtc.restaurant.controller.command.Command;
import by.epamtc.restaurant.service.UserService;
import by.epamtc.restaurant.service.ServiceFactory;

public class LoginCommand implements Command {

	private static final ServiceFactory factory = ServiceFactory.getInstance();
	private static final UserService userService = factory.getUserService();

	private static final String PARAMETER_LOGIN = "login";
	private static final String PARAMETER_PASSWORD = "password";
	private static final String PARAMETER_USER = "user";

	private static final String PARAMETER_ERROR = "error";
	private static final String PARAMETER_AUTHORIZATION_MESSAGE = "authorization_message";
	private static final String WELCOME_PAGE = "Controller?command=go_to_welcome_page";
	private static final String LOGIN_PAGE = "Controller?command=go_to_login_page";
	private static final String ERROR_PAGE = "Controller?command=go_to_error_page";
	private static final String ERROR_MESSAGE = "Incorrect data";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String login = request.getParameter(PARAMETER_LOGIN);
		String password = request.getParameter(PARAMETER_PASSWORD);

		UserAuthData userAuthData = new UserAuthData(login, password);

		HttpSession session;
		User user;

		try {
			user = userService.authorization(userAuthData);
			if (user != null) {
				session = request.getSession();
				session.setAttribute(PARAMETER_USER, user);
				response.sendRedirect(WELCOME_PAGE);
			} else {
				request.getServletContext().setAttribute(PARAMETER_AUTHORIZATION_MESSAGE, ERROR_MESSAGE);
				response.sendRedirect(LOGIN_PAGE);
			}
		} catch (ServiceException e) {
			// log
			request.setAttribute(PARAMETER_ERROR, e);
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
			e.printStackTrace();
		}
	}
}
