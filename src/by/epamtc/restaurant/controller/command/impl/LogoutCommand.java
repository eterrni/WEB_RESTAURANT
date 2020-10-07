package by.epamtc.restaurant.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.restaurant.controller.command.Command;

public class LogoutCommand implements Command {

	private static final String WELCOME_PAGE = "Controller?command=go_to_welcome_page";
	private static final String ATTRIBUTE_USER = "user";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session != null) {
			session.removeAttribute(ATTRIBUTE_USER);
		}
		response.sendRedirect(WELCOME_PAGE);
	}

}
