package by.epamtc.restaurant.controller.command.impl.go_to;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.restaurant.bean.user.User;
import by.epamtc.restaurant.controller.command.Command;

public class GoToAdminPageCommand implements Command {

	private static final String ADMIN_PAGE = "WEB-INF/jsp/admin_page.jsp";
	private static final String WELCOME_PAGE = "WEB-INF/jsp/welcome_page.jsp";

	private static final String CONTENT_TYPE = "text/html";

	private static final Integer USER_ROLE_ID = 2;
	private static final String ATTRIBUTE_USER = "user";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute(ATTRIBUTE_USER);
		if ((user == null) || (user.getRole().getRoleId() == USER_ROLE_ID)) {
			request.getRequestDispatcher(WELCOME_PAGE).forward(request, response);
		} else {
			response.setContentType(CONTENT_TYPE);

			request.getRequestDispatcher(ADMIN_PAGE).forward(request, response);
		}

	}

}
