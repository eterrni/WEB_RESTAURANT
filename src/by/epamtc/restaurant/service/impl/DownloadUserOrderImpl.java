package by.epamtc.restaurant.service.impl;

import java.util.List;

import by.epamtc.restaurant.bean.order.Order;
import by.epamtc.restaurant.dao.DAOFactory;
import by.epamtc.restaurant.dao.DownloadUserOrderDAO;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.service.DownloadUserOrderService;
import by.epamtc.restaurant.service.exception.ServiceException;

public class DownloadUserOrderImpl implements DownloadUserOrderService {
	
	private static final DAOFactory factory = DAOFactory.getInstance();
	private static final DownloadUserOrderDAO downloadUserOrderDAO = factory.getDownloadUserOrderDAO();

	@Override
	public List<Order> download(Integer userId) throws ServiceException {
		List<Order> userOrderList = null;
		try {
			userOrderList = downloadUserOrderDAO.download(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return userOrderList;
	}

}
