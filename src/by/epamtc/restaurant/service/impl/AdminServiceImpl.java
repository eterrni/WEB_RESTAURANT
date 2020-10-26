package by.epamtc.restaurant.service.impl;

import by.epamtc.restaurant.dao.AdminDAO;
import by.epamtc.restaurant.dao.DAOFactory;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.service.AdminService;
import by.epamtc.restaurant.service.exception.ServiceException;

public class AdminServiceImpl implements AdminService {

	private static final DAOFactory factory = DAOFactory.getInstance();
	private static final AdminDAO adminDAO = factory.getAdminDAO();

	@Override
	public void changeOrderStatus(Integer orderId) throws ServiceException {
		try {
			adminDAO.changeOrderStatus(orderId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void appointUserAnAdministrator(Integer userId) throws ServiceException {
		try {
			adminDAO.appointUserAnAdministrator(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void appointAdministratorAnUser(Integer employeeId) throws ServiceException {
		try {
			adminDAO.appointAdministratorAnUser(employeeId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
