package by.epamtc.restaurant.dao;

import java.util.List;

import by.epamtc.restaurant.bean.order.Order;
import by.epamtc.restaurant.dao.exception.DAOException;

public interface DownloadClientsOrderDAO {

	List<Order> downloadUnconfirmedClientsOrderList() throws DAOException;
	List<Order> downloadConfirmedClientsOrderList() throws DAOException;
}
