package by.epamtc.restaurant.dao;

import java.util.List;

import by.epamtc.restaurant.bean.order.Order;
import by.epamtc.restaurant.bean.user.User;
import by.epamtc.restaurant.dao.exception.DAOException;

public interface DownloadAdminInfoDAO {

	List<Order> downloadUnconfirmedClientsOrderList() throws DAOException;
	List<Order> downloadConfirmedClientsOrderList() throws DAOException;
	List<User> downloadUserList() throws DAOException;
	List<User> downloadEmployeeList() throws DAOException;
}
