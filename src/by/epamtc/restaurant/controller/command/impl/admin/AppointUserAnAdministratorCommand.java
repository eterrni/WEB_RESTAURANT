package by.epamtc.restaurant.controller.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.restaurant.controller.command.Command;
import by.epamtc.restaurant.service.AdminService;
import by.epamtc.restaurant.service.ServiceFactory;
import by.epamtc.restaurant.service.exception.ServiceException;

public class AppointUserAnAdministratorCommand implements Command {

	private static final String PARAMETER_USER_ID = "user_id";

	private static final String CLIENTS_PAGE = "Controller?command=go_to_clients_page";
	private static final String ERROR_PAGE = "Controller?command=go_to_error_page";

	private static final String LOGGER_MESSAGE = "AppointUserAnAdministrator exception";
	private static final String ATTRIBUTE_ERROR = "error";

	private static final ServiceFactory factory = ServiceFactory.getInstance();
	private static final AdminService adminService = factory.getAdminService();

	private static final Logger logger = LogManager.getLogger(AppointUserAnAdministratorCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = null;

		try {

			Integer userId = Integer.parseInt(request.getParameter(PARAMETER_USER_ID));
			adminService.appointUserAnAdministrator(userId);
			page = CLIENTS_PAGE;

		} catch (ServiceException e) {
			logger.error(LOGGER_MESSAGE);
			request.getSession().setAttribute(ATTRIBUTE_ERROR, e);
			page = ERROR_PAGE;

		} finally {
			response.sendRedirect(page);
		}

	}

}
