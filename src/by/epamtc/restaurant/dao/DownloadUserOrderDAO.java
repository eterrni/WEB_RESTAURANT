package by.epamtc.restaurant.dao;

import java.util.List;

import by.epamtc.restaurant.bean.order.Order;
import by.epamtc.restaurant.bean.payment.Payment;
import by.epamtc.restaurant.dao.exception.DAOException;

public interface DownloadUserOrderDAO {

	List<Order> downloadUserOrderList(Integer userId) throws DAOException;
	
	List<Payment> downloadUserPaymentList(Integer userId) throws DAOException;
}
