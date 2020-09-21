package by.epamtc.restaurant.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String COMMAND_NAME = "command";

	private static final String LOGIN = "login";
	private static final String REGISTRATION = "registration";

	private static final String LOGIN_PAGE = "WEB-INF/jsp/login_page.jsp";
	private static final String REGISTRATION_PAGE = "WEB-INF/jsp/registration_page.jsp";
	private static final String WELCOME_PAGE = "WEB-INF/jsp/welcome_page.jsp";

	public TransferServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String currentCommand = request.getParameter(COMMAND_NAME);
		currentCommand = currentCommand.toLowerCase();

		switch (currentCommand) {
		case LOGIN:
			request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
			break;
		case REGISTRATION:
			request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
			break;
		default:
			request.getRequestDispatcher(WELCOME_PAGE).forward(request, response);
			break;
		}

	}

}
