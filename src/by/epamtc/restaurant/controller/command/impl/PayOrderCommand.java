package by.epamtc.restaurant.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.restaurant.controller.command.Command;
import by.epamtc.restaurant.service.ServiceFactory;
import by.epamtc.restaurant.service.UserFeaturesService;
import by.epamtc.restaurant.service.exception.ServiceException;

public class PayOrderCommand implements Command {

	private static final String GO_TO_WELCOME_PAGE = "Controller?command=go_to_welcome_page";
	private static final String GO_TO_USER_PAYMENT_PAGE = "Controller?command=go_to_user_payment_page";

	private static final String ATTRIBUTE_USER = "user";
	private static final String ATTRIBUTE_ERROR = "error";

	private static final String PARAMETER_PAYMENT_ID = "paymentId";

	private static final String LOGGER_MESSAGE = "UpdateUserDataCommand exception";
	private static final String ERROR_PAGE = "Controller?command=go_to_error_page";

	private static final Logger logger = LogManager.getLogger(PayOrderCommand.class);
	private static final ServiceFactory factory = ServiceFactory.getInstance();
	private static final UserFeaturesService userFeaturesService = factory.getUserFeaturesService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String page = null;
		if (request.getSession().getAttribute(ATTRIBUTE_USER) != null) {
			try {
				Integer paymentId = Integer.parseInt(request.getParameter(PARAMETER_PAYMENT_ID));
				userFeaturesService.payOrder(paymentId);
				page = GO_TO_USER_PAYMENT_PAGE;
			} catch (ServiceException e) {
				logger.error(LOGGER_MESSAGE);
				request.getSession().setAttribute(ATTRIBUTE_ERROR, e);
				page = ERROR_PAGE;
			}
		} else {
			page = GO_TO_WELCOME_PAGE;
		}
		response.sendRedirect(page);
	}

}
