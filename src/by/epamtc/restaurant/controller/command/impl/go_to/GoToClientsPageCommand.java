package by.epamtc.restaurant.controller.command.impl.go_to;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.restaurant.bean.user.User;
import by.epamtc.restaurant.controller.command.Command;
import by.epamtc.restaurant.controller.command.impl.utility.DownloadAdminInfoUtility;

public class GoToClientsPageCommand implements Command {

	private static final DownloadAdminInfoUtility downloadAdminInfoUtility = DownloadAdminInfoUtility.getInstance();
	private static final String WELCOME_PAGE = "WEB-INF/jsp/welcome_page.jsp";
	private static final String CLIENTS_PAGE = "WEB-INF/jsp/admin/clients_page.jsp";

	private static final String ATTRIBUTE_CLIENTS_LIST = "clients_list";
	private static final String ATTRIBUTE_USER = "user";

	private static final Integer USER_ROLE_ID = 2;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ATTRIBUTE_USER);

		if (user == null || user.getRole().getRoleId() == USER_ROLE_ID) {
			request.getRequestDispatcher(WELCOME_PAGE).forward(request, response);
		} else {

			downloadAdminInfoUtility.downloadUserList(request, response);

			request.getRequestDispatcher(CLIENTS_PAGE).forward(request, response);

			session.removeAttribute(ATTRIBUTE_CLIENTS_LIST);

		}

	}

}
