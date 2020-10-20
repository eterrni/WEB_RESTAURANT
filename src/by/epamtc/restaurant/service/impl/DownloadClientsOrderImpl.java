package by.epamtc.restaurant.service.impl;

import java.util.List;

import by.epamtc.restaurant.bean.order.Order;
import by.epamtc.restaurant.dao.DAOFactory;
import by.epamtc.restaurant.dao.DownloadClientsOrderDAO;
import by.epamtc.restaurant.dao.exception.DAOException;
import by.epamtc.restaurant.service.DownloadClientsOrderService;
import by.epamtc.restaurant.service.exception.ServiceException;

public class DownloadClientsOrderImpl implements DownloadClientsOrderService {

	private static final DAOFactory factory = DAOFactory.getInstance();
	private static final DownloadClientsOrderDAO downloadClientsOrderDAO = factory.getDownloadClientsOrderDAO();

	@Override
	public List<Order> downloadUnconfirmedClientsOrderList() throws ServiceException {
		List<Order> unconfirmedClientsOrderList = null;
		try {
			unconfirmedClientsOrderList = downloadClientsOrderDAO.downloadUnconfirmedClientsOrderList();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return unconfirmedClientsOrderList;
	}
	
	public List<Order> downloadConfirmedClientsOrderList() throws ServiceException {
		List<Order> confirmedClientsOrderList = null;
		try {
			confirmedClientsOrderList = downloadClientsOrderDAO.downloadConfirmedClientsOrderList();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return confirmedClientsOrderList;
	}

}
