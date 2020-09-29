package by.epamtc.restaurant.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.restaurant.bean.User;
import by.epamtc.restaurant.bean.UserUpdateData;
import by.epamtc.restaurant.controller.command.Command;
import by.epamtc.restaurant.service.ServiceFactory;
import by.epamtc.restaurant.service.UserService;
import by.epamtc.restaurant.service.exception.ServiceException;

public class UpdateUserDataCommand implements Command {

	private static final ServiceFactory factory = ServiceFactory.getInstance();
	private static final UserService userService = factory.getUserService();

	private static final String PARAMETER_NAME = "name";
	private static final String PARAMETER_SURNAME = "surname";
	private static final String PARAMETER_PATRONYMIC = "patronymic";
	private static final String PARAMETER_PHONE_NUMBER = "phoneNumber";
	private static final String PARAMETER_AGE = "age";
	private static final String PARAMETER_EMAIL = "email";
	private static final String PARAMETER_ID = "id";

	private static final String USER = "user";
	private static final String PARAMETER_ERROR = "error";
	private static final String PARAMETER_UPDATE_MESSAGE = "update_message";
	private static final String PERSONAL_ACCOUNT_PAGE = "Controller?command=go_to_personal_account_page";
	private static final String ERROR_PAGE = "Controller?command=go_to_error_page";

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
		User user = (User)request.getSession().getAttribute(USER);
		

		try {
			if (userService.update(userUpdateData, user)) {
				request.getSession().setAttribute(PARAMETER_UPDATE_MESSAGE, "successful_update_data");
				request.getSession().removeAttribute(USER);
				request.getSession().setAttribute(USER, user);
				response.sendRedirect(PERSONAL_ACCOUNT_PAGE);
			} else {
				request.getSession().setAttribute(PARAMETER_UPDATE_MESSAGE, "unsuccessful_update_data");
				response.sendRedirect(PERSONAL_ACCOUNT_PAGE);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			request.setAttribute(PARAMETER_ERROR, e);
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
			e.printStackTrace();
		}

	}

}
