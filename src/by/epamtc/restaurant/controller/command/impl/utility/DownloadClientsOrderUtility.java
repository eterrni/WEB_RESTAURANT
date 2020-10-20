package by.epamtc.restaurant.controller.command.impl.utility;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.restaurant.bean.order.Order;
import by.epamtc.restaurant.service.DownloadClientsOrderService;
import by.epamtc.restaurant.service.ServiceFactory;
import by.epamtc.restaurant.service.exception.ServiceException;

public class DownloadClientsOrderUtility {

	private static final String ATTRIBUTE_UNCONFIRMED_CLIENTS_ORDER_LIST = "unconfirmed_clients_order_list";
	private static final String ATTRIBUTE_CONFIRMED_CLIENTS_ORDER_LIST = "confirmed_clients_order_list";
	private static final String ATTRIBUTE_ERROR = "error";

	private static final String ERROR_PAGE = "Controller?command=go_to_error_page";

	private static final DownloadClientsOrderUtility instance = new DownloadClientsOrderUtility();

	private static final ServiceFactory factory = ServiceFactory.getInstance();
	private static final DownloadClientsOrderService downloadClientsOrder = factory.getDownloadClientsOrderService();
	
	public static DownloadClientsOrderUtility getInstance() {
		return instance;
	}

	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();

		try {
			List<Order> unconfirmedClientsOrderList = downloadClientsOrder.downloadUnconfirmedClientsOrderList();
			List<Order> confirmedClientsOrderList = downloadClientsOrder.downloadConfirmedClientsOrderList();

			if (session.getAttribute(ATTRIBUTE_UNCONFIRMED_CLIENTS_ORDER_LIST) != null) {
				session.removeAttribute(ATTRIBUTE_UNCONFIRMED_CLIENTS_ORDER_LIST);
			}
			if (session.getAttribute(ATTRIBUTE_CONFIRMED_CLIENTS_ORDER_LIST) != null) {
				session.removeAttribute(ATTRIBUTE_CONFIRMED_CLIENTS_ORDER_LIST);
			}

			session.setAttribute(ATTRIBUTE_UNCONFIRMED_CLIENTS_ORDER_LIST, unconfirmedClientsOrderList);
			session.setAttribute(ATTRIBUTE_CONFIRMED_CLIENTS_ORDER_LIST, confirmedClientsOrderList);
		} catch (ServiceException e) {
			request.getSession().setAttribute(ATTRIBUTE_ERROR, e);
			response.sendRedirect(ERROR_PAGE);
		}

	}

}
