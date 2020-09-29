package by.epamtc.restaurant.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.restaurant.controller.command.Command;

public class GoToRegistrationPageCommand implements Command {

	private static final String REGISTRATION_PAGE = "WEB-INF/jsp/registration_page.jsp";
	private static final String PARAMETER_REGISTRATION_MESSAGE = "registration_message";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);

		request.getSession().removeAttribute(PARAMETER_REGISTRATION_MESSAGE);
	}
}
