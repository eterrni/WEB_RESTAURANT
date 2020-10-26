package by.epamtc.restaurant.controller.command.impl.go_to;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.restaurant.bean.user.User;
import by.epamtc.restaurant.controller.command.Command;
import by.epamtc.restaurant.controller.command.impl.utility.DownloadAdminInfoUtility;

public class GoToUnconfirmedOrderPageCommand implements Command {

	private static final DownloadAdminInfoUtility downloadAdminInfoUtility = DownloadAdminInfoUtility
			.getInstance();
	private static final String WELCOME_PAGE = "WEB-INF/jsp/welcome_page.jsp";
	private static final String UNCONFIRMED_ORDER_PAGE = "WEB-INF/jsp/admin/unconfirmed_order_page.jsp";

	private static final String ATTRIBUTE_UNCONFIRMED_CLIENTS_ORDER_LIST = "unconfirmed_clients_order_list";
	private static final String ATTRIBUTE_USER = "user";

	private static final Integer USER_ROLE_ID = 2;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);

		if (user == null || user.getRole().getRoleId() == USER_ROLE_ID) {
			request.getRequestDispatcher(WELCOME_PAGE).forward(request, response);
		} else {

			downloadAdminInfoUtility.downloadUnconfirmedOrder(request, response);

			request.getRequestDispatcher(UNCONFIRMED_ORDER_PAGE).forward(request, response);

			session.removeAttribute(ATTRIBUTE_UNCONFIRMED_CLIENTS_ORDER_LIST);

		}
	}
}
