package by.epamtc.restaurant.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.restaurant.bean.goods.Goods;
import by.epamtc.restaurant.bean.order.Order;
import by.epamtc.restaurant.bean.user.User;
import by.epamtc.restaurant.controller.command.Command;
import by.epamtc.restaurant.service.PlaceOrderService;
import by.epamtc.restaurant.service.ServiceFactory;
import by.epamtc.restaurant.service.exception.ServiceException;

public class PlaceOrderCommand implements Command {

	public static final String GRATITUDE_PAGE = "Controller?command=go_to_gratitude_page";
	public static final String WELCOME_PAGE = "Controller?command=go_to_welcome_page";
	private static final String ERROR_PAGE = "Controller?command=go_to_error_page";

	private static final String ATTRIBUTE_ERROR = "error";
	private static final String LOGGER_MESSAGE = "PlaceOrderCommand exception";

	private static final Logger logger = LogManager.getLogger(LoginCommand.class);

	public static final ServiceFactory factory = ServiceFactory.getInstance();
	public static final PlaceOrderService placeOrderImpl = factory.getPlaceOrderService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String page;

		if (request.getSession().getAttribute("user") != null) {
			try {

				User user = (User) request.getSession().getAttribute("user");
				Integer userId = user.getId();

				Order order = (Order) request.getSession().getAttribute("order");
				List<Goods> orderList = order.getOrderList();

				placeOrderImpl.placeOrder(userId, orderList);

			} catch (ServiceException e) {
				logger.error(LOGGER_MESSAGE);
				request.getSession().setAttribute(ATTRIBUTE_ERROR, e);
				page = ERROR_PAGE;
			}

			page = GRATITUDE_PAGE;
		} else {
			page = WELCOME_PAGE;
		}
		
		response.sendRedirect(page);
	}
}
