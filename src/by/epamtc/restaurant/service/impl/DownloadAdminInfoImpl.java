package by.epamtc.restaurant.service.impl;

import java.util.List;

import by.epamtc.restaurant.bean.order.Order;
import by.epamtc.restaurant.bean.payment.Payment;
import by.epamtc.restaurant.bean.user.User;
import by.epamtc.restaurant.dao.DAOFactory;
import by.epamtc.restaurant.dao.DownloadAdminInfoDAO;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.service.DownloadAdminInfoService;
import by.epamtc.restaurant.service.exception.ServiceException;

public class DownloadAdminInfoImpl implements DownloadAdminInfoService {

	private static final DAOFactory factory = DAOFactory.getInstance();
	private static final DownloadAdminInfoDAO downloadAdminInfoDAO = factory.getDownloadAdminInfoDAO();

	@Override
	public List<Order> downloadUnconfirmedClientsOrderList() throws ServiceException {
		List<Order> unconfirmedClientsOrderList = null;
		try {
			unconfirmedClientsOrderList = downloadAdminInfoDAO.downloadUnconfirmedClientsOrderList();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return unconfirmedClientsOrderList;
	}

	public List<Order> downloadConfirmedClientsOrderList() throws ServiceException {
		List<Order> confirmedClientsOrderList = null;
		try {
			confirmedClientsOrderList = downloadAdminInfoDAO.downloadConfirmedClientsOrderList();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return confirmedClientsOrderList;
	}

	public List<User> downloadUserList() throws ServiceException {
		List<User> userList = null;
		try {
			userList = downloadAdminInfoDAO.downloadUserList();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return userList;
	}
	
	public List<User> downloadEmployeeList() throws ServiceException {
		List<User> employeeList = null;
		try {
			employeeList = downloadAdminInfoDAO.downloadEmployeeList();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return employeeList;
	}

	@Override
	public List<Payment> downloadPaymentList() throws ServiceException {
		List<Payment> paymentList = null;
		try {
			paymentList = downloadAdminInfoDAO.downloadPaymentList();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return paymentList;
	}
}
