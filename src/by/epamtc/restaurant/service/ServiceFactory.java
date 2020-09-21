package by.epamtc.restaurant.service;

import by.epamtc.restaurant.service.impl.LoginService;
import by.epamtc.restaurant.service.impl.RegistrationService;
import by.epamtc.restaurant.service.ServiceFactory;

public class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();
	private final static Service LOGIN_SERVICE = new LoginService();
	private final static Service REGISTRATION_SERVICE = new RegistrationService();
	
	public static ServiceFactory getInstance() {
		return instance;
	}
	
	public Service getLoginService() {
		return LOGIN_SERVICE;
	}
	
	public Service getRegistrationService() {
		return REGISTRATION_SERVICE;
	}
}
