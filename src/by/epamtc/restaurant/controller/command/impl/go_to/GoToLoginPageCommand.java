package by.epamtc.restaurant.controller.command.impl.go_to;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.restaurant.controller.command.Command;

public class GoToLoginPageCommand implements Command {

	private static final String LOGIN_PAGE = "WEB-INF/jsp/login_page.jsp";
	private static final String ATTRIBUTE_AUTHORIZATION_MESSAGE = "authorization_message";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
		
		request.getSession().removeAttribute(ATTRIBUTE_AUTHORIZATION_MESSAGE);

	}

}
