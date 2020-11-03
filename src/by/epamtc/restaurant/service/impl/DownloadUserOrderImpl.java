package by.epamtc.restaurant.service.impl;

import java.util.List;

import by.epamtc.restaurant.bean.order.Order;
import by.epamtc.restaurant.bean.payment.Payment;
import by.epamtc.restaurant.dao.DAOFactory;
import by.epamtc.restaurant.dao.DownloadUserOrderDAO;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.service.DownloadUserOrderService;
import by.epamtc.restaurant.service.exception.ServiceException;

public class DownloadUserOrderImpl implements DownloadUserOrderService {

	private static final DAOFactory factory = DAOFactory.getInstance();
	private static final DownloadUserOrderDAO downloadUserOrderDAO = factory.getDownloadUserOrderDAO();

	@Override
	public List<Order> downloadUserOrderList(Integer userId) throws ServiceException {
		List<Order> userOrderList = null;
		try {
			userOrderList = downloadUserOrderDAO.downloadUserOrderList(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return userOrderList;
	}

	@Override
	public List<Payment> downloadUserPaymentList(Integer userId) throws ServiceException {
		List<Payment> userPaymentList = null;
		try {
			userPaymentList = downloadUserOrderDAO.downloadUserPaymentList(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return userPaymentList;
	}

}
