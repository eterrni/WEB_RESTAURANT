package by.epamtc.restaurant.service;

import by.epamtc.restaurant.service.impl.DownloadMenuImpl;
import by.epamtc.restaurant.service.impl.UserServiceImpl;
import by.epamtc.restaurant.service.ServiceFactory;

public class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();
	
	private final static UserService userService = new UserServiceImpl();
	
	private final static DownloadMenuService downloadMenuService = new DownloadMenuImpl();
	
	public static ServiceFactory getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}
	
	public DownloadMenuService getDownloadMenuService() {
		return downloadMenuService;
	}
	
}
