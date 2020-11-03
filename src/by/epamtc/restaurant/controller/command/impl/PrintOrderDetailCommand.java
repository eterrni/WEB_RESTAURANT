package by.epamtc.restaurant.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.restaurant.bean.goods.Goods;
import by.epamtc.restaurant.controller.command.Command;
import by.epamtc.restaurant.service.ServiceFactory;
import by.epamtc.restaurant.service.UserFeaturesService;
import by.epamtc.restaurant.service.exception.ServiceException;

public class PrintOrderDetailCommand implements Command {

	private static final String ATTRIBUTE_USER = "user";

	private static final String WELCOME_PAGE = "Controller?command=go_to_welcome_page";
	private static final String ORDER_DETAIL_PAGE = "Controller?command=go_to_order_detail_page";
	private static final String ERROR_PAGE = "Controller?command=go_to_error_page";

	private static final String PARAMETER_ORDER_ID = "order_id";
	private static final String ATTRIBUTE_ORDER_DETAIL_LIST = "order_detail_list";

	private static final String ATTRIBUTE_ERROR = "error";

	private static final String LOGGER_MESSAGE = "PrintOrderDetailCommand exception";

	private static final ServiceFactory factory = ServiceFactory.getInstance();
	private static final UserFeaturesService userFeaturesService = factory.getUserFeaturesService();

	private static final Logger logger = LogManager.getLogger(PrintOrderDetailCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String page = null;

		if (session.getAttribute(ATTRIBUTE_USER) == null) {
			response.sendRedirect(WELCOME_PAGE);
		} else {

			try {

				Integer orderId = Integer.parseInt(request.getParameter(PARAMETER_ORDER_ID));
				List<Goods> orderDetailList = userFeaturesService.getOrderDetailList(orderId);

				session.setAttribute(ATTRIBUTE_ORDER_DETAIL_LIST, orderDetailList);
				page = ORDER_DETAIL_PAGE;

			} catch (ServiceException e) {
				logger.error(LOGGER_MESSAGE);
				request.getSession().setAttribute(ATTRIBUTE_ERROR, e);
				page = ERROR_PAGE;

			} finally {
				response.sendRedirect(page);
			}
		}
	}
}
