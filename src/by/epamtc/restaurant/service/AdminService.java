package by.epamtc.restaurant.service;

import by.epamtc.restaurant.service.exception.ServiceException;

public interface AdminService {

	void changeOrderStatus(Integer orderId) throws ServiceException;
	
	void appointUserAnAdministrator(Integer userId) throws ServiceException;
	
	void appointAdministratorAnUser(Integer employeeId) throws ServiceException;
}
