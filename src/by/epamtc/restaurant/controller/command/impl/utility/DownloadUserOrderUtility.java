package by.epamtc.restaurant.controller.command.impl.utility;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.restaurant.bean.order.Order;
import by.epamtc.restaurant.bean.user.User;
import by.epamtc.restaurant.service.DownloadUserOrderService;
import by.epamtc.restaurant.service.ServiceFactory;
import by.epamtc.restaurant.service.exception.ServiceException;

public class DownloadUserOrderUtility {

	private static final DownloadUserOrderUtility instance = new DownloadUserOrderUtility();

	private static final String ATTRIBUTE_USER_ORDER_LIST = "user_order_list";
	private static final String ATTRIBUTE_ERROR = "error";
	private static final String ATTRIBUTE_USER = "user";

	private static final String ERROR_PAGE = "Controller?command=go_to_error_page";

	private static final ServiceFactory factory = ServiceFactory.getInstance();
	private static final DownloadUserOrderService downloadUserService = factory.getDownloadUserOrderService();

	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute(ATTRIBUTE_USER);
		Integer userId = user.getId();

		try {
			List<Order> userOrderList = downloadUserService.download(userId);

			if (session.getAttribute(ATTRIBUTE_USER_ORDER_LIST) != null) {
				session.removeAttribute(ATTRIBUTE_USER_ORDER_LIST);
			}

			session.setAttribute(ATTRIBUTE_USER_ORDER_LIST, userOrderList);

		} catch (ServiceException e) {
			request.getSession().setAttribute(ATTRIBUTE_ERROR, e);
			response.sendRedirect(ERROR_PAGE);
		}
	}

	public static DownloadUserOrderUtility getInstance() {
		return instance;
	}

}
