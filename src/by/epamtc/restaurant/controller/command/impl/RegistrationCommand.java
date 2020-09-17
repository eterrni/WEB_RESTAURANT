package by.epamtc.restaurant.controller.command.impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.restaurant.controller.command.Command;
import by.epamtc.restaurant.service.Service;
import by.epamtc.restaurant.service.ServiceFactory;
import by.epamtc.restaurant.service.exception.ServiceException;

public class RegistrationCommand implements Command {

	private static final ServiceFactory factory = ServiceFactory.getInstance();
	private final Service service = factory.getRegistrationService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			service.execute(request, response);
		} catch (ServiceException e) {
			//TO DO
			e.printStackTrace();
		}
	}
}
