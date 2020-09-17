package by.epamtc.restaurant.service;

import by.epamtc.restaurant.service.impl.LoginService;
import by.epamtc.restaurant.service.impl.RegistrationService;
import by.epamtc.restaurant.service.ServiceFactory;

public class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();
	private final Service loginService = new LoginService();
	private final Service registartionService = new RegistrationService();
	
	public static ServiceFactory getInstance() {
		return instance;
	}
	
	public Service getLoginService() {
		return loginService;
	}
	
	public Service getRegistrationService() {
		return registartionService;
	}
}
