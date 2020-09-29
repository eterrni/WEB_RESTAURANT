package by.epamtc.restaurant.service;

import by.epamtc.restaurant.service.impl.UserServiceImpl;
import by.epamtc.restaurant.service.ServiceFactory;

public class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();
	private final static UserService userService = new UserServiceImpl();
	
	public static ServiceFactory getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}
	
}
