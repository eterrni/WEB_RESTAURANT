package by.epamtc.restaurant.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.restaurant.bean.user.User;
import by.epamtc.restaurant.bean.user.UserUpdateData;
import by.epamtc.restaurant.controller.command.Command;
import by.epamtc.restaurant.service.ServiceFactory;
import by.epamtc.restaurant.service.UserService;
import by.epamtc.restaurant.service.exception.ServiceException;

public class UpdateUserDataCommand implements Command {

	private static final String PARAMETER_NAME = "name";
	private static final String PARAMETER_SURNAME = "surname";
	private static final String PARAMETER_PATRONYMIC = "patronymic";
	private static final String PARAMETER_PHONE_NUMBER = "phoneNumber";
	private static final String PARAMETER_AGE = "age";
	private static final String PARAMETER_EMAIL = "email";
	private static final String PARAMETER_ID = "id";

	private static final String ATTRIBUTE_USER = "user";
	private static final String ATTRIBUTE_ERROR = "error";
	private static final String ATTRIBUTE_UPDATE = "update_message";

	private static final String SUCCESSFUL_UPDATE_MESSAGE = "successful_update_data";
	private static final String UNSUCCESSFUL_UPDATE_MESSAGE = "unsuccessful_update_data";
	private static final String LOGGER_MESSAGE = "UpdateUserDataCommand exception";

	private static final String PERSONAL_ACCOUNT_PAGE = "Controller?command=go_to_personal_account_page";
	private static final String ERROR_PAGE = "Controller?command=go_to_error_page";

	private static final Logger logger = LogManager.getLogger(UpdateUserDataCommand.class);
	private static final ServiceFactory factory = ServiceFactory.getInstance();
	private static final UserService userService = factory.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter(PARAMETER_NAME);
		String surname = request.getParameter(PARAMETER_SURNAME);
		String patronymic = request.getParameter(PARAMETER_PATRONYMIC);
		String phoneNumber = request.getParameter(PARAMETER_PHONE_NUMBER);
		Integer age = Integer.parseInt(request.getParameter(PARAMETER_AGE));
		String email = request.getParameter(PARAMETER_EMAIL);
		Integer id = Integer.parseInt(request.getParameter(PARAMETER_ID));

		UserUpdateData userUpdateData = new UserUpdateData(name, surname, patronymic, phoneNumber, age, email, id);
		User user = (User) request.getSession().getAttribute(ATTRIBUTE_USER);
		String page = PERSONAL_ACCOUNT_PAGE;

		try {
			if (userService.update(userUpdateData, user)) {
				request.getSession().setAttribute(ATTRIBUTE_UPDATE, SUCCESSFUL_UPDATE_MESSAGE);
				request.getSession().removeAttribute(ATTRIBUTE_USER);
				request.getSession().setAttribute(ATTRIBUTE_USER, user);
				page = PERSONAL_ACCOUNT_PAGE;
			} else {
				request.getSession().setAttribute(ATTRIBUTE_UPDATE, UNSUCCESSFUL_UPDATE_MESSAGE);
				page = PERSONAL_ACCOUNT_PAGE;
			}
		} catch (ServiceException e) {
			logger.error(LOGGER_MESSAGE);
			request.getSession().setAttribute(ATTRIBUTE_ERROR, e);
			page = ERROR_PAGE;
		}
		response.sendRedirect(page);
	}

}
