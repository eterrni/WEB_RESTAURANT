package by.epamtc.restaurant.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.restaurant.controller.command.Command;

public class GoToPersonalAccountPageCommand implements Command{
	
	private static final String PERSONAL_ACCOUNT_PAGE = "WEB-INF/jsp/personal_account_page.jsp";
	private static final String PARAMETER_UPDATE_MESSAGE = "update_message";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		request.getRequestDispatcher(PERSONAL_ACCOUNT_PAGE).forward(request, response);
		request.getSession().removeAttribute(PARAMETER_UPDATE_MESSAGE);

	}
}
