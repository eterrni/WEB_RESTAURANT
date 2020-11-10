package by.epamtc.restaurant.controller.command.impl.go_to;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.restaurant.controller.command.Command;

public class GoToContactPageCommand implements Command {

	private static final String CONTACT_PAGE = "WEB-INF/jsp/contact_page.jsp";
	private static final String CONTENT_TYPE = "text/html";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);

		request.getRequestDispatcher(CONTACT_PAGE).forward(request, response);

	}

}
