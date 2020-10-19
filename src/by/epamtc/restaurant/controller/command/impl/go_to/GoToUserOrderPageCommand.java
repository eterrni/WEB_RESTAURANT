package by.epamtc.restaurant.controller.command.impl.go_to;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.restaurant.controller.command.Command;
import by.epamtc.restaurant.controller.command.impl.utility.DownloadUserOrderUtility;

public class GoToUserOrderPageCommand implements Command {

	private static final String USER_ORDER_PAGE = "WEB-INF/jsp/user_order_page.jsp";
	private static final String WELCOME_PAGE = "WEB-INF/jsp/welcome_page.jsp";

	private static final String ATTRIBUTE_USER = "user";

	private static final String CONTENT_TYPE = "text/html";
	private static final DownloadUserOrderUtility downloadUtility = DownloadUserOrderUtility.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute(ATTRIBUTE_USER) != null) {
			response.setContentType(CONTENT_TYPE);

			downloadUtility.download(request, response);

			request.getRequestDispatcher(USER_ORDER_PAGE).forward(request, response);
		} else {
			request.getRequestDispatcher(WELCOME_PAGE).forward(request, response);
		}
	}

}
