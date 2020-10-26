package by.epamtc.restaurant.service;

import by.epamtc.restaurant.service.impl.AdminServiceImpl;
import by.epamtc.restaurant.service.impl.DownloadAdminInfoImpl;
import by.epamtc.restaurant.service.impl.DownloadMenuImpl;
import by.epamtc.restaurant.service.impl.DownloadUserOrderImpl;
import by.epamtc.restaurant.service.impl.PlaceOrderImpl;
import by.epamtc.restaurant.service.impl.UserServiceImpl;
import by.epamtc.restaurant.service.ServiceFactory;

public class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();

	private static final AdminService adminService = new AdminServiceImpl();
	private static final UserService userService = new UserServiceImpl();

	private static final DownloadMenuService downloadMenuService = new DownloadMenuImpl();
	private static final DownloadUserOrderService downloadUserOrderService = new DownloadUserOrderImpl();
	private static final DownloadAdminInfoService downloadAdminInfoService = new DownloadAdminInfoImpl();

	private static final PlaceOrderService placeOrderService = new PlaceOrderImpl();

	public static ServiceFactory getInstance() {
		return instance;
	}

	public AdminService getAdminService() {
		return adminService;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public DownloadUserOrderService getDownloadUserOrderService() {
		return downloadUserOrderService;
	}

	public DownloadMenuService getDownloadMenuService() {
		return downloadMenuService;
	}
	
	public DownloadAdminInfoService getDownloadAdminInfoService() {
		return downloadAdminInfoService;
	}

	public PlaceOrderService getPlaceOrderService() {
		return placeOrderService;
	}

}
