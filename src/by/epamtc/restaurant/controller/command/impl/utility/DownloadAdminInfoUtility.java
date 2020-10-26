package by.epamtc.restaurant.controller.command.impl.utility;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamtc.restaurant.bean.order.Order;
import by.epamtc.restaurant.bean.user.User;
import by.epamtc.restaurant.service.DownloadAdminInfoService;
import by.epamtc.restaurant.service.ServiceFactory;
import by.epamtc.restaurant.service.exception.ServiceException;

public class DownloadAdminInfoUtility {

	private static final String ATTRIBUTE_UNCONFIRMED_CLIENTS_ORDER_LIST = "unconfirmed_clients_order_list";
	private static final String ATTRIBUTE_CONFIRMED_CLIENTS_ORDER_LIST = "confirmed_clients_order_list";
	private static final String ATTRIBUTE_CLIENTS_LIST = "clients_list";
	private static final String ATTRIBUTE_EMPLOYEES_LIST = "employees_list";
	
	private static final String ATTRIBUTE_ERROR = "error";

	private static final String ERROR_PAGE = "Controller?command=go_to_error_page";

	private static final DownloadAdminInfoUtility instance = new DownloadAdminInfoUtility();

	private static final ServiceFactory factory = ServiceFactory.getInstance();
	private static final DownloadAdminInfoService downloadAdminInfoService = factory.getDownloadAdminInfoService();

	public static DownloadAdminInfoUtility getInstance() {
		return instance;
	}

	public void downloadUnconfirmedOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();

		try {
			List<Order> unconfirmedClientsOrderList = downloadAdminInfoService.downloadUnconfirmedClientsOrderList();

			if (session.getAttribute(ATTRIBUTE_UNCONFIRMED_CLIENTS_ORDER_LIST) != null) {
				session.removeAttribute(ATTRIBUTE_UNCONFIRMED_CLIENTS_ORDER_LIST);
			}

			session.setAttribute(ATTRIBUTE_UNCONFIRMED_CLIENTS_ORDER_LIST, unconfirmedClientsOrderList);
		} catch (ServiceException e) {
			request.getSession().setAttribute(ATTRIBUTE_ERROR, e);
			response.sendRedirect(ERROR_PAGE);
		}

	}

	public void downloadConfirmedOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();

		try {
			List<Order> confirmedClientsOrderList = downloadAdminInfoService.downloadConfirmedClientsOrderList();

			if (session.getAttribute(ATTRIBUTE_CONFIRMED_CLIENTS_ORDER_LIST) != null) {
				session.removeAttribute(ATTRIBUTE_CONFIRMED_CLIENTS_ORDER_LIST);
			}

			session.setAttribute(ATTRIBUTE_CONFIRMED_CLIENTS_ORDER_LIST, confirmedClientsOrderList);
		} catch (ServiceException e) {
			request.getSession().setAttribute(ATTRIBUTE_ERROR, e);
			response.sendRedirect(ERROR_PAGE);
		}

	}

	public void downloadUserList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();

		try {
			List<User> userList = downloadAdminInfoService.downloadUserList();

			if (session.getAttribute(ATTRIBUTE_CLIENTS_LIST) != null) {
				session.removeAttribute(ATTRIBUTE_CLIENTS_LIST);
			}

			session.setAttribute(ATTRIBUTE_CLIENTS_LIST, userList);
		} catch (ServiceException e) {
			request.getSession().setAttribute(ATTRIBUTE_ERROR, e);
			response.sendRedirect(ERROR_PAGE);
		}
	}

	public void downloadEmployeeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();

		try {
			List<User> employeeList = downloadAdminInfoService.downloadEmployeeList();

			if (session.getAttribute(ATTRIBUTE_EMPLOYEES_LIST) != null) {
				session.removeAttribute(ATTRIBUTE_EMPLOYEES_LIST);
			}

			session.setAttribute(ATTRIBUTE_EMPLOYEES_LIST, employeeList);
		} catch (ServiceException e) {
			request.getSession().setAttribute(ATTRIBUTE_ERROR, e);
			response.sendRedirect(ERROR_PAGE);
		}
	}
}
