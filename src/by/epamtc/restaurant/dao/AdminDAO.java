package by.epamtc.restaurant.dao;

import by.epamtc.restaurant.dao.exception.DAOException;

public interface AdminDAO {

	void changeOrderStatus(Integer orderId) throws DAOException;
	
	void appointUserAnAdministrator(Integer userId) throws DAOException;
	
	void appointAdministratorAnUser(Integer employeeId) throws DAOException;
}
