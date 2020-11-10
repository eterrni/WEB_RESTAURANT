package by.epamtc.restaurant.controller.command.impl.go_to;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.restaurant.controller.command.Command;

public class GoToRegistrationPageCommand implements Command {

	private static final String REGISTRATION_PAGE = "WEB-INF/jsp/registration_page.jsp";
	private static final String ATTRIBUTE_REGISTRATION_MESSAGE = "registration_message";
	private static final String CONTENT_TYPE = "text/html";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);

		request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);

		request.getSession().removeAttribute(ATTRIBUTE_REGISTRATION_MESSAGE);
	}
}
